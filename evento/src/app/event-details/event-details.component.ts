import { Component, OnInit } from '@angular/core';
import { ApiService } from '../services/api.service';
import { eventDetailsDTO } from '../models/event-details.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})

export class EventDetailsComponent implements OnInit {
  eventDetails: eventDetailsDTO;
  eventId: number;
  constructor(private route: ActivatedRoute, private apiService: ApiService) { }

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.eventId = +params['eventId'];
      this.getEvent();
    });
  }
  getEvent(): void {
    this.apiService['getEvent'](this.eventId).subscribe(
      (response: eventDetailsDTO) => {
        this.eventDetails = response;
      },
      (error: any) => {
        console.error(error)
      }
    )
  }
}