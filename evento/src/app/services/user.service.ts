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
  jwtHelper: JwtHelperService;
  constructor(private http: HttpClient) {
    this.jwtHelper =  new JwtHelperService();
   }

  public getuserEmail(): any {
    const token = localStorage.getItem(this.TOKEN_KEY);
    if (token) {
      const payload = token.split('.')[1]; 
      const decodedPayload = atob(payload); 
      return JSON.parse(decodedPayload).sub; 
    }
    return null;
  }
   UserId(callback: (val:any) => void): void {
  
    let email = this.getuserEmail();

    this.http.post<any>("http://localhost:8888/user",{"email":email.sub})
    .subscribe({
      next:(response: any)=>{
        callback( response);
      },
      error:(error: any)=>{
        callback( null);
      }
    }
    );
  }
  getUserId(userId:any):any{
    this.userId =userId;
    console.log(this.userId);
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
