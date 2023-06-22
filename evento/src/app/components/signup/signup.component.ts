import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { EmailValidator } from 'src/app/services/email-validator';

@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css']
})

export class SignupComponent implements OnInit {

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
    this.http.get('https://trial.mobiscroll.com/content/countries.json').subscribe((resp: any) => {
      const countries = [];
      for (let i = 0; i < resp.length; ++i) {
        const country = resp[i];
        countries.push({ text: country.text, value: country.value });
      }
      this.countries = countries;
    });
    this.registerForm = this._formBuilder.group({
      name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      password: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      email: ['', [Validators.required, Validators.email]],
      phone: ['', [Validators.required, Validators.pattern("^[0][1][0125][0-9]{8}$")]],
      image: ['', [Validators.required]],
      country: ['', [Validators.required]],
      gender: ['', [Validators.required]],
      birthDate: ['', [Validators.required]]
    });

  }
  register(): void {
    this.registerForm.controls["country"].setValue(this.selectedCountry);
    const user = this.registerForm.value;
    console.log(user)

    const userData = new FormData();
    userData.append('image', this.selectedImage);
    userData.append('user', JSON.stringify(user));
    this.apiService.post("auth/register", userData)
      .subscribe({
        next: response => {
          console.log(response)
          this._router.navigateByUrl('/home');

        },
        error: error => { }
      }
      );
  }

  onChangeCountry(event: any) {
    this.selectedCountry = event.valueText;
    console.log('Selected Country:', event.valueText);
  }
  onImageSelected(event: any) {
    this.selectedImage = event.target.files[0];
  }

  validateEmail(){
    console.log(this.email);
    this.apiService.post("auth/email/check",this.email)
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



