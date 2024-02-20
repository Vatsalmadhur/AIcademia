import { Firestore } from '@google-cloud/firestore';
import { Inject, Injectable } from '@nestjs/common';
import { ConfigService } from '@nestjs/config';
import { messaging } from 'firebase-admin';
import { cert, initializeApp } from 'firebase-admin/app';
import {getFirestore} from "firebase-admin/firestore"

@Injectable()
export class FirestoreProvider{
  private firestore: Firestore;
  constructor(
    @Inject(ConfigService) private readonly config: ConfigService,
  ) {
    initializeApp({
      credential: cert(this.config.get('SA_KEY')),
    });
    this.firestore = getFirestore()
  }

  getInstance(): Firestore {
    return this.firestore;
  }
}
