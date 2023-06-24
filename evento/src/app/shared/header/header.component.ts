import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/services/auth.service';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit{

  isUserLoggedIn:boolean = false;
  isAdmin:boolean=false;
  constructor(private authService:AuthService , private userService:UserService){}
  ngOnInit(): void {
    this.isUserLoggedIn = this.authService.isUserLoggedIn();
    this.isAdmin = this.userService.getUserRole()==1?true:false;
    console.log("isAdmin "+ this.isAdmin);
    this.userService.getUserIdToken();
  }

  logOut() {
    localStorage.removeItem('username');
    localStorage.removeItem('token');
    this.isAdmin=false;
    console.log("logout")
    this.isUserLoggedIn = false;
  }
  


}
