import { Component } from '@angular/core';
import { DwButtonComponent } from '../dw-button/dw-button.component';
import { LnButtonComponent } from '../ln-button/ln-button.component';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [DwButtonComponent,LnButtonComponent],
  templateUrl: './home.component.html',
  styleUrl: './home.component.sass'
})
export class HomeComponent {

}
