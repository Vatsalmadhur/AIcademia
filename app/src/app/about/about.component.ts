import { Component } from '@angular/core';
import { ContentComponent } from '../content/content.component';

@Component({
  selector: 'app-about',
  standalone: true,
  imports: [ContentComponent],
  templateUrl: './about.component.html',
  styleUrl: './about.component.sass'
})
export class AboutComponent {

}
