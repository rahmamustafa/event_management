import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventReview } from 'src/app/models/event-details-models/review-models/event-review.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-event-reviews',
  templateUrl: './event-reviews.component.html',
  styleUrls: ['./event-reviews.component.css']
})
export class EventReviewsComponent implements OnInit{
  reviews:EventReview []=[];
  constructor(private _activatedRoute:ActivatedRoute,private apiService:ApiService){
  
  }
  
  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe((params) => {
      const id = params.get('id');
      console.log(id);
      this.getReviews();
    });
  }

  getReviews() {
    this._activatedRoute.paramMap
    .subscribe(parms=>{
      let id=parms.get('id');
      console.log(id);
      this.apiService.get("event/"+id+"/reviews")
      .subscribe({
        next:response=>{
          this.reviews=response;
        console.log( this.reviews);
      },
      error:error=>{}
    }
        );
      }
        );
}
  
}
//   ngOnInit(): void {
//     this._activatedRoute.paramMap
//     .subscribe(parms=>{
//       let id=parms.get('id');
//       console.log(id);
//       this.apiService.get("event/"+id+"/reviews")
//       .subscribe({
//         next:response=>{
//         console.log(response._embedded);
//       },
//       error:error=>{}
//     }
//     );
//   }
//     );

// }
// }