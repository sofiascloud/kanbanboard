import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserLoginService {

  constructor(private httpclient:HttpClient) { }
  url:string="http://localhost:5555/user/auth/login";
 

  save(data:any){
    return this.httpclient.post<any>(this.url,data); 
  }
}
