import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { UserTicket } from 'src/app/models/event-registration/user-ticket.model';
import { ApiService } from 'src/app/services/api.service';
import { render } from 'creditcardpayments/creditCardPayments';
import { UserService } from 'src/app/services/user.service';
import { eventDetailsDTO } from 'src/app/models/event-details.model';
import { DatePipe } from '@angular/common';
import { EventTicket } from 'src/app/models/event-registration/event-ticket.model';
import th from '@mobiscroll/angular/dist/js/i18n/th';
@Component({
  selector: 'app-ticket-registeration',
  templateUrl: './ticket-registeration.component.html',
  styleUrls: ['./ticket-registeration.component.css']
})
export class TicketRegisterationComponent  implements OnInit{
  eventDetails: eventDetailsDTO;
  image:any;
  currentDateTime:any;
  eventDate:any;
    userId:any;
    tickets:EventTicket[];
    ticketId:any;
    totalPrice:any;
    price:any;
    quantity:any=1;
    eventId:any;
    availableTickets:any;
    userTicket:UserTicket=new UserTicket();
    constructor(private _activatedRoute:ActivatedRoute,private userService: UserService,
      private http: HttpClient,private apiService: ApiService, public datepipe: DatePipe) {
      this.tickets=[];
      // this.userService.UserId(this.getId);
      
      this.quantity=1;
      // console.log(this.quantity)
      // console.log(this.tickets[this.ticketId-1].price)
      // this.totalPrice=this.quantity*(this.tickets[this.ticketId-1].price);
    
  }
  // getId(id:any){
   
  //   console.log("userId"+id)
  //   this.userTicket.eventId=this.eventId;
  //   this.userTicket.quantity=this.quantity;
  //   this.userTicket.userId=id;
  //   this.userTicket.ticketId=this.ticketId;
  //   this.apiService.post("api/events/register", this.userTicket)
  //   .subscribe({
  //     next: response => {
        
