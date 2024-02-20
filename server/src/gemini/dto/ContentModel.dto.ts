import { ApiProperty } from '@nestjs/swagger';
import { ApiResponseModel } from './ApiResponse.dto';
import { QuizModel } from './QuizModel.dto';

export class ContentModel {
  id: string;
  content: {
    intro: string;
    goals: string;
    advice: string;
    resources: string;
    quiz: QuizModel[];
  };
  constructor(id: string) {
    this.id = id;
  }
}
export class GetContentResponse extends ApiResponseModel {
  constructor({
    status,
    msg,
    errors,
    data,
  }: {
    status: boolean;
    msg?: string;
    errors?: [string];
    data?: ContentModel;
  }) {
    super({ status, msg, errors });
    this.data = data;
  }
  @ApiProperty({
    type: ContentModel,
    name: 'data',
    description: 'Object containing contents for the userId',
  })
  data?: ContentModel;
}
