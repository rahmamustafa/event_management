import { MbscModule } from '@mobiscroll/angular';
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import { RouterModule } from '@angular/router';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

// import { MbscInputModule } from '@mobiscroll/angular';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { EventComponent } from './components/event/event.component';
import { HomeComponent } from './components/home/home.component';
import { HeaderComponent } from './shared/header/header.component';
import { NotFoundComponent } from './components/not-found/not-found.component';
import { FooterComponent } from './shared/footer/footer.component';
import { SharedComponent } from './shared/shared.component';
import { EventListComponent } from './components/event-list/event-list.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    EventComponent,
    HomeComponent,
    HeaderComponent,
    NotFoundComponent,
    FooterComponent,
    SharedComponent,
    EventListComponent
  ],
  imports: [  
    MbscModule,   
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    RouterModule,
    ReactiveFormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
