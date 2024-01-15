import { Component, NgModule } from '@angular/core';
import {MatButtonModule} from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';

@Component({
  selector: 'app-dw-button',
  standalone: true,
  imports: [MatButtonModule,MatIconModule],
  templateUrl: './dw-button.component.html',
  styleUrl: './dw-button.component.sass'
})
export class DwButtonComponent {

}
