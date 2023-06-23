import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { EventComponent } from './components/event/event.component';
// import { EventDetailsComponent } from './event-details/event-details.component';
import { EventDetailsComponent } from './components/event-details/event-details.component';
import { SignupComponent } from './components/signup/signup.component';
import {EventListComponent} from './components/event-list/event-list.component'
import { NotFoundComponent } from './components/not-found/not-found.component';
import { AdminComponent } from './components/admin/admin.component';
import { AddEventComponent } from './components/admin/add-event/add-event.component';


const routes: Routes = [
  {path: '',
      children: [
 

  {path:'home',component:HomeComponent},
  {path:'event/:id',component:EventDetailsComponent},
  {path:'404',component:NotFoundComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'events',component:EventListComponent},
  {path:'admin',component:AdminComponent},
  {path:'add-event',component:AddEventComponent},
  {path:'',component:HomeComponent}

  ]}


];
// const routes: Routes = [
//   {path: '',
//       children: [
//   {path:'home',component:HomeComponent},
//   {path:'event',component:EventComponent},
//  ]},
//  {path:'',component:HomeComponent},

//     { path: 'login', component: LoginComponent },
//     { path: '**', redirectTo: '' }

// ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
