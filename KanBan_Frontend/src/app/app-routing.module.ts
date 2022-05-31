import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomepageComponent } from './homepage/homepage.component';
import { KanbanboardComponent } from './kanbanboard/kanbanboard.component';
import { RegisterComponent } from './register/register.component'
import { SearchTaskComponent } from './search-task/search-task.component';
import { UserLoginComponent } from './user-login/user-login.component';

const routes: Routes = [
  {path:'', component:HomepageComponent},
  {path:'home', component:HomepageComponent},
  {path:'user-login', component:UserLoginComponent},
  {path:'register', component:RegisterComponent},
  {path:'board', component:KanbanboardComponent},
  {path:'search', component:SearchTaskComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
