import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventReview } from 'src/app/models/event-details-models/review-models/event-review.model';
import { ApiService } from 'src/app/services/api.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Subscription } from 'rxjs';
import { SharedServiceService } from 'src/app/services/shared-service.service';

@Component({
  selector: 'app-event-reviews',
  templateUrl: './event-reviews.component.html',
  styleUrls: ['./event-reviews.component.css']
})
export class EventReviewsComponent implements OnInit {
  reviews: EventReview[] = [];
  image: any;
  private refreshSubscription: Subscription;
  event_id:number;
  constructor(private sharedService:SharedServiceService,private _activatedRoute: ActivatedRoute, private apiService: ApiService, private sanitizer: DomSanitizer) {
    this.refreshSubscription = this.sharedService.refresh$.subscribe(() => {
      this._activatedRoute.paramMap.subscribe((params:any) => {
        let id = params.get('id');
        this.refreshComponent(id);
        
      });
     
    });
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
  ngOnDestroy() {
   
    this.refreshSubscription.unsubscribe();
  }

  refreshComponent(id:any) {
    this.getReviews(id);
  }
}