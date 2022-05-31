import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {

  constructor(private httpclient:HttpClient) { }
  registerUrlPost:string="http://localhost:5555/user/auth/register";
  registerUrlGet:string="http://localhost:5555/user/auth/";

  save(data:any){
    return this.httpclient.post<any>(this.registerUrlPost,data); 
  }
  get(){
    return this.httpclient.get<any>(this.registerUrlGet); 
  }
}
