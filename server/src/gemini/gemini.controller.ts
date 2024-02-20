import { Body, Controller, Get, Inject, Post, Query } from '@nestjs/common';
import { GeminiService } from './gemini.service';
import { ApiResponseModel } from './dto/ApiResponse.dto';
import { GetContentResponse } from './dto/ContentModel.dto';
import { ApiBody, ApiOkResponse, ApiTags } from '@nestjs/swagger';
import { GetQuizResponse } from './dto/QuizResponseModel.dto';
import { EnqueueProducer } from 'src/kafka/producer/Enqueue.producer';
import { QueueMessage } from './dto/QueueMessage.dto';



@Controller('/')
@ApiTags('Gemini')
export class GeminiController {
  constructor(
    private readonly geminiService: GeminiService,
    @Inject(EnqueueProducer) private readonly enqueuer: EnqueueProducer,
  ) {}

  @Get('content')
  @ApiOkResponse({ type: GetContentResponse })
  async getContent(@Query('id') id: string): Promise<GetContentResponse> {
    try {
      const content = await this.geminiService.getContentById(id);
      if (!!content) {
        return new GetContentResponse({ status: true, data: content });
      } else throw new Error('Unable to get content');
    } catch (e) {
      return { status: false, msg: e.message || 'Unable to get content' };
    }
  }

  @ApiOkResponse({ type: GetQuizResponse })
  @ApiBody({
    type: QueueMessage,
  })

  @Post('enqueue_content')
  async enqueueContentGen(@Body() {id}:{id:string}): Promise<ApiResponseModel> {
    try {
      await this.enqueuer.enqueue('generate-content', [
        {
          value: id,
        },
      ]);
        return new ApiResponseModel({ status: true, msg: 'Content generation enqueued' });
    } catch (e) {
      return {
        status: false,
        msg: e.message || 'Error during enqueue process',
      };
    }
  }



  @ApiOkResponse({ type: GetQuizResponse })
  @ApiBody({
    type: QueueMessage,
  })
  @Post('enqueue_quiz')
  async enqueueQuizGen(@Body() {id}:{id:string}): Promise<ApiResponseModel> {
    try {
      await this.enqueuer.enqueue('generate-quiz', [
        {
          value: id,
        },
      ]);
        return new ApiResponseModel({ status: true, msg: 'Quiz generation enqueued' });
    } catch (e) {
      return {
        status: false,
        msg: e.message || 'Error during enqueue process',
      };
    }
  }

  @Get('update_quiz')
  async updateQuiz(@Query('id') id: string): Promise<GetQuizResponse> {
    try {
      const content = await this.geminiService.generateQuizForUser(id);
      if (!!content) {
        return new GetQuizResponse({ status: true, data: content });
      } else throw new Error('Unable to generate content');
    } catch (e) {
      return { status: false, msg: e.message || 'Unable to generate quiz' };
    }
  }

  @Get('notify')
  async notifyUser(@Query('id') id: string): Promise<ApiResponseModel> {
    try {
      const message_id = await this.geminiService.notifyUser(id, {
        body: 'Test content',
        title: 'Test title',
      });
      if (!!message_id) {
        return new ApiResponseModel({ status: true, msg: 'User notified' });
      } else throw new Error('Unable to notify user');
    } catch (e) {
      return { status: false, msg: e.message || 'Unable to notify user' };
    }
  }

  @Get('update_content')
  async updateContent(@Query('id') id: string): Promise<ApiResponseModel> {
    try {
      const content = await this.geminiService.generateContentForUser(id);
      if (!!content) {
        return new GetContentResponse({ status: true, data: content });
      } else throw new Error('Unable to generate content');
    } catch (e) {
      return { status: false, msg: e.message || 'Unable to generate content' };
    }
  }
}
