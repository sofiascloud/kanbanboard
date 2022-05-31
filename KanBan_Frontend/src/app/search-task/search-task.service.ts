import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLoginComponent } from '../user-login/user-login.component';

@Injectable({
  providedIn: 'root'
})
export class SearchTaskService {

  constructor(private httpclient:HttpClient, private login:UserLoginComponent) { }
  Url:string="http://localhost:5555/task/v2";

 
  reqHeader = new HttpHeaders().set(
    'Authorization',
    'Bearer ' + this.login.checkToken()
  );

  get(id:any){
    return this.httpclient.get<any>(this.Url+"/"+id,{
      headers: this.reqHeader,
    });
  }
}
