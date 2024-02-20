import { Injectable, Scope } from '@nestjs/common';
import { GoogleGenerativeAI } from '@google/generative-ai';
import { ConfigService } from '@nestjs/config';

@Injectable({ scope: Scope.DEFAULT })
export class GeminiProvider {
  private gemini: GoogleGenerativeAI;

   constructor(private config: ConfigService) {
    this.initializeGemini();
  }

  getInstance(): GoogleGenerativeAI {
    return this.gemini;
  }

  private initializeGemini(): void {
    const apiKey = this.config.get('GEMINI_API_KEY');
    this.gemini = new GoogleGenerativeAI(apiKey);
  }

  updateApiKey(apiKey: string): void {
    this.gemini = new GoogleGenerativeAI(apiKey);
  }
}



