import { Module } from '@nestjs/common';
import { GeminiService } from './gemini.service';
import { GeminiController } from './gemini.controller';
import { GeminiProvider } from './gemini.provider';
import { FirestoreProvider } from 'src/firestore/firestore.provider';
import { KafkaModule } from 'src/kafka/kafka.module';
import { EnqueueProducer } from 'src/kafka/producer/Enqueue.producer';
import { TestConsumer } from './test.consumer';

@Module({
  imports: [KafkaModule],
  controllers: [GeminiController],
  providers: [GeminiService, GeminiProvider, FirestoreProvider, EnqueueProducer, TestConsumer],
})
export class GeminiModule {}
