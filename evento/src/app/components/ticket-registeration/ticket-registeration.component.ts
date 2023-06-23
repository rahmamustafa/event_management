import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserTicket } from 'src/app/models/event-registration/user-ticket.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-ticket-registeration',
  templateUrl: './ticket-registeration.component.html',
  styleUrls: ['./ticket-registeration.component.css']
})
export class TicketRegisterationComponent  implements OnInit{
  constructor(private apiService: ApiService, private _formBuilder: FormBuilder

    , private http: HttpClient,private _router: Router) {

  }
  userTicket:UserTicket;
  ngOnInit() {
    // Initialize the registerForm with FormGroup and FormControls
    this.registerForm = this._formBuilder.group({
      
      userId:new FormControl(''),
      quantity:new FormControl('')
      
     

      // Add other form controls here
    });
  }
  registerForm: FormGroup;
  register(): void {
    this.makePayment();
    // var response1;
    // this.userTicket = this.registerForm.value;
    // console.log("->>>>>>>>>>>"+this.userTicket)
    // this.userTicket.eventId=1;
    // this.userTicket.ticketId=1;
    // this.apiService.post("pay", this.userTicket)
    //   .subscribe({
    //     next: response => {
    //       console.log(response)
    //       response1=response;
    //       // if(response==true)
    //       //   this._router.navigateByUrl('/home');
    //     },
    //     error: error => { }
    //   }
    //   );
      // this.apiService(response1)
      // .subscribe({
      //   next: response => {
      //     console.log(response)
      //     response1=response;
      //     // if(response==true)
      //     //   this._router.navigateByUrl('/home');
      //   },
      //   error: error => { }
      // }
      // );
  }
  makePayment() {
    this.apiService.post("pay", this.userTicket)
      .subscribe({
        next: response => {
          console.log(response)
         
          // if(response==true)
          //   this._router.navigateByUrl('/home');
          return response.json();
        },
        error: error => { }
      }
      );
      
  }
}
