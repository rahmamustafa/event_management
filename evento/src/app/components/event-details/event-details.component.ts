import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { APIResponse } from 'src/app/models/api-response.model';
import { EventReview } from 'src/app/models/review-models/event-review.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-event-details',
  templateUrl: './event-details.component.html',
  styleUrls: ['./event-details.component.css']
})
export class EventDetailsComponent implements OnInit{
  review =new EventReview();
  constructor(private _activatedRoute:ActivatedRoute,private apiService:ApiService){
  
  }
  
  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe((params) => {
      const id = params.get('id');
      console.log(id);
      
    });
  }
  getEventReviews(): Array<EventReview> {
   return this.apiService.get("event/"+id+"/reviews")
    .subscribe({
      next:response=>{
        console.log(response);  
      console.log(response._embedded);
      console.log(response.Sucess);
      console.log(response.Data);
    },
    error:error=>{}
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