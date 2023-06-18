import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { DomSanitizer } from '@angular/platform-browser';

import { Event } from 'src/app/models/event/event';
import { Category } from 'src/app/models/event/category';

@Component({
  selector: 'app-event-list',
  templateUrl: './event-list.component.html',
  styleUrls: ['./event-list.component.css']
})
export class EventListComponent implements OnInit {
  events: Event[] = [];
  categories: Category[] = [];
  numbers: number[] = [1, 2, 3, 4, 5];
  image: any;

  constructor(
    private activatedRoute: ActivatedRoute,
    private apiService: ApiService,
    private sanitizer: DomSanitizer
  ) {}

  ngOnInit(): void {
    this.getEvents(0);
    this.getCategories();
    console.log("aaaaaaaaaaaaaaa");
  }

  getEvents(page: number) {
    this.apiService.get(`events?page=${page}&size=6`).subscribe({
      next: (response: Event[]) => {
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
      error: (error) => {}
    });
  }

  getCategories() {
    this.apiService.get("categories").subscribe({
      next: (response: Category[]) => {
        this.categories = response;
      },
      error: (error) => {}
    });
  }

  getEventByCategory(categoryType: string) {
    this.apiService.get(`events/category/${categoryType}`).subscribe({
      next: (response: Event[]) => {
        this.events = response;
      },
      error: (error) => {}
    });
  }

  getEventBySpeaker(speakerName: string) {
    this.apiService.get(`events/${speakerName}`).subscribe({
      next: (response: Event[]) => {
        this.events = response;
      },
      error: (error) => {}
    });
  }

  getEventByStatus(status: string) {
    this.apiService.get(`events/status/${status}`).subscribe({
      next: (response: Event[]) => {
        this.events = response;
      },
      error: (error) => {}
    });
  }
}
