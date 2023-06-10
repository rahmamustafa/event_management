import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { APIResponse } from '../models/api-response.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  basic:string="https://basic_url";
  constructor(private _http:HttpClient) { }
  get(url:string){
    return this._http.get<APIResponse>(`${this.basic}/${url}`);
  }
  post(url:string,body:any){
    return  this._http.post<APIResponse>(`${this.basic}/${url}`,body)
  }
  delete(url:string,id:number){
   return this._http.delete<APIResponse>(`${this.basic}/${url}${id}`)
  }
}
