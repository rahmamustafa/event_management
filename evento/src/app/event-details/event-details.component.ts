import { Component, OnInit } from '@angular/core';
// import {}
@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit{

  data: any;
  eventDate: Date;
  description: string;
  title: string;
  isOnline: boolean;
  image: string;

  // constructor(private data)

  ngOnInit(): void {
    throw new Error('Method not implemented.');
  }


}
