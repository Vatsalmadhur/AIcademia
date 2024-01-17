import { Component,Input } from '@angular/core';
import {MatCardModule} from '@angular/material/card';
@Component({
  selector: 'app-card',
  standalone: true,
  imports: [MatCardModule],
  templateUrl: './card.component.html',
  styleUrl: './card.component.sass'
})
export class CardComponent {
@Input() title :string;
@Input() content :string;
}
