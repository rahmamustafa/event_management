import { Component, ElementRef, Inject, OnInit, ViewChild } from '@angular/core';
import { speaker } from 'src/app/models/home-models/speaker.model';
import { ApiService } from 'src/app/services/api.service';
import { DomSanitizer } from '@angular/platform-browser';
import { DatePipe } from '@angular/common';
import { recommendationDto } from 'src/app/models/home-models/recommendationDto';
import { LoginComponent } from '../../login/login.component';


@Component({
  selector: 'app-speakers',
  templateUrl: './speakers.component.html',
  styleUrls: ['./speakers.component.css']
})
export class SpeakersComponent implements OnInit {
  
  hola: LoginComponent ;
  speakers: speaker[] = [];
  recommendedEvents: recommendationDto[]=[];
  userId: any;
  image: any;
  currentDateTime: string | null;
  // hola.isUserLoggedIn();
  
 

  constructor(private apiService: ApiService, private sanitizer: DomSanitizer, public datepipe: DatePipe) {
    this.currentDateTime = this.datepipe.transform((new Date), 'yyyy-MM-dd h:mm:ss');
    console.log(this.currentDateTime);
    
  }

  ngOnInit(): void {
    this.apiService.get(`speakers/most-speaking`)
      .subscribe({
        next: response => {
          console.log(response);
          this.speakers = response;
          console.log(this.speakers);
        },
        error: error => {
          console.log("error->" + error);
        }
      }
      );
    this.getEventSpeakers();
    this.isLoggedIn();
  }
  getEventSpeakers(): void {
    this.apiService.get("recommendation/" + 1)
      .subscribe({
        next: response => {
          console.log(response + "?????????????????????????????????????")
          this.recommendedEvents = response;
          console.log(this.recommendedEvents);
          //   if (response.speakers && response.speakers.length > 0) {

          //     response.speakers.forEach((speaker: { image: any; }) => {
          //       const bytes = new Uint8Array(speaker.image);
          //       const base64 = btoa(String.fromCharCode(...bytes));
          //       let objectURL = 'data:image/png;base64,' + base64;
          //       speaker.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
          //       console.log(this.image);
          //     });

          //     console.log(response.speakers);
          //   }
          // },
          // error: error => { }

        }
      });
    }
  isLoggedIn(): boolean {
   return this.hola.isUserLoggedIn();
}

}
