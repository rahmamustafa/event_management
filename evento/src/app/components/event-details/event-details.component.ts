import { Component, OnInit } from '@angular/core';

import { ActivatedRoute } from '@angular/router';
import { eventDetailsDTO } from 'src/app/models/event-details.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})

export class EventDetailsComponent implements OnInit {
  eventDetails: eventDetailsDTO;

  // _activatedRoute: any;
  constructor(private route: ActivatedRoute, private apiService: ApiService) { }
  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const eventId = params.get('eventId');
      this.getEvent();
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
}