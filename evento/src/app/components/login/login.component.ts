import { HttpClient } from '@angular/common/http';
import { Component } from '@angular/core';
import { OnInit } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  
  constructor(private apiService:ApiService , private http: HttpClient){

  }
  selectedCountry :string
  countries: any;

  ngOnInit(): void {
      this.http.get('https://trial.mobiscroll.com/content/countries.json').subscribe((resp: any) => {
          const countries = [];
          for (let i = 0; i < resp.length; ++i) {
              const country = resp[i];
              countries.push({ text: country.text, value: country.value });
          }
          this.countries = countries;
      });
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

  onChangeCountry(event: any) {
    this.selectedCountry = event.valueText;
    console.log('Selected Country:', event.valueText);
  }
  
}

