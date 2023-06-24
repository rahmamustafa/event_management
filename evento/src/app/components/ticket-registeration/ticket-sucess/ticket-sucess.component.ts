import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-ticket-sucess',
  templateUrl: './ticket-sucess.component.html',
  styleUrls: ['./ticket-sucess.component.css']
})
export class TicketSucessComponent  implements OnInit{
  constructor(private apiService: ApiService

    , private http: HttpClient,private _router: Router) {
      console.log("hiiii")
  }
 
  ngOnInit() {
    console.log("hiiii")
    this.apiService.get("pay/success")
      .subscribe({
        next: (response: any) => {
          console.log(response)
        
    }
  });
  }

}
