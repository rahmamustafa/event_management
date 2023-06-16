import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { EventComponent } from './components/event/event.component';
import { EventDetailsComponent } from './event-details/event-details.component';


const routes: Routes = [
  {path: '', 
      children: [
  {path:'home',component:HomeComponent},
  {path:'login',component:LoginComponent},
  {path:'event',component:EventComponent},
  {path:'',component:HomeComponent},
  {path:'event/1',component:EventDetailsComponent}
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
