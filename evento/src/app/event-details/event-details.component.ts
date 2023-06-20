import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { eventDetailsDTO } from '../models/event-details.model';
import { ActivatedRoute } from '@angular/router';
import { eventSpeakersDTO } from '../models/event-speakers.model';
// import { DatePipe } from '@angular/common';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
  // providers: [DatePipe]
})

export class EventDetailsComponent implements OnInit {
  eventDetails: eventDetailsDTO;
  speakers : eventSpeakersDTO[];
 

  constructor(private route: ActivatedRoute, private apiService: ApiService) {}


  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const eventId = params.get('eventId');
      this.getEvent();
      this.getEventSpeakers();
    });
  }


  getEvent(): void {
    this.route.paramMap
      .subscribe(parms => {
        let id = parms.get('id');
        console.log(id);
        this.apiService.get("eventDetails/" + id)
          .subscribe({
            next: response => {
              this.eventDetails = response;
              console.log(response);
            },
            error: error => { }
          }
          );
      }
      );
  }


  getEventSpeakers(): void {
    this.route.paramMap
      .subscribe(parms => {
        let id = parms.get('id');
        console.log(id);
        this.apiService.get("eventDetails/" + id+ "/speakers")
          .subscribe({
            next: response => {
              this.speakers = response;
              console.log(response);
            },
            error: error => { }
          }
          );
      }
      );
  }
}

