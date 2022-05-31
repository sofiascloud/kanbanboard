import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserLoginService } from './user-login.service';
import Swal from 'sweetalert2'; 

@Component({
  selector: 'app-user-login',
  templateUrl: './user-login.component.html',
  styleUrls: ['./user-login.component.css']
})
export class UserLoginComponent implements OnInit {

  constructor(private Login:UserLoginService, private route:Router) { }

  ngOnInit(): void {
  }

  loginform=new FormGroup({
    userId:new FormControl('',Validators.required),
    emailId:new FormControl('',Validators.pattern("^[a-zA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)$")),
    password:new FormControl('',Validators.pattern("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$"))
  });
  get userId(){
    return this.loginform.get('userId');
  }
  get emailId(){
    return this.loginform.get('emailId');
  }
  get password(){
    return this.loginform.get('password');
  }
  data:any;
  
  login(){
    this.Login.save(this.loginform.value).subscribe( response=>{
      console.log('response : ' + response.token,this.loginform.value.emailId,this.loginform.value.userId);
      window.localStorage.setItem("loginEmail",this.loginform.value.emailId);
      window.localStorage.setItem("loginUserId",this.loginform.value.userId);
      this.data=response.token;
      window.localStorage.setItem('mykey', response.token);
      console.log(response)
      this.loginform.reset();
    
      Swal.fire({ title:'User login Success', timer:4000,icon:'success',showConfirmButton:false})

      if(this.data!=null){
        this.route.navigate(['/board']);
      }
    },
    (error)=>{
      Swal.fire({ title:'User Login Failed', text:'Enter Correct Email Id and Password',
       timer:5000,icon:"error",showConfirmButton:false})
    }
    )

  }
  checkKey: any;
checkToken() {
  this.checkKey = localStorage.getItem('mykey');
  console.log(this.checkKey);
  return this.checkKey;
}
checkEmailId(){
  return window.localStorage.getItem("loginEmail");
}
checkUserId(){
  return window.localStorage.getItem("loginUserId");
}
public showPassword: boolean = false;
  
  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
}
