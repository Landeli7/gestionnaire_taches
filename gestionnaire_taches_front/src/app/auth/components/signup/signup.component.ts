import { Component, ViewChild } from '@angular/core';
import { Router, RouterModule } from '@angular/router';

import {MatButtonModule} from '@angular/material/button';
import {MatCardModule} from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field';
import {MatInputModule} from '@angular/material/input';

import { FormsModule, NgForm } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import {MatSnackBar} from '@angular/material/snack-bar';

interface body {
  name: string;
  email: string;
  pass: string;
}

@Component({
  selector: 'app-signup',
  standalone: true,
  imports: [
    RouterModule, 
    MatCardModule, 
    MatButtonModule, 
    MatFormFieldModule, 
    MatInputModule, 
    FormsModule
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss'
})
export class SignupComponent {
  @ViewChild('signupForm') signupForm!: NgForm;
  private snackBarConfig = {
    duration: 5000
  };

  constructor(private http: HttpClient, private _snackBar: MatSnackBar, private router: Router) { }

  submitForm() {
    console.log(this.signupForm.value);
    
    let bodypost: body = {
      name: this.signupForm.form.get("name")?.getRawValue(),
      email: this.signupForm.form.get("email")?.getRawValue(),
      pass: this.signupForm.form.get("pass")?.getRawValue(),
    }

    const httpOptions = {
      headers: new HttpHeaders({
          'Accept': 'application/json',
          'Content-Type': 'application/json'
      })
    };
      
    const req = this.http.post("http://localhost:3000/auth/signup", bodypost, httpOptions);

    req.subscribe({
      next: data => {
        console.log(data);
        this._snackBar.open(data.toString(), "OK", this.snackBarConfig);
        this.router.navigate(['/login']);

      },
      error: error => {
        const errMessage = error.error[0];
        console.error(errMessage);
        this._snackBar.open(errMessage, "OK", this.snackBarConfig);
      }
    });
  }
}
