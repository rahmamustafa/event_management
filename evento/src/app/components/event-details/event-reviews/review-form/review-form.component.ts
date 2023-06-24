import { animate, state, style, transition, trigger } from '@angular/animations';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import {  EventReviewCreateDto } from 'src/app/models/event-details-models/review-models/event-review-create-dto.model';
import { ApiService } from 'src/app/services/api.service';
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-review-form',
  templateUrl: './review-form.component.html',
  styleUrls: ['./review-form.component.css'],
  animations: [
    trigger('fadeInOut', [
      state('void', style({ opacity: 0 })),
      transition('void <=> *', animate(300))
    ])
  ]
})
export class ReviewFormComponent {
  reviewForm:FormGroup;
  showSection = false;
  eventId: string;
  isReviewed:boolean;
  userId:any;
  constructor(private _form:FormBuilder, private apiService: ApiService,private http: HttpClient,private route: ActivatedRoute,private userService:UserService){
    
  }
 
  
  ngOnInit(): void {
   

    this.reviewForm=this._form.group({
      Review:['',[Validators.required, Validators.min(4)]]
    });
    this.route.params.subscribe(params => {
      this.eventId = params['id'];
    });
    this.apiService.get(`events/${this.eventId}/review?user=1`)
    .subscribe({
      next: response => {
        this.isReviewed = response;
      },
      error: error => { }
    }
    );
  }

  addReview(){
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
      //get User Data
     
    console.log("clicked ->"+this.eventId);
    let userReview = this.reviewForm.get("Review")?.value;
    let eventReview = new EventReviewCreateDto();
    eventReview.review=userReview;
    eventReview.user_id=this.userId;
    console.log("userId ->"+this.userId);

    this.http.post<any>(`http://localhost:8888/events/${this.eventId}/review`, eventReview)
    .subscribe({
      next:response=>{
        console.log(response);
        this.reviewForm = this._form.group({
          Review: ''
        });

      },
      error:error=>{
        console.log("error->"+error);
      }
    }
    );
  }


  toggleSection() {
    this.showSection = !this.showSection;
  }
}
