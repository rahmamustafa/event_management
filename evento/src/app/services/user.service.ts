import { Injectable } from '@angular/core';
import { ApiService } from './api.service';
import { HttpClient } from '@angular/common/http';
import { JwtHelperService } from '@auth0/angular-jwt';



@Injectable({
  providedIn: 'root'
})
export class UserService {
  userId:any ;
  private readonly TOKEN_KEY = 'token';

  constructor(private http: HttpClient ,  private jwtHelper: JwtHelperService) { }

   getuserEmail(): any {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const payload = token.split('.')[1]; 
      const decodedPayload = atob(payload); 
      return JSON.parse(decodedPayload).sub; 
    }
    return null;
  }
   
  getuserRole(): any {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      console.log("Role   " + this.getClaim(token, "isAdmin"));
      // return this.getClaim(token, "isAdmin");
    }
    return null;
  }

  public decodeToken(token: string): any {
    return this.jwtHelper.decodeToken(token);
  }

  public getClaim(token: string, claimKey: string): any {
    const decodedToken = this.decodeToken(token);
    return decodedToken ? decodedToken[claimKey] : null;
  }
   
 
}
