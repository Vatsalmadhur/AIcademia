import { Component } from '@angular/core';
import { ContentComponent } from '../content/content.component';
import { CardComponent } from '../card/card.component';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [ContentComponent,CardComponent],
  templateUrl: './about.component.html',
  styleUrl: './about.component.sass'
})
export class AboutComponent {

}
