import { Component } from '@angular/core';
import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Observable } from 'rxjs';
import { map, shareReplay } from 'rxjs/operators';
import { KanbanboardComponent } from '../kanbanboard/kanbanboard.component';

@Component({
  selector: 'app-navigationtool',
  templateUrl: './navigationtool.component.html',
  styleUrls: ['./navigationtool.component.css']
})
export class NavigationtoolComponent {

  isHandset$: Observable<boolean> = this.breakpointObserver.observe(Breakpoints.Handset)
    .pipe(
      map(result => result.matches),
      shareReplay()
    );

  constructor(private breakpointObserver: BreakpointObserver, private task:KanbanboardComponent) {}
      getAllTask(){
        this.task.getTask();
      }
}
