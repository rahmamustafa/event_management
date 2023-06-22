import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  isUserLoggedIn:boolean = false;
  constructor(private authService:AuthService){}
  ngOnInit(): void {
    this.isUserLoggedIn = this.authService.isUserLoggedIn();
  }


  logOut() {
    localStorage.removeItem('username');
    localStorage.removeItem('token');
    console.log("logout")
    this.isUserLoggedIn = false;
  }


}
