import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { RegisterService } from './register.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css'],
})
export class RegisterComponent implements OnInit {
  constructor(private Register: RegisterService, private route: Router) {}

  registerform = new FormGroup({
    userName: new FormControl('', Validators.required),
    emailId: new FormControl(
      '',
      Validators.pattern(
        '^[a-zA-Z0-9.!#$%&’+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:.[a-zA-Z0-9-]+)$'
      )
    ),
    password: new FormControl(
      '',
      Validators.pattern(
        '^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$'
      )
    ),
  });
  // (?=.*\d)
  ngOnInit(): void {}

  get userName() {
    return this.registerform.get('userName');
  }
  get password() {
    return this.registerform.get('password');
  }
  get emailId() {
    return this.registerform.get('emailId');
  }

  submitData() {
    this.Register.save(this.registerform.value).subscribe((response) => {
      console.log('Registeration is done: ' + response.userId);
      window.localStorage.setItem('genUserId', response.userId);

      Swal.fire({
        title:
          'User registered Successfully!!!' +
          '\n Your User Id is :' +
          response.userId,
        timer: 6000,
        icon: 'success',
        showConfirmButton: false,
      });
      this.registerform.reset();
      this.route.navigate(['/user-login']);
    });
  }

  checkMyUserId() {
    return window.localStorage.getItem('genUserId');
  }

  public showPassword: boolean = false;

  public togglePasswordVisibility(): void {
    this.showPassword = !this.showPassword;
  }
}
