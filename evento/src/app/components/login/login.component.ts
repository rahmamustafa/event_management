import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { User } from 'src/app/models/login/user';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  constructor(private apiService:ApiService,private _formBuilder: FormBuilder , private http: HttpClient){

  }
  selectedCountry :string;
  selectedImage: File
  countries: any;
  registerForm:FormGroup;
  

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
        // name: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
        // password: ['', [Validators.required, Validators.min(5),Validators.maxLength(20)]],
        // email: ['', [Validators.required, Validators.email]],
        // phone: ['', [Validators.required]], 
        // image: [null,[Validators.required]], 
        // country: ['',[Validators.required]], 
        // gender: ['',[Validators.required]], 
        // birthDate: [null,[Validators.required]] 
          name: [''],
        password: [''],
        email: [''],
        phone: [''], 
        country: [''], 
        gender: [''], 
        birthDate: ['']
      });

  }
  register(): void{
    this.registerForm.controls["country"].setValue(this.selectedCountry);
    const user = this.registerForm.value;
    // user.image = this.selectedImage;
    console.log(user)

    const userData = new FormData();
    userData.append('image', this.selectedImage);
    userData.append('user',JSON.stringify(user));
    this.apiService.post("users",userData)
    .subscribe({
      next:response=>{
        console.log(response)
      },
      error:error=>{}
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
  
}

