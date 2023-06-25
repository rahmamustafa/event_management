import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './components/home/home.component';
import { LoginComponent } from './components/login/login.component';
import { EventComponent } from './components/event/event.component';
import { EventDetailsComponent } from './components/event-details/event-details.component';
import { SignupComponent } from './components/signup/signup.component';
import {EventListComponent} from './components/event-list/event-list.component'
import { NotFoundComponent } from './components/not-found/not-found.component';
import { TicketRegisterationComponent } from './components/ticket-registeration/ticket-registeration.component';
import { AddEventComponent } from './components/admin/add-event/add-event.component';
import { AdminComponent } from './components/admin/admin.component';
import { TicketSucessComponent } from './components/ticket-registeration/ticket-sucess/ticket-sucess.component';
import { RevenueComponent } from './components/admin/revenue/revenue.component';
import { CalendarComponent } from './components/calendar/calendar.component';
import { UserUpComingEventsComponent } from './components/user-up-coming-events/user-up-coming-events.component';


 
const routes: Routes = [
   {path:'pay/success',component:TicketSucessComponent},
   {path:'MyEvents',component:UserUpComingEventsComponent},
  {path:'event/:eventId/register/:ticketId',component:TicketRegisterationComponent},
  {path:'404',component:NotFoundComponent},
  {path:'home',component:HomeComponent},
  {path:'event/:id',component:EventDetailsComponent},
  {path:'login',component:LoginComponent},
  {path:'signup',component:SignupComponent},
  {path:'events',component:EventListComponent},
  {path:'admin',component:AdminComponent},
  {path:'add-event',component:AddEventComponent},
  {path:'calendar',component:CalendarComponent},
  {path:'',component:HomeComponent},
  {path:'**',component:NotFoundComponent}


];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }

