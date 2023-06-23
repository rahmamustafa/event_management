import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
  })
export class AuthService{

    isUserLoggedIn() {
        let user = localStorage.getItem('username')
        return !(user === null)
      }
}
