import { Inject, Injectable } from '@nestjs/common';
import { ProducerService } from './producer.service';
import { Message } from 'kafkajs';

@Injectable()
export class EnqueueProducer {
  constructor(private producerService: ProducerService) {}

  async enqueue(topic: string, messages: Message[]) {
    try {
      this.producerService.produce({
        topic,
        messages,
      });
    } catch (e) {
      throw e;
    }
  }
}
