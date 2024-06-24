import { Component, ViewChild } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule, NgForm } from '@angular/forms';
import { AuthService } from '../../services/auth/auth.service';

interface body {
  email: string,
  pass: string
}

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    RouterModule, 
    MatCardModule, 
    MatButtonModule, 
    MatFormFieldModule, 
    MatInputModule, 
    FormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  @ViewChild('loginForm') loginForm!: NgForm;
  private snackBarConfig = {
    duration: 5000
  };

  private authService: AuthService;

  constructor(authService:AuthService, private http: HttpClient, private _snackBar: MatSnackBar, private router: Router) {
    this.authService = authService;
  }

  submitForm() {
    console.log(this.loginForm.value);
    
    let bodypost: body = {
      email: this.loginForm.form.get("email")?.getRawValue(),
      pass: this.loginForm.form.get("pass")?.getRawValue(),
    }

    const httpOptions = {
      headers: new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
      })
    };
      
    const req = this.http.post("http://localhost:3000/auth/login", bodypost, httpOptions);

    req.subscribe({
      next: data => {
        console.log(data);
        this.authService.sharedProperties.userId = parseInt(data.toString());
        this._snackBar.open("Connexion réussie.", "OK", this.snackBarConfig);
        this.router.navigate(['/tasklist']);

      },
      error: error => {
        const errMessage = error.error[0];
        console.error(errMessage);
        this._snackBar.open(errMessage, "OK", this.snackBarConfig);
      }
    });

  }
}
