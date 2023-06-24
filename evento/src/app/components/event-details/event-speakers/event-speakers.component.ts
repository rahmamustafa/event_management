import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import { NgbCarouselConfig } from '@ng-bootstrap/ng-bootstrap';
import { EventReview } from 'src/app/models/event-details-models/review-models/event-review.model';
import { Speakers } from 'src/app/models/event/speakers';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-event-speakers',
  templateUrl: './event-speakers.component.html',
  styleUrls: ['./event-speakers.component.css']
})
export class EventSpeakersComponent {
  speakers: Speakers[] = [];
  image: any;
  responsiveOptions;
 
  constructor(config: NgbCarouselConfig,private _activatedRoute: ActivatedRoute, private apiService: ApiService, private sanitizer: DomSanitizer) {

    this.responsiveOptions = [
      {
       breakpoint: '1024px',
        numVisible: 3,
        numScroll: 3
      }
  ];
  }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe((params: { get: (arg0: string) => any; }) => {
      const id = params.get('id');
      console.log(id);
      this.getSpeakers(id);
    });
  }

  getSpeakers(id: any) {

    this.apiService.get(`events/${id}/speakers`)
      .subscribe({
        next: (response: any) => {
          this.speakers = response;
         
        },
        error: (error: any) => { }
      }
      );

  }

}
