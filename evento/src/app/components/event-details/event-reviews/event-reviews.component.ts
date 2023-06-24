import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventReview } from 'src/app/models/event-details-models/review-models/event-review.model';
import { ApiService } from 'src/app/services/api.service';
import { DomSanitizer } from '@angular/platform-browser';
@Component({
  selector: 'app-event-reviews',
  templateUrl: './event-reviews.component.html',
  styleUrls: ['./event-reviews.component.css']
})
export class EventReviewsComponent implements OnInit {
  reviews: EventReview[] = [];
  image: any;
  
  constructor(private _activatedRoute: ActivatedRoute, private apiService: ApiService, private sanitizer: DomSanitizer) {

  }

  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe((params:any) => {
      const id = params.get('id');
      console.log(id);
      this.getReviews(id);
    });
  }

  getReviews(id: any) {

    this.apiService.get("event/" + id + "/reviews")
      .subscribe({
        next: (response: EventReview[]) => {
          this.reviews = response;
         
        },
        error: (error: any) => { }
      }
      );

  }
  getImage(image: any): any {
    const bytes = new Uint8Array(image);
    const base64 = btoa(String.fromCharCode(...bytes));
    let objectURL = 'data:image/png;base64,' + image;
    let return_image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
    return return_image;
  }

}