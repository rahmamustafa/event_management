import { Component, Inject, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
// import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { EmailSubscriptionService } from 'src/app/services/email-subscription.service';

@Component({
  selector: 'app-subscription',
  templateUrl: './subscription.component.html',
  styleUrls: ['./subscription.component.css'],
  // providers: [MatSnackBar]

})
export class SubscriptionComponent implements OnInit {
  subscribeForm:FormGroup;

  constructor(private _router:Router,private _api:ApiService,private emailSubscriptionService: EmailSubscriptionService, private _form:FormBuilder){ }

  ngOnInit(): void {
    this.subscribeForm=this._form.group({
      Email:['',[Validators.required, Validators.email]],
    });
  }
  subscribe() {
    let email =this.subscribeForm.get("Email")?.value;
  

    this.emailSubscriptionService.subscribeEmail(email) 
      .subscribe({
      next:(response: any)=>{ 
        // this.snackBar.open('Success!', 'Close', {
        //   duration: 1000, 
        //   panelClass: ['success-snackbar'] 
        // });
        this.subscribeForm = this._form.group({
          Email: ''
        });
      },
      error:(error: any)=>{
        console.log('unsuccessful');
      }
    }
    );
     
  }


}
