import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import {MatToolbarModule} from '@angular/material/toolbar';
import { AuthService } from './auth/services/auth/auth.service';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, MatToolbarModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss',
  providers: [AuthService]
})
export class AppComponent {
  title = 'gestionnaire_taches_front';
}
