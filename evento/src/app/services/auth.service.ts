import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { Observable } from "rxjs";

@Injectable({
    providedIn: 'root'
  })
export class AuthService implements HttpInterceptor{

  private readonly TOKEN_KEY = 'token';

  constructor() {}

  intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {

    const token = localStorage.getItem(this.TOKEN_KEY);
    const modifiedRequest = request.clone({
      setHeaders: {
        Authorization: ``+token
      }
    });

    return next.handle(modifiedRequest);
  }
 
    isUserLoggedIn() {
        let user = localStorage.getItem('username')
        return !(user === null)
      }
}
