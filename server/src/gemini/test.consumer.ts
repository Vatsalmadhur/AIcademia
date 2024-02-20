import { Injectable, OnModuleInit } from '@nestjs/common';
import { GeminiService } from 'src/gemini/gemini.service';
import { ConsumerService } from 'src/kafka/consumer/consumer.service';

@Injectable()
export class TestConsumer implements OnModuleInit {
  constructor(
    private readonly consumerService: ConsumerService,
    private geminiService: GeminiService,
  ) {}

  async handleIncomingContentRequest({ value }) {
    try {
      const id = value.toString();
      await this.geminiService.generateContentForUser(id);
      await this.geminiService.notifyUser(id, {
        title: "Content refreshed",
        body: 'Your content has been refreshed. Check it out now!',
      })
    } catch (e) {
      console.log(e);
    }
  }

  async handleIncomingQuizRequest({ value }) {
    try {
      const id = value.toString();
      await this.geminiService.generateQuizForUser(id);
      await this.geminiService.notifyUser(id, {
        title: "Quiz refreshed",
        body: 'Your quiz has been refreshed. Check it out now!',
      })
    } catch (e) {
      console.log(e);
    }
  }

  async onModuleInit() {
    await this.consumerService.consume(
      { topics: ['generate-content', 'generate-quiz', 'test'] },
      {
        eachMessage: async ({ topic, message }) => {
          console.log({
            value: message.value.toString(),
            topic: topic.toString(),
          });
          switch (topic.toString()) {
            case 'generate-content':
              this.handleIncomingContentRequest(message);
              break;
            case 'generate-quiz':
              this.handleIncomingQuizRequest(message);
              break;
            case 'test':
              console.log('test');
              break;
          }
        },
      },
    );
  }
}
