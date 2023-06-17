import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { DomSanitizer } from '@angular/platform-browser';

import { Event } from 'src/app/models/event/event';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent {
  events: Event[] = [];
  image: any;
  constructor(private _activatedRoute: ActivatedRoute, private apiService: ApiService, private sanitizer: DomSanitizer) {

  }

  ngOnInit(): void {
    this.getEvents();
    console.log("aaaaaaaaaaaaaaa");

  }

  getEvents() {
    this.apiService.get("events/category/sport")
      .subscribe({
        next: response => {

          this.events = response;
          // const bytes = new Uint8Array(this.events[0].image);
          // const base64 = btoa(String.fromCharCode(...bytes));
          // let objectURL = 'data:image/png;base64,' + this.events[0].image;
          // this.image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
          // // this.image = 'data:image/jpg;base64,${base64}';
          // console.log(this.image);
          console.log("ssssssss s");
          console.log(response);
        },
        error: error => { }
      }
      );

      
  }

}
