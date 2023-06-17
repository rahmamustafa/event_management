import { AbstractControl, ValidationErrors } from "@angular/forms";
import { Injectable, OnInit } from "@angular/core";
import { ApiService } from "./api.service";

@Injectable({
    providedIn: 'root'
})

export class EmailValidator {
    constructor() { }

     validate(control: AbstractControl , apiService: ApiService) : ValidationErrors | null{
        let isValid:boolean = false;
        apiService.post("api/email/check",control.value)
        .subscribe({
          next:response=>{
            console.log("resp " + response)
            isValid=response
          },
          error:error=>{
            return null;
          }
        }
        );
        return isValid ? null : { 'myCustomError': 'This value is invalid' };
    }
}