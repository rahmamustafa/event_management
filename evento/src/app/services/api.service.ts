import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { APIResponse } from '../models/api-response.model';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  constructor(private _http:HttpClient) { }
  get(url:string){
    return this._http.get<APIResponse>(`https://api.mohamed-sadek.com/${url}`);
  }
  post(url:string,body:any){
    return  this._http.post<APIResponse>(`https://api.mohamed-sadek.com/${url}`,body)
  }
  delete(url:string,id:number){
   return this._http.delete<APIResponse>(`https://api.mohamed-sadek.com/${url}${id}`)
  }
}
