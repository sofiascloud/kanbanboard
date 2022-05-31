import { Component, ɵɵqueryRefresh } from '@angular/core';
import { map } from 'rxjs/operators';
import { Breakpoints, BreakpointObserver } from '@angular/cdk/layout';
import { AddTaskComponent } from '../add-task/add-task.component';
import { MatDialog } from '@angular/material/dialog';
import { KanbanboardService } from './kanbanboard.service';
import { UserLoginComponent } from '../user-login/user-login.component';
import { UpdateTaskComponent } from '../update-task/update-task.component';
import { CdkDragDrop, moveItemInArray, transferArrayItem,} from '@angular/cdk/drag-drop';
import Swal from 'sweetalert2';
import { Router } from '@angular/router';

@Component({
  selector: 'app-kanbanboard',
  templateUrl: './kanbanboard.component.html',
  styleUrls: ['./kanbanboard.component.css'],
})
export class KanbanboardComponent {
  constructor(
    private breakpointObserver: BreakpointObserver,
    public dialog: MatDialog,
    private board: KanbanboardService,
    private userId: UserLoginComponent,
    private route: Router
  ) {}
  openDialogSave(): void {
    const dialogRef = this.dialog.open(AddTaskComponent, {
      width: '300px',
      height: '800px',
    });

    dialogRef.afterClosed().subscribe((result) => {
      Swal.fire({
        title: 'Task Added',
        text: '',
        timer: 4000,
        icon: 'success',
        showConfirmButton: false,
      });
      this.getTask();
    });
  }
  tId: any;
  openDialogUpdate(id: any): void {
    const dialogRef = this.dialog.open(UpdateTaskComponent, {
      width: '300px',
    });
    this.tId = id;
    console.log(this.tId);
    window.localStorage.setItem('mytaskid', this.tId);

    dialogRef.afterClosed().subscribe((result) => {
      Swal.fire({
        title: 'Task Updated',
        timer: 4000,
        icon: 'success',
        showConfirmButton: false,
      });
      window.location.reload();
    });
  }

  Id: string = this.userId.checkUserId();
  emailId:string
  

  
  ngOnInit() {
    this.getTask();

    this.emailId=this.userId.checkEmailId();
  }

  data: any;
  index: any;
  count: number = 0;
  s: number = 0;

  //for Drag and Drop
  todoItems: any = [];
  inProgressItems: any = [];
  completedItems: any = [];

  getTask() {
    this.board.get(this.Id).subscribe(
      (response) => {
        console.log(response.length);
        this.data = response;
        this.sorting(response);
        //check length for car limitation
        this.s = response.length;
      },
      (error) => {
        Swal.fire({
          title: 'No Task for this user',
          text: 'Please add task to see them on the board',
          timer: 4000,
          icon: 'error',
          showConfirmButton: false,
        });
      }
    );
  }

  //sorting on the basis of status
  i: number = 0;
  sorting(data: any) {
    for (this.i; this.i < data.length; this.i++) {
      if (data[this.i].status === 'To Do') {
        this.todoItems.push(data[this.i]);
      }
      if (data[this.i].status === 'In Progress') {
        this.inProgressItems.push(data[this.i]);
      }
      if (data[this.i].status === 'Completed') {
        this.completedItems.push(data[this.i]);
      }
    }
    console.log(this.todoItems);
    console.log(this.inProgressItems);
    console.log(this.completedItems);
  }

  deleteTask(task: any) {
    this.board.delete(task).subscribe((response) => {
      Swal.fire({
        title: 'Task Deleted',
        timer: 6000,
        icon: 'success',
        showConfirmButton: false,
      });

      window.location.reload();
      this.getTask();
    });
  }

  //function for Drag and drop
  task: any;
  drop(event: CdkDragDrop<string[]>) {
    if (event.previousContainer === event.container) {
      // for changing cards inside the lane
      moveItemInArray(
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
    // for changing cards in between the lanes
    else {
      console.log(event.container.id); //droping lane status
      let containername = event.container.id; //droping lane status

      console.log(event.previousContainer.data[event.currentIndex]); //task object
      this.task = event.previousContainer.data[event.currentIndex]; //task object

      this.task.status = containername;
      console.log(this.task);

      this.updatestat(this.task);

      transferArrayItem(
        event.previousContainer.data,
        event.container.data,
        event.previousIndex,
        event.currentIndex
      );
    }
  }

  updatestat(data: any) {
    this.board.update(data).subscribe((response) => {
      Swal.fire({
        title: 'Task updated',
        timer: 6000,
        icon: 'success',
        showConfirmButton: false,
      });
      window.location.reload();
      this.getTask();
    });
  }

  //capture search bar texts
  searchBy: string;
  searchTask() {
    window.localStorage.setItem('search', this.searchBy);
    this.route.navigate(['/search']);
    console.log(this.searchBy);
    return window.localStorage.getItem('search');
  }
}
