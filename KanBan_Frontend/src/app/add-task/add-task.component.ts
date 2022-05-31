import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';
import { KanbanboardComponent } from '../kanbanboard/kanbanboard.component';
import { UserLoginComponent } from '../user-login/user-login.component';
import { AddTaskService } from './add-task.service';
import Swal from 'sweetalert2';

@Component({
  selector: 'app-add-task',
  templateUrl: './add-task.component.html',
  styleUrls: ['./add-task.component.css'],
})
export class AddTaskComponent implements OnInit {
  ngOnInit(): void {}
  constructor(
    public dialogRef: MatDialogRef<AddTaskComponent>,
    private crud: AddTaskService,
    private login: UserLoginComponent,
    private display: KanbanboardComponent
  ) {}
  closeDialog(): void {
    this.dialogRef.close();
  }

  boardform = new FormGroup({
    taskId: new FormControl('', Validators.required),
    taskTitle: new FormControl('', Validators.required),
    taskDesc: new FormControl('', Validators.required),
    taskDeadline: new FormControl(''),
    priority: new FormControl('', Validators.required),
    assignee: new FormControl('', Validators.required),
    status: new FormControl('', Validators.required),
  });

  userId: number = 0;

  get taskId() {
    return this.boardform.get('taskId');
  }
  get taskTitle() {
    return this.boardform.get('taskTitle');
  }
  get taskDesc() {
    return this.boardform.get('taskDesc');
  }
  get taskDeadline() {
    return this.boardform.get('taskDeadline');
  }
  get priority() {
    return this.boardform.get('priority');
  }
  get assignee() {
    return this.boardform.get('assignee');
  }
  get status() {
    return this.boardform.get('status');
  }
  minDate = new Date();
  count: number = 0;
  task() {
    this.crud
      .save({
        taskId: this.boardform.value.taskId,
        taskTitle: this.boardform.value.taskTitle,
        taskDesc: this.boardform.value.taskDesc,
        taskDeadline: this.boardform.value.taskDeadline,
        priority: this.boardform.value.priority,
        assignee: this.boardform.value.assignee,
        status: this.boardform.value.status,
        userId: this.login.checkUserId(),
        emailId: this.login.checkEmailId(),
      })
      .subscribe(
        (response) => {
          // console.log(this.display.getTask());
          this.count++;
          // window.localStorage.setItem("my",this.count);
          this.closeDialog();
        },
        (error) => {
          if (error.status == 409) {
            Swal.fire({
              title: 'Please enter a different Task ID',
              timer: 6000,
              icon: 'warning',
              showConfirmButton: false,
            });
          }
        }
      );
  }
}
