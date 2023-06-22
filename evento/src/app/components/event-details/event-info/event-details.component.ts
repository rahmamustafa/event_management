import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
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
  speakers : eventSpeakersDTO[];
  image:any;
   id: any;
  // speaker: any;
  // date:any;
  currentDateTime:any;
  eventDate:any;
  constructor(private route: ActivatedRoute, private apiService: ApiService,private sanitizer: DomSanitizer, public datepipe: DatePipe) {
    this.currentDateTime =this.datepipe.transform((new Date), 'yyyy-MM-dd h:mm:ss');
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
  
  getEvent(id:any): void {
   
        this.apiService.get("eventDetails/" + id)
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
              this.eventDate=this.datepipe.transform(response.eventDate, 'yyyy-MM-dd h:mm:ss');
               
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
        console.log(id+"!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        this.apiService.get("eventDetails/" + id + "/speakers")
          .subscribe({
            next: response => {
        if (response.speakers && response.speakers.length > 0) {

              response.speakers.forEach((eventSpeakersDTO: { image:any; }) => {
                const bytes = new Uint8Array(eventSpeakersDTO.image);
                const base64 = btoa(String.fromCharCode(...bytes));
                let objectURL = 'data:image/png;base64,' + base64;
                eventSpeakersDTO.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
              });

              console.log(response.speakers);
            }
          },
          error: error => { }
        });
      });
    }
  
  

  // getEventSpeakers(): void {
   
  //       this.apiService.get("eventDetails/ ${this.eventId} /speakers")
  //       .subscribe({
  //         next: response => {
  //           this.speakers = response;
  //           console.log(response);
  //           // const speaker= ;
  //           response.speakers.forEach((eventSpeakersDTO: { image:any; }) => {
  //             const bytes = new Uint8Array(eventSpeakersDTO.image);
  //             const base64 = btoa(String.fromCharCode(...bytes));
  //             let objectURL = 'data:image/png;base64,' + base64;
  //             eventSpeakersDTO.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
  //           });
    
  //           console.log(response.speakers);
           
  //         },
  //         error: error => { }
  //       });
  // }

  // getEventSpeakers(): void {
  //   this.route.paramMap
  //     .subscribe(parms => {
  //       let id = parms.get('id');
  //       console.log(id);
  //       this.apiService.get("eventDetails/" + id+ "/speakers")
  //         .subscribe({
  //           next: response => {
  //             response.speakers.forEach((eventSpeakersDTO: { image:any; }) => {
  //               const bytes = new Uint8Array(eventSpeakersDTO.image);
  //               const base64 = btoa(String.fromCharCode(...bytes));
  //               let objectURL = 'data:image/png;base64,' + base64;
  //               eventSpeakersDTO.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
  //             });
      
  //             console.log(response.speakers);
             
  //           },
  //           error: error => { }
  //         }
  //         );
  //     }
  //     );
  // }
  


}