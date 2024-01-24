import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { RouterOutlet, RouterLink, RouterLinkActive } from '@angular/router';

@Component({
  selector: 'app-pessoa-crud',
  standalone: true,
  imports: [
    MatButtonModule,
    RouterOutlet, 
    RouterLink, 
    RouterLinkActive
  ],
  templateUrl: './pessoa-crud.component.html',
  styleUrl: './pessoa-crud.component.css'
})
export class PessoaCrudComponent {
  
}
