import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  sharedProperties = {
    userId: -1
  };

  constructor() { }
}
