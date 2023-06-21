import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { EventComponent } from './components/event/event.component';
import { EventListComponent } from './components/event-list/event-list.component';


const routes: Routes = [
  {
    path: '',
    children: [
      { path: 'home', component: HomeComponent },
      { path: 'login', component: LoginComponent },
      { path: 'event', component: EventComponent },
      { path: 'eventList', component: EventListComponent },
      { path: '', component: HomeComponent },
    ]
  }
// import { EventDetailsComponent } from './event-details/event-details.component';
import { EventDetailsComponent } from './components/event-details/event-details.component';
import { SignupComponent } from './components/signup/signup.component';


const routes: Routes = [
  {path: '', 
      children: [
  {path:'eventDetails/:id',component:EventDetailsComponent},
  
  {path:'home',component:HomeComponent},
  {path:'event/:id',component:EventDetailsComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'event',component:EventComponent},
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
