import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { EmailValidator } from 'src/app/services/email-validator';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})

export class LoginComponent implements OnInit {

  constructor(private apiService: ApiService, private _formBuilder: FormBuilder

    , private http: HttpClient, private emailValidator: EmailValidator, private _router: Router) {

  }
  selectedCountry: string;
  selectedImage: File
  email: string;
  emailExists: boolean;
  countries: any;
  registerForm: FormGroup;


  ngOnInit(): void {
   
    this.registerForm = this._formBuilder.group({
      password: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email]],
   
    });

  }
  login(): void {
    const user = this.registerForm.value;
    console.log(user)

    this.apiService.post("api/users/login", user)
      .subscribe({
        next: response => {
          console.log(response)
          this._router.navigateByUrl('/home');
        },
        error: error => { }
      }
      );
  }


  validateEmail(){
    console.log(this.email);
    this.apiService.post("api/login/check",this.email)
    .subscribe({
      next:response=>{
        console.log("resp " + response)
        this.emailExists=response
      },
      error:error=>{
        return null;
      }
    }
    );
}

  // validateEmail() {
  //   this.emailExists = this.emailValidator.validateEmail(this.email);

  //  }
}



