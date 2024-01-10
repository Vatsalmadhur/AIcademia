import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet } from '@angular/router';
import { NavbarComponent } from './navbar/navbar.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, RouterOutlet,NavbarComponent,RouterModule],
  templateUrl: './app.component.html',
  // styleUrl: './navbar/navbar.component.sass'
})
export class AppComponent {
  title = 'app';
}