  //     },
  //     error: error => { }
  //   }
  //   );
  // }
  @ViewChild('paypalRef',{static:true}) private paypalRef:ElementRef;
//   userTicket:UserTicket;
  ngOnInit() {
    this._activatedRoute.paramMap.subscribe((params) => {
      this.eventId = params.get('eventId');
      this.ticketId = params.get('ticketId');
      console.log("event id="+ this.eventId);
      console.log("user id="+  this.userId);
      console.log("ticket id="+  this.ticketId);
      this.getEvent( this.eventId);
      this.getTickets( this.eventId);
      
      
    });
   this.paypalService();
    console.log(window.paypal)
   
  // render({
  //   id: "#paypalBtn",
  //   currency: "USD",
  //   value: "100.00",
  //   onApprove: (details) => {
  //     alert("Transaction successful");
  //   }
  // });
}
paypalService(){
  paypal
  .Buttons({
    style: {
      layout: 'horizontal',
    },
    createOrder: (data: any, actions: any) => {

     
     
      // if(!validQuantity)
      return actions.order.create({
        purchase_units: [{
          amount: {
            value: this.totalPrice,
           
            currency_code: 'USD',
          },
       
        }],
      });
    },
    onApprove: async (data: any, actions: any) => {
      // Capture the transaction and verify its status
      const captureResult = await actions.order.capture();
      const transactionStatus = captureResult.status;

      if (transactionStatus === 'COMPLETED') {
        this.registerTicket();
        alert('Transaction successful');
        
      } else {
        alert('Transaction failed');
      }
    },
  })
  .render(this.paypalRef.nativeElement);

}
registerTicket() {
  this.setUserId();
  this.setUserTicket();
  console.log("hi")
  this.apiService.post("events/register", this.userTicket)
  .subscribe({
    next: response => {
      
    },
    error: error => { }
  }
  );

}
setUserId() {
  let email = this.userService.getuserEmail();

  this.http.post<any>("http://localhost:8888/user",{"email":email})
  .subscribe({
    next:response=>{
      console.log("->>>>>>"+response)
      this.userId=response;
      console.log("->>>>>>"+this.userId)
      
    },
    error:error=>{
     
    }
  }
  );
}
setUserTicket() {
  console.log("event idddd="+this.eventId)
  this.userTicket.eventId=this.eventId;
  this.userTicket.quantity=this.quantity;
  this.userTicket.userId=this.userId;
  this.userTicket.ticketId=this.ticketId;
 
}
getEvent(id:any): void {
   
  this.apiService.get("eventDetails/" + id)
    .subscribe({
      next: (response: eventDetailsDTO) => {
        this.eventDetails = response;
        console.log(response);
       
        this.eventDate=this.datepipe.transform(response.eventDate, 'yyyy-MM-dd h:mm:ss');
         
        console.log(this.eventDate);
    
      },
      error: (error: any) => { }
    }
    );

}
getTickets(id:any) {
  
  this.apiService.get("event/"+id+"/tickets")
  .subscribe({
    next:(response: EventTicket[])=>{
      this.tickets=response;
      this.totalPrice=this.quantity*(this.tickets[this.ticketId-1].price);
      console.log(response);
  },
  error:(error: any)=>{}
}
    );
 
}
changeTotalPrice(){
  if(this.quantity<1)
    this.quantity=1;
  else {
     this.getNumberOfAvailableTickets();
     console.log(  this.availableTickets)
    if(this.quantity> this.availableTickets) {
      this.quantity=  this.availableTickets;
    }
  }
  console.log(this.tickets[this.ticketId-1].price)
  this.totalPrice= this.quantity * (this.tickets[this.ticketId-1].price);
}
getNumberOfAvailableTickets():any{
  
  this.apiService.get("events/"+this.eventId+"/availableTickets/"+this.ticketId)
  .subscribe({
    next:(response: any)=>{
      
      console.log(response);
      this.availableTickets=response;
      
  },
  error:(error: any)=>{}
}
    );
    
    
}
}
//     // Initialize the registerForm with FormGroup and FormControls
//     this.registerForm = this._formBuilder.group({
      
//       userId:new FormControl(''),
//       quantity:new FormControl('')
      
     

//       // Add other form controls here
//     });
//   }
//   registerForm: FormGroup;
//   register(): void {
//     this.makePayment();
//     // var response1;
//     // this.userTicket = this.registerForm.value;
//     // console.log("->>>>>>>>>>>"+this.userTicket)
//     // this.userTicket.eventId=1;
//     // this.userTicket.ticketId=1;
//     // this.apiService.post("pay", this.userTicket)
//     //   .subscribe({
//     //     next: response => {
//     //       console.log(response)
//     //       response1=response;
//     //       // if(response==true)
//     //       //   this._router.navigateByUrl('/home');
//     //     },
//     //     error: error => { }
//     //   }
//     //   );
//       // this.apiService(response1)
//       // .subscribe({
//       //   next: response => {
//       //     console.log(response)
//       //     response1=response;
//       //     // if(response==true)
//       //     //   this._router.navigateByUrl('/home');
//       //   },
//       //   error: error => { }
//       // }
//       // );
//   }
//   makePayment() {
//      this.userTicket = this.registerForm.value;
//     console.log("->>>>>>>>>>>"+this.userTicket)
//     this.userTicket.eventId=1;
//     this.userTicket.ticketId=1;
//     this.apiService.post("pay", this.userTicket)
//       .subscribe({
//         next: response => {
//           console.log(response.redirect_url)
//           // this._router.navigateByUrl(response.redirect_url);
//           window.location.href=response.redirect_url;
//           // if(response==true)
//           //   this._router.navigateByUrl('/home');
//           // this.http.get<any>(response.redirect_url).subscribe({
//           //   next: response => {
//           //     console.log(response)
//           //   }
//           // });
//           },
        
         
          
       
//         error: error => { }
//       }
//       );
      
//   }
// }

