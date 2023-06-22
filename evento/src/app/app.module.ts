import { MbscModule } from '@mobiscroll/angular';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { DatePipe } from '@angular/common';
// import { MatSnackBarModule } from '@angular/material/snack-bar';
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
// import { EventDetailsComponent } from './event-details/event-details.component';
import { SharedComponent } from './shared/shared.component';
import { EventDetailsComponent } from './components/event-details/event-info/event-details.component';
import { EventReviewsComponent } from './components/event-details/event-reviews/event-reviews.component';
import { EventTicketsComponent } from './components/event-details/event-tickets/event-tickets.component';
import { SubscriptionComponent } from './components/home/subscription/subscription.component';
import { ScheduleComponent } from './components/home/schedule/schedule.component';
import { SpeakersComponent } from './components/home/speakers/speakers.component';
import { NewEventsComponent } from './components/home/new-events/new-events.component';
import {EventListComponent} from './components/event-list/event-list.component';
// import { EventSpeakersComponent } from './components/event-details/event-speakers/event-speakers.component'

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
    // EventDetailsComponent,
    SharedComponent,
    EventDetailsComponent,
    EventReviewsComponent,
    EventTicketsComponent,
    SubscriptionComponent,
    ScheduleComponent,
    EventReviewsComponent,
    SpeakersComponent,
    NewEventsComponent,
    EventListComponent
    // EventSpeakersComponent
  ],
  imports: [
    MbscModule,
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule,
    // MatSnackBarModule,
    DatePipe,
    MbscInputModule,
    BrowserAnimationsModule
  ],
  providers: [DatePipe],
  bootstrap: [AppComponent]
})
export class AppModule { }
