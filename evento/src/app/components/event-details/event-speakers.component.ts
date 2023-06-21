// import { Component, OnInit } from '@angular/core';
// import { ApiService } from '../services/api.service';
// import { ActivatedRoute } from '@angular/router';
// import { eventSpeakersDTO } from '../models/event-speakers.model';


// @Component({
//   selector: 'app-event-details',
//   templateUrl: '../event-details/event-details.component.html',
//   styleUrls: ['../event-details/event-details.component.css']
// })
// export class EventSpeakersComponent implements OnInit {
//   speakers: eventSpeakersDTO;
//   constructor(private route: ActivatedRoute, private apiService: ApiService) { }

//   ngOnInit(): void {
//     this.route.paramMap.subscribe((params) => {
//       const eventId = params.get('eventId');
//       this.getEventSpeakers();
//     });
//   }

//   getEventSpeakers(): void {
//     this.route.paramMap
//       .subscribe(parms => {
//         let id = parms.get('id');
//         console.log(id);
//         this.apiService.get("eventDetails/" + id+ "/speakers")
//           .subscribe({
//             next: response => {
//               this.speakers = response;
//               console.log(response);
//             },
//             error: error => { }
//           }
//           );
//       }
//       );
//   }
// }







