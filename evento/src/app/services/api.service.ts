import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})

export class ApiService {
  basic:string="http://localhost:8888";
  constructor(private _http:HttpClient) { }
  get(url:string){
    return this._http.get<any>(`${this.basic}/${url}`);
  }
  post(url:string,body:any){
    return  this._http.post<any>(`${this.basic}/${url}`,body)
  }
  delete(url:string,id:number){
   return this._http.delete<any>(`${this.basic}/${url}${id}`)
  }
}
