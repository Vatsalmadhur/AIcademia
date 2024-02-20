  export class QuizModel {
    question: string;
    options: string[];
    answer: number;

    constructor({ question, options, answer }: QuizModel) {
      this.question = question;
      this.options = options;
      this.answer = answer;
    }
  }
