import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { UserLoginComponent } from '../user-login/user-login.component';
import { UpdateTaskService } from './update-task.service';

@Component({
  selector: 'app-update-task',
  templateUrl: './update-task.component.html',
  styleUrls: ['./update-task.component.css']
})
export class UpdateTaskComponent implements OnInit {

  ngOnInit(): void {
   this.getTaskForPrefill();
  }
  constructor(public dialogRef: MatDialogRef<UpdateTaskComponent>, private crud:UpdateTaskService,
    private login:UserLoginComponent) {}
  save(): void {
    this.dialogRef.close();
  }
  
  boardform=new FormGroup({
    taskId:new FormControl('',Validators.required),
    taskTitle:new FormControl('',Validators.required),
    taskDesc:new FormControl('',Validators.required),
    taskDeadline:new FormControl(''),
    priority:new FormControl('',Validators.required),
    assignee:new FormControl('',Validators.required),
    status:new FormControl('',Validators.required)
  });
  userId:number=0;
  

  get taskId(){
    return this.boardform.get('taskId');
  }
  get taskTitle(){
    return this.boardform.get('taskTitle');
  }
  get taskDesc(){
    return this.boardform.get('taskDesc');
  }
  get taskDeadline(){
    return this.boardform.get('taskDeadline');
  }
  get priority(){
    return this.boardform.get('priority');
  }
  get assignee(){
    return this.boardform.get('assignee');
  }
  get status(){
    return this.boardform.get('status');
  }
  task(){
    this.crud.update({taskId:this.boardform.value.taskId,taskTitle:this.boardform.value.taskTitle,taskDesc:this.boardform.value.taskDesc,
    taskDeadline:this.boardform.value.taskDeadline,priority:this.boardform.value.priority,assignee:this.boardform.value.assignee,status:this.boardform.value.status,
  userId:this.login.checkUserId(),emailId:this.login.checkEmailId()}).subscribe(response=>{
    console.log(response);
   this.save();
  });
  }
  minDate = new Date();
  //helps in populating the form in prefill
  prefillData: any;
  //save taskID to get the complete object from backend
  prefillId: any;
  getTaskForPrefill() {
    this.prefillId = window.localStorage.getItem('mytaskid');
    this.crud.getByTaskId(this.prefillId).subscribe((response) => {
      console.log(
        response.taskId,
        response.taskTitle,
        response.taskDesc,
        response.taskDeadline,
        response.priority,
        response.status
      );
      this.prefillData = response;
    });
  }
}
