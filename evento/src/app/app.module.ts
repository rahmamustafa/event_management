import { MbscModule } from '@mobiscroll/angular';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
import { MatSnackBarModule } from '@angular/material/snack-bar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';


import { MbscInputModule } from '@mobiscroll/angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { SignupComponent } from './components/signup/signup.component';
import { EventComponent } from './components/event/event.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './shared/header/header.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { FooterComponent } from './shared/footer/footer.component';
import { SharedComponent } from './shared/shared.component';
import { EventDetailsComponent } from './components/event-details/event-details.component';
import { EventReviewsComponent } from './components/event-details/event-reviews/event-reviews.component';
import { EventTicketsComponent } from './components/event-details/event-tickets/event-tickets.component';
import { SubscriptionComponent } from './components/home/subscription/subscription.component';
import { ScheduleComponent } from './components/home/schedule/schedule.component';
import { SpeakersComponent } from './components/home/speakers/speakers.component';
import { NewEventsComponent } from './components/home/new-events/new-events.component';
import {EventListComponent} from './components/event-list/event-list.component';
import { TicketRegisterationComponent } from './components/ticket-registeration/ticket-registeration.component'
import { AdminComponent } from './components/admin/admin.component';
import { AddEventComponent } from './components/admin/add-event/add-event.component';
import { SideBarComponent } from './components/admin/side-bar/side-bar.component'
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatNativeDateModule } from '@angular/material/core';
import { MatMomentDatetimeModule } from '@mat-datetimepicker/moment';
import { MatDatetimepickerModule } from '@mat-datetimepicker/core';
import { CarouselModule } from 'primeng/carousel';

import { Router } from '@angular/router';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

import { ReviewFormComponent } from './components/event-details/event-reviews/review-form/review-form.component';
import { TicketSucessComponent } from './components/ticket-registeration/ticket-sucess/ticket-sucess.component';
import { GoogleMapsModule } from '@angular/google-maps'
import { NgChartsModule } from 'ng2-charts';

import { UserService } from './services/user.service';
import { EventSpeakersComponent } from './components/event-details/event-speakers/event-speakers.component';
import { AuthService } from './services/auth.service';
import { RevenueComponent } from './components/admin/revenue/revenue.component';
@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    SignupComponent,
    EventComponent,
    HomeComponent,
    HeaderComponent,
    NotFoundComponent,
    FooterComponent,
    SharedComponent,
    EventDetailsComponent,
    EventReviewsComponent,
    EventTicketsComponent,
    SubscriptionComponent,
    ScheduleComponent,
    EventReviewsComponent,
    SpeakersComponent,
    NewEventsComponent,
    EventListComponent,
    TicketRegisterationComponent,
    AdminComponent,
    AddEventComponent,
    SideBarComponent,
    ReviewFormComponent,
    TicketSucessComponent,
    EventSpeakersComponent,
    RevenueComponent
  ],
  imports: [
    MbscModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule,
    MatSnackBarModule,
    DatePipe,
    MbscInputModule,
    BrowserAnimationsModule,
    MatDatepickerModule,
    MatInputModule,
    MatNativeDateModule,
    MatMomentDatetimeModule,
    MatDatetimepickerModule,
    GoogleMapsModule,
    NgbModule,
    CarouselModule,
    NgChartsModule
  ],
  providers: [DatePipe,UserService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthService,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
