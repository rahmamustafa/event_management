import { Component, OnInit } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';

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
  image:any;
  // _activatedRoute: any;
  constructor(private route: ActivatedRoute, private apiService: ApiService,private sanitizer: DomSanitizer) { }
  ngOnInit(): void {
    this.route.paramMap.subscribe((params) => {
      const id = params.get('id');
      console.log(id);
      this.getEvent(id);
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
          
            },
            error: error => { }
          }
          );
    
  }
}