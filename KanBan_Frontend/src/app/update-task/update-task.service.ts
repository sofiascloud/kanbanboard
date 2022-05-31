import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserLoginComponent } from '../user-login/user-login.component';

@Injectable({
  providedIn: 'root'
})
export class UpdateTaskService {

  constructor(private httpclient:HttpClient, private login:UserLoginComponent) { }
  Url:string="http://localhost:5555/task/v2";
  getUrl: string = 'http://localhost:5555/task/v2/card/';

  reqHeader = new HttpHeaders().set(
    'Authorization',
    'Bearer ' + this.login.checkToken()
  );

  update(data:any){
    return this.httpclient.put<any>(this.Url,data,{
      headers: this.reqHeader,
    });
  }
  getByTaskId(taskId: any) {
    return this.httpclient.get<any>(this.getUrl+ taskId, {
      headers: this.reqHeader,
    });
  }
}
