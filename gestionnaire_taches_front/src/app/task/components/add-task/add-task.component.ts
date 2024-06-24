import { Component, ViewChild } from '@angular/core';

import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { Router, RouterModule } from '@angular/router';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { FormsModule, NgForm } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { MatSelectModule } from '@angular/material/select';

interface body {
  name: string;
  desc: string;
  dueDate: string;
  userId: number;
  completed: Boolean;
  priority: number;
}

@Component({
  selector: 'app-add-task',
  standalone: true,
  imports: [
    RouterModule,
    MatCardModule, 
    MatButtonModule, 
    MatFormFieldModule, 
    MatInputModule, 
    MatDatepickerModule, 
    MatNativeDateModule, 
    FormsModule, 
    MatSelectModule
  ],
  templateUrl: './add-task.component.html',
  styleUrl: './add-task.component.scss'
})
export class AddTaskComponent {
  @ViewChild('addTaskForm') addTaskForm!: NgForm;
  private authService: AuthService;
  private userId: number = -1;
  private snackBarConfig = {
    duration: 5000
  };

  constructor(authService: AuthService, private http: HttpClient, private _snackBar: MatSnackBar, private router: Router) {
    this.authService = authService;
  }

  ngOnInit() {
    this.userId = this.authService.sharedProperties.userId;
    if (this.userId == -1) {
      this.router.navigate(['/login']);
    }
  }

  submitForm() {
    console.log(this.addTaskForm.value);

    let bodypost: body = {
      name: this.addTaskForm.form.get("name")?.getRawValue(),
      desc: this.addTaskForm.form.get("desc")?.getRawValue(),
      dueDate: this.addTaskForm.form.get("dueDate")?.getRawValue(),
      priority: this.addTaskForm.form.get("priority")?.getRawValue(),
      userId: this.userId,
      completed: false,
    }

    const httpOptions = {
      headers: new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
      })
    };

    const req = this.http.post("http://localhost:3000/task/add", bodypost, httpOptions);

    req.subscribe({
      next: data => {
        console.log(data);
        this._snackBar.open(data.toString(), "OK", this.snackBarConfig);
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
