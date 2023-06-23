import { HttpClient } from '@angular/common/http';
import { Component, ElementRef, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { Router } from '@angular/router';
import { UserTicket } from 'src/app/models/event-registration/user-ticket.model';
import { ApiService } from 'src/app/services/api.service';
import { render } from 'creditcardpayments/creditCardPayments';
@Component({
  selector: 'app-ticket-registeration',
  templateUrl: './ticket-registeration.component.html',
  styleUrls: ['./ticket-registeration.component.css']
})
export class TicketRegisterationComponent  implements OnInit{
  
    
    constructor() {
    
  }
  @ViewChild('paypalRef',{static:true}) private paypalRef:ElementRef;
//   userTicket:UserTicket;
  ngOnInit() {
    console.log(window.paypal)
    paypal
      .Buttons({
        style: {
          layout: 'horizontal',
        },
        createOrder: (data: any, actions: any) => {
          return actions.order.create({
            purchase_units: [{
              amount: {
                value: '100.00',
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
            alert('Transaction successful');
          } else {
            alert('Transaction failed');
          }
        },
      })
      .render(this.paypalRef.nativeElement);
  
  // render({
  //   id: "#paypalBtn",
  //   currency: "USD",
  //   value: "100.00",
  //   onApprove: (details) => {
  //     alert("Transaction successful");
  //   }
  // });
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

