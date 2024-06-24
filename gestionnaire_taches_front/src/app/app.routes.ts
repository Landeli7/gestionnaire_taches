import { Routes } from '@angular/router';
import { SignupComponent } from './auth/components/signup/signup.component';
import { LoginComponent } from './auth/components/login/login.component';
import { TaskListComponent } from './task/components/task-list/task-list.component';
import { AddTaskComponent } from './task/components/add-task/add-task.component';

export const routes: Routes = [
    {path: "signup", component: SignupComponent},
    {path: "login", component: LoginComponent},
    {path: "", component: LoginComponent},
    {path: "tasklist", component: TaskListComponent},
    {path: "addtask", component: AddTaskComponent}
];
