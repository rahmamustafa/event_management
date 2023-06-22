import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { EventComponent } from './components/event/event.component';
// import { EventDetailsComponent } from './event-details/event-details.component';
import { EventDetailsComponent } from './components/event-details/event-info/event-details.component';
import { SignupComponent } from './components/signup/signup.component';
import {EventListComponent} from './components/event-list/event-list.component'
import { NotFoundComponent } from './components/not-found/not-found.component';
import { DatePipe } from '@angular/common';
// import { EventSpeakersComponent } from './components/event-details/event-speakers/event-speakers.component';


const routes: Routes = [
  {path: '',
      children: [
 

  {path:'home',component:HomeComponent},
  // {path:'event-details/:id/speakers', component: EventSpeakersComponent},
  { path: 'event-details/:id', component: EventDetailsComponent },
  {path:'404',component:NotFoundComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'events',component:EventListComponent},
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
  exports: [RouterModule],
  providers: [DatePipe]
})
export class AppRoutingModule { }
