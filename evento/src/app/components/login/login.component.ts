import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  constructor(private apiService:ApiService){}

  ngOnInit(): void {
   
    console.log(
      this.apiService.get("events?page=0&size=20")
      .subscribe({
        next:response=>{
        console.log(response._embedded);
      },
      error:error=>{}
    }
    ));
  }
  login(): void{
    this.apiService.get("/")
    .subscribe({
      next:response=>{
        console.log(response._embedded)
      },
      error:error=>{}
    }
    );
  }
}

