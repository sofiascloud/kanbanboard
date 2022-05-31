import { Component, OnInit } from '@angular/core';
import { KanbanboardComponent } from '../kanbanboard/kanbanboard.component';
import { UserLoginComponent } from '../user-login/user-login.component';
import { SearchTaskService } from './search-task.service';

@Component({
  selector: 'app-search-task',
  templateUrl: './search-task.component.html',
  styleUrls: ['./search-task.component.css']
})
export class SearchTaskComponent implements OnInit {

  Bike:any;
  boards: any[];
 
  constructor(private kanban:KanbanboardComponent, private search:SearchTaskService, private userId:UserLoginComponent) {}
  
  ngOnInit() {
    this.boards = [];
    this.getTask();
  }
 
  Id:string=this.userId.checkUserId();
  data:any;
  
  getTask(){
    this.search.get(this.Id).subscribe(response=>{
      console.log(response);
      this.data=response;
    this.sorting(response);
      
  });
}
searchData:any;
searchBy:string;
storeTask:any=[];
i: number = 0;
  sorting(data: any) {
    this.searchData=window.localStorage.getItem("search");
    console.log(this.searchData);
    for (this.i; this.i < data.length; this.i++) {
      if (data[this.i].taskTitle === this.searchData) {
        this.storeTask.push(data[this.i]);
      }
      if (data[this.i].priority === this.searchData) {
        this.storeTask.push(data[this.i]);
      }
      if (data[this.i].taskDeadline === this.searchData) {
        this.storeTask.push(data[this.i]);
      }
      if (data[this.i].status === this.searchData) {
        this.storeTask.push(data[this.i]);
      }
    }
    this.data=null;
    this.data=this.storeTask;
    console.log(this.storeTask);
    
  }

}
