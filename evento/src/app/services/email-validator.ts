import { AbstractControl, ValidationErrors } from "@angular/forms";
import { Injectable, OnInit } from "@angular/core";
import { ApiService } from "./api.service";

@Injectable({
    providedIn: 'root'
})

export class EmailValidator {
    constructor( private apiService:ApiService) { }
    isExist:boolean;
    validateEmail(email:string) :boolean{
      console.log(email);
      this.apiService.post("api/email/check",email)
      .subscribe({
        next:response=>{
          console.log("resp " + response)
          this.isExist =  response;
        },
        error:error=>{
          return null;
        }
      }
      );
      return this.isExist;
}
}