import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

import { ActivatedRoute } from '@angular/router';
import { eventDetailsDTO } from 'src/app/models/event-details.model';
import { eventSpeakersDTO } from 'src/app/models/event-speakers.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})

export class EventDetailsComponent implements OnInit {
  eventDetails: eventDetailsDTO;
  speakers: eventSpeakersDTO[];
  imageObject = [{
    image: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/5.jpg',
    thumbImage: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/5.jpg',
    title: 'Hummingbirds are amazing creatures'
}, {
    image: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/9.jpg',
    thumbImage: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/9.jpg'
}, {
    image: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/4.jpg',
    thumbImage: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/4.jpg',
    title: 'Example with title.'
},{
    image: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/7.jpg',
    thumbImage: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/7.jpg',
    title: 'Hummingbirds are amazing creatures'
}, {
    image: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/1.jpg',
    thumbImage: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/1.jpg'
}, {
    image: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/2.jpg',
    thumbImage: 'https://sanjayv.github.io/ng-image-slider/contents/assets/img/slider/2.jpg',
    title: 'Example two with title.'
}];
  image: any;
  id: any;
  currentDateTime: any;
  eventDate: any;
  constructor(private route: ActivatedRoute, private apiService: ApiService, private sanitizer: DomSanitizer, public datepipe: DatePipe) {
    this.currentDateTime = this.datepipe.transform((new Date), 'yyyy-MM-dd h:mm:ss');
    console.log(this.currentDateTime);
  }
  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      this.id = params.get('id');
      console.log(this.id);
      this.getEvent(this.id);
      this.getEventSpeakers();
    });
  }

  getEvent(id: any): void {

    this.apiService.get("event/"+ id+"/eventDetails")
      .subscribe({
        next: response => {
          this.eventDetails = response;
          console.log(response);
          const bytes = new Uint8Array(this.eventDetails.image);
          const base64 = btoa(String.fromCharCode(...bytes));
          let objectURL = 'data:image/png;base64,' + this.eventDetails.image;
          this.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
          // this.image = `data:image/jpg;base64,${base64}`;
          console.log(this.image);
          this.eventDate = this.datepipe.transform(response.eventDate, 'yyyy-MM-dd h:mm:ss');

          console.log(this.eventDate);

        },
        error: error => { }
      }
      );
  }
  getEventSpeakers(): void {
    this.route.paramMap
      .subscribe(
        parms => {
          let id = parms.get('id');
          console.log(id + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
          this.apiService.get("event/"+ id +"/speakers")
            .subscribe({
              next: response => {
                console.log(response + "??????????????????????????????????????")
                this.speakers = response;
                console.log(this.speakers);
                if (response.speakers && response.speakers.length > 0) {

                  response.speakers.forEach((speaker: { image: any; }) => {
                    const bytes = new Uint8Array(speaker.image);
                    const base64 = btoa(String.fromCharCode(...bytes));
                    let objectURL = 'data:image/png;base64,' + base64;
                    speaker.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
                  });

                  console.log(response.speakers);
                }
              },
              error: error => { }
            });
        });
  }
}

