import { bootstrapApplication } from '@angular/platform-browser';
import { appConfig } from './app/app.config';
import { AppComponent } from './app/app.component';
import { provideRouter } from '@angular/router';
import routeConfig from './app/routes';
import { provideProtractorTestingSupport } from '@angular/platform-browser';
import { provideAnimations } from '@angular/platform-browser/animations';

bootstrapApplication(AppComponent,   {
  providers: [
    provideProtractorTestingSupport(),
    provideRouter(routeConfig),
    provideAnimations()
]
})
  .catch((err) => console.error(err));
