import { ApiProperty } from '@nestjs/swagger';
import { ApiResponseModel } from './ApiResponse.dto';
import {QuizModel} from './QuizModel.dto';

export class GetQuizResponse extends ApiResponseModel {
  constructor({
    status,
    msg,
    errors,
    data,
  }: {
    status: boolean;
    msg?: string;
    errors?: [string];
    data?: QuizModel[];
  }) {
    super({ status, msg, errors });
    this.data = data;
  }
  @ApiProperty({
    type: QuizModel,
    isArray: true,
    name: 'data',
    description: 'Object containing contents for the userId',
  })
  data?: QuizModel[];
}
