import { CommonModule } from '@angular/common';
import { ChangeDetectionStrategy, ChangeDetectorRef, Component, ViewChild, inject, signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import { Router, RouterModule } from '@angular/router';

import {MatExpansionModule} from '@angular/material/expansion';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { MatSnackBar } from '@angular/material/snack-bar';
import { AuthService } from '../../../auth/services/auth/auth.service';
import { MatCardModule } from '@angular/material/card';
import { MatSelectModule } from '@angular/material/select';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';

interface taskList {
  name: String;
  desc: String;
  dueDate: Date;
  id: number;
  completed: Boolean;
  priority: number;
}

@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [
    RouterModule, 
    CommonModule, 
    MatListModule, 
    MatButtonModule, 
    MatExpansionModule, 
    MatCardModule, 
    MatSelectModule,
    FormsModule,
    MatFormFieldModule
  ],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.scss',
  changeDetection: ChangeDetectionStrategy.Default
})
export class TaskListComponent {
  private authService: AuthService;
  userId: number = -1;
  tasks: taskList[] = [];
  taskIndex = signal(0);
  sorted: boolean = false;
  private snackBarConfig = {
    duration: 5000
  };
  private localUrl = "http://localhost:3000";
  httpOptions = {
    headers: new HttpHeaders({
        'Accept': 'application/json',
        'Content-Type': 'application/json'
    })
  };

  setTaskIndex(index: number) {
    console.log(index);
    this.taskIndex.set(index);
  }

  constructor(authService:AuthService, private http: HttpClient, private _snackBar: MatSnackBar, private router: Router, private changeDetection: ChangeDetectorRef) {  
    this.authService = authService;
  }

  ngOnInit() {
    this.userId = this.authService.sharedProperties.userId;
    if (this.userId == -1) {
      this.router.navigate(['/login']);
    }
    this.getTaskList();
  }

  handleError(error: any) {
    const errMessage = error.error[0];
    console.error(errMessage);
    this._snackBar.open(errMessage, "OK", this.snackBarConfig);
  }

  getTaskList() {
    const req = this.http.get(this.localUrl + "/task/getList/" + this.userId, this.httpOptions);

    req.subscribe({
      next: data => {
        console.log(data);
        var dataArray = Object.values(data);
        this.tasks = dataArray;
      },
      error: error => {
        this.handleError(error);
      }
    });
  }

  sortTaskList() {
    let funcStr = "getList";
    if (!this.sorted) {
      funcStr = "sortList";
    }
    
    this.sorted = !this.sorted;

    const req = this.http.get(this.localUrl + "/task/" + funcStr + "/" + this.userId, this.httpOptions);

    req.subscribe({
      next: data => {
        console.log(data);
        var dataArray = Object.values(data);
        this.tasks = dataArray;
      },
      error: error => {
        this.handleError(error);
      }
    });
  }

  deleteTask() {
    const req = this.http.delete(this.localUrl + "/task/delete/" + this.taskIndex(), this.httpOptions);

    req.subscribe({
      next: data => {
        console.log(data);
        this._snackBar.open(data.toString(), "OK", this.snackBarConfig);
        console.log("taskList before delete : ", this.tasks);
        this.tasks = this.tasks.filter(( task ) => {
          return task.id !== this.taskIndex();
        });
        console.log("taskList after delete : ", this.tasks);
        this.changeDetection.detectChanges();
      },
      error: error => {
        this.handleError(error);
      }
    });
  }

  completeTask() {
    const req = this.http.post(this.localUrl + "/task/complete/" + this.taskIndex(), this.httpOptions);

    req.subscribe({
      next: data => {
        console.log(data);
        const indexOfTaskToComplete = this.tasks.findIndex((task) => task.id == this.taskIndex());
        this.tasks[indexOfTaskToComplete].completed = !this.tasks[indexOfTaskToComplete].completed;
        this._snackBar.open(data.toString(), "OK", this.snackBarConfig);
        this.changeDetection.detectChanges();
      },
      error: error => {
        this.handleError(error);
      }
    });
  }

  changePriority(newPriority: any) {
    const req = this.http.post(this.localUrl + "/task/changePriority/" + this.taskIndex(), newPriority, this.httpOptions);

    req.subscribe({
      next: data => {
        this._snackBar.open(data.toString(), "OK", this.snackBarConfig);
      },
      error: error => {
        this.handleError(error);
      }
    });
  }
  
}
