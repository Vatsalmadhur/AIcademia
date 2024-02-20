import { Inject, Injectable } from '@nestjs/common';
import { GeminiProvider } from './gemini.provider';
import { GenerativeModel } from '@google/generative-ai';
import { FirestoreProvider } from 'src/firestore/firestore.provider';
import { Firestore } from 'firebase-admin/firestore';
import { ContentModel } from './dto/ContentModel.dto';
import { UserModel } from './dto/UserModel.dto';
import { QuizModel } from './dto/QuizModel.dto';
import { prompts } from './Prompts';
import { messaging } from 'firebase-admin';
import { Messaging } from 'firebase-admin/lib/messaging/messaging';

class GenModel {
  history: string[];
  prompt: string;
}

@Injectable()
export class GeminiService {
  private readonly bot: GenerativeModel;
  private readonly db: Firestore;
  private readonly messaging:Messaging
  constructor(
    @Inject(GeminiProvider) private readonly geminiProvider: GeminiProvider,
    @Inject(FirestoreProvider)
    private readonly firestoreProvider: FirestoreProvider,
  ) {
    this.bot = this.geminiProvider.getInstance().getGenerativeModel({
      model: 'gemini-pro',
    });
    this.db = this.firestoreProvider.getInstance();
    this.messaging = messaging()
  }

  async notifyUser(id: string, data:{title:string, body:string}): Promise<String> {
    try {
      const fcm_id = await this.db.collection('fcm-keys').doc(id).get()
      const message_id = await this.messaging.send({
        notification: {
          title: data.title,
          body: data.body,
        },
        data: {
          id,
        },
        token: fcm_id.data().fcm_key
      });
      return message_id
    } catch (e) {
      throw new Error(e.message || 'Unable to notify user');
    }
  }

  async generateText(prompt: string): Promise<string> {
    const result = await this.bot.generateContent(prompt);
    return result.response.text();
  }
  async getAllUsers(): Promise<UserModel[]> {
    const usersRef = this.db.collection('users');
    const users = await usersRef.get();
    return users.docs.map((doc) => {
      const data = doc.data();
      return {
        id: doc.id,
        acquired_skills: data.acquired_skills,
        current_skill: data.current_skill,
      };
    });
  }
  async getUserById(id: string): Promise<UserModel> {
    const userRef = this.db.collection('users').doc(id);
    const user = await userRef.get();
    const data = user.data();
    return !!data
      ? {
          id,
          acquired_skills: data.acquired_skills,
          current_skill: data.current_skill,
        }
      : null;
  }

  private getPrompt = (user: UserModel) => {
    return `
    You are working as a personal teacher for a student.
    This particular student have already acquired some skills and want to learn some new skill.
    As a personal teacher your job is to respond with valid answers for the questions that the student is asking.
    Answer only what is asked and follow the above instructions strictly.
    Act as a computer software.
    Only respond to the query, no conversation.
    Skills I have: ${user.acquired_skills?.join(', ')}
    Skills I am trying to learn: ${user.current_skill}
    `;
  };

  async getContentById(id: string): Promise<ContentModel> {
    const contentRef = this.db.collection('content').doc(id);
    const content = await contentRef.get();
    const data = content.data();
    if (!data) throw new Error('Content not found');
    return {
      id: content.id,
      content: {
        intro: data.intro,
        goals: data.goals,
        advice: data.advice,
        resources: data.resources,
        quiz: data.quiz,
      },
    };
  }

  async generateContentForUser(id: string): Promise<ContentModel> {
    try {
      const user = await this.getUserById(id);
      if (!user) throw new Error('User not found');
      const userGenModel = await this.db.collection('gen-model').doc(id).get();
      if (
        userGenModel.exists &&
        userGenModel.updateTime.toDate().getDate() >= new Date().getDate()
      ) {
        throw new Error('You can only generate content once a day');
      }
      const history = userGenModel.data()?.history || [];
      if (history.length === 0)
        history.push(
          { role: 'user', parts: this.getPrompt(user) },
          { role: 'model', parts: "okay, let's start" },
        );
      const chat = this.bot.startChat({
        history,
      });
      const tmpRes = new ContentModel(id);
      const genereatedContent = {
        intro: null,
        goals: null,
        advice: null,
        resources: null,
        quiz: [],
      };

      await Promise.all(
        Object.keys(prompts).map(async (part) => {
          const res = await chat.sendMessage(prompts[part]);
          if (part === 'quiz') {
            try {
              const str = res.response.text();
              const jsonStr = str.split('%%%')[1];
              const quiz = JSON.parse(jsonStr);
              quiz.forEach((q: QuizModel) => {
                genereatedContent.quiz.push(q);
              });
            } catch (e) {
              console.log(e);
            }
          } else genereatedContent[part] = res.response.text();
        }),
      );
      tmpRes.content = genereatedContent;
      if (userGenModel.exists) {
        await this.updateContentById(id, tmpRes.content);
        await this.db
          .collection('gen-model')
          .doc(id)
          .update({
            history: await chat.getHistory(),
          });
      } else {
        await this.createContentById(id, tmpRes.content);
        await this.db
          .collection('gen-model')
          .doc(id)
          .set({
            history: await chat.getHistory(),
          });
      }

      return tmpRes;
    } catch (error) {
      console.log(error);
      throw new Error(error.message || 'Error generating content');
    }
  }

  async generateQuizForUser(id: string): Promise<QuizModel[]> {
    try {
      const user = await this.getUserById(id);
      if (!user) throw new Error('User not found');
      const userGenModel = await this.db.collection('gen-model').doc(id).get();
      if (
        userGenModel.exists &&
        userGenModel.updateTime.toDate().getDate() >= new Date().getDate()
      ) {
      //  throw new Error('You can only generate content once a day');
      }
      const history = userGenModel.data()?.history || [];
      if (history.length === 0)
        history.push(
          { role: 'user', prompts: this.getPrompt(user) },
          { role: 'model', prompts: "okay, let's start" },
        );
      const chat = this.bot.startChat({
        history,
      });
      const quizArray:QuizModel[] = []
      const res = await chat.sendMessage(prompts.quiz);
      const str = res.response.text();
      const jsonStr = str.split('%%%')[1];
      const quiz = JSON.parse(jsonStr);
      quiz.forEach((q: QuizModel) => {
        quizArray.push(q);
      });
      if (userGenModel.exists) {
        await this.updateQuizById(id, quizArray);
        await this.db
          .collection('gen-model')
          .doc(id)
          .update({
            history: await chat.getHistory(),
          });
      } else {
        await this.db
          .collection('gen-model')
          .doc(id)
          .set({
            history: await chat.getHistory(),
          });
      }
      return quizArray
    } catch (error) {
      throw new Error(error.message || 'Error generating content');
    }
  }

  private async createContentById(
    id: string,
    content: ContentModel['content'],
  ) {
    const contentRef = this.db.collection('content').doc(id);
    await contentRef.set(content);
  }

  private async updateQuizById(id: string, quiz: QuizModel[]) {
    const contentRef = this.db.collection('content').doc(id);
    await contentRef.update({ quiz });
  }

  private async updateContentById(
    id: string,
    content: ContentModel['content'],
  ) {
    const contentRef = this.db.collection('content').doc(id);
    await contentRef.update(content);
  }
}
