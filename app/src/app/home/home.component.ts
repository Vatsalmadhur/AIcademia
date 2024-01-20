import { Component } from '@angular/core';
import { DwButtonComponent } from '../dw-button/dw-button.component';
import { LnButtonComponent } from '../ln-button/ln-button.component';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-home',
  standalone: true,
  imports: [DwButtonComponent,LnButtonComponent,RouterModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.sass'
})
export class HomeComponent {

}
