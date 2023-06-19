import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class EmailSubscriptionService {
  private baseUrl = 'http://localhost:8888/api/subscribe';

  constructor(private http: HttpClient) { }

  subscribeEmail(email: any) {
    console.log(email);
    return this.http.post(this.baseUrl, { email });
  }
}
