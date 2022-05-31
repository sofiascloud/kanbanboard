import { HttpClient, HttpHeaders, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLoginComponent } from '../user-login/user-login.component';

@Injectable({
  providedIn: 'root'
})
export class KanbanboardService {

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
  
  delete(data:any){
    return this.httpclient.delete<any>(this.Url,{
      headers: this.reqHeader,body:data
    });
  }
  
  url: string = 'http://localhost:5555/task/v2';
  update(data: any) {
    return this.httpclient.put<any>(this.Url, data, {
      headers: this.reqHeader,
    });
  }
  
  
}
