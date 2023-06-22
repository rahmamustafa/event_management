// import { DatePipe } from '@angular/common';
// import { Component, OnInit } from '@angular/core';
// import { DomSanitizer } from '@angular/platform-browser';
// import { ActivatedRoute } from '@angular/router';
// import { eventSpeakersDTO } from 'src/app/models/event-speakers.model';
// import { ApiService } from 'src/app/services/api.service';
// import { EventDetailsComponent } from '../event-info/event-details.component';
// import { eventDetailsDTO } from 'src/app/models/event-details.model';

// @Component({
//   selector: 'app-event-speakers',
//   templateUrl: './event-speakers.component.html',
//   styleUrls: ['./event-speakers.component.css']
// })
// export class EventSpeakersComponent implements OnInit{

//   speakers : eventSpeakersDTO[];
//   image:any;
//   id :any;
//   eventId: any;
//   // speaker: any;
//   // date:any;
//   currentDateTime:any;
//   eventDate:any;
//   constructor(private route: ActivatedRoute, private apiService: ApiService,private event: eventDetailsDTO, private sanitizer: DomSanitizer, public datepipe: DatePipe) {
//     this.currentDateTime =this.datepipe.transform((new Date), 'yyyy-MM-dd h:mm:ss');
//     console.log(this.currentDateTime);
//    }


//   ngOnInit(): void {
//     this.route.paramMap.subscribe((params) => {
//       const eventId = params.get('id');
//       console.log(eventId);
//       this.getEventSpeakers();
//       this.eventId = this.event.id;
//     });

//   }

//   getEventSpeakers(): void {
//     this.route.paramMap
//       .subscribe(parms => {
//         let id = parms.get('id');
//         console.log(id);
//         this.apiService.get("eventDetails/" + this.eventId + "/speakers")
//           .subscribe({
//             next: response => {
//               response.speakers.forEach((eventSpeakersDTO: { image:any; }) => {
//                 const bytes = new Uint8Array(eventSpeakersDTO.image);
//                 const base64 = btoa(String.fromCharCode(...bytes));
//                 let objectURL = 'data:image/png;base64,' + base64;
//                 eventSpeakersDTO.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
//               });
      
//               console.log(response.speakers);
             
//             },
//             error: error => { }
//           }
//           );
//       }
//       );
//   }
  



// }
