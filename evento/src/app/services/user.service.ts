import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { HttpClient } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  userId:any ;
  private readonly TOKEN_KEY = 'token';

  constructor(private http: HttpClient) { }

   getuserEmail(): any {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const payload = token.split('.')[1]; 
      const decodedPayload = atob(payload); 
      return JSON.parse(decodedPayload).sub; 
    }
    return null;
  }
   
 
}
