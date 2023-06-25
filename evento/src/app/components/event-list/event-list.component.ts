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
  numbers: number[];
  image: any;
  pageNumber: number = 0;
  categoryType: string;
  type: number;
  size: number = 9;
  pageNumbers: number ;
  constructor(
    private activatedRoute: ActivatedRoute,
    private apiService: ApiService,
    private sanitizer: DomSanitizer
  ) {

  }

  ngOnInit(): void {
    this.getEvents(0);
    this.getCategories();
    console.log("aaaaaaaaaaaaaaa");

    this.numbers = this.numSequence(this.pageNumbers);

  }
  numSequence(n: number): Array<number> {

    return Array(n);
  }
  getpageNumber(): any {
    this.apiService.get(`pagesNumber/${this.size}`).subscribe({
      next: (response: number) => {
        console.log(response);
        this.pageNumbers = response;       
      },
      error: (error: any) => {
        console.log("ERROR");
      }
    });
  }
  getEvents(page: number) {
    this.apiService.get(`events?page=${this.pageNumber}&size=9`).subscribe({
      next: (response: any) => {
        this.events = response;
        console.log("ssssssss s");
        console.log(response);
      },
      error: (error: any) => { }
    });
    this.pageNumber = this.getpageNumber();

    console.log("**********" + this.pageNumbers)
    console.log("**********" + this.getpageNumber())
  }

  getCategories() {
    this.apiService.get("categories").subscribe({
      next: (response: any) => {
        this.categories = response;
      },
      error: (error: any) => { }
    });
  }

  getEventByCategory() {
    this.apiService.get(`events/category/${this.categoryType}?page=${this.pageNumber}`).subscribe({
      next: (response: any) => {
        this.events = response;
      },
      error: (error: any) => { }
    });
  }

  getEventBySpeaker(speakerName: string) {
    this.apiService.get(`events/${speakerName}?page=${this.pageNumber}`).subscribe({
      next: (response: any) => {
        this.events = response;
      },
      error: (error: any) => { }
    });
  }

  getEventByStatus(status: string) {
    this.apiService.get(`events/status/${status}?page=${this.pageNumber}`).subscribe({
      next: (response: any) => {
        this.events = response;
      },
      error: (error: any) => { }
    });
  }
  setPageNumber(page: number) {
    this.pageNumber = page;
    if (this.type == 1)
      this.getEventByCategory();
    else if (this.type == 0)
      this.getEvents(page);
  }
  setCategoryType(type: string) {
    this.categoryType = type;
    this.getEventByCategory();
  }
  getImage(image: any): any {
    const bytes = new Uint8Array(image);
    const base64 = btoa(String.fromCharCode(...bytes));
    let objectURL = 'data:image/png;base64,' + image;
    let return_image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
    return return_image;
  }

}
