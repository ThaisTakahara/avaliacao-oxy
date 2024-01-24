import { Component } from '@angular/core';
import { HeaderComponent } from './components/template/header/header.component';
import { NavComponent } from './components/template/nav/nav.component';
import { FooterComponent } from './components/template/footer/footer.component';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    HeaderComponent,
    NavComponent, 
    FooterComponent],
  templateUrl: 'app.component.html'
})

export class AppComponent {
  
}
