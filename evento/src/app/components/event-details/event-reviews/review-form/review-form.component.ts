import { animate, state, style, transition, trigger } from '@angular/animations';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { CreateEventReview } from 'src/app/models/event-details-models/review-models/create-event-review.model';
import { ApiService } from 'src/app/services/api.service';
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
  constructor(private _form:FormBuilder,private apiService:ApiService,private route: ActivatedRoute){}
  ngOnInit(): void {
    this.reviewForm=this._form.group({
      Review:['',[Validators.required, Validators.min(4)]]
    });
    this.route.params.subscribe(params => {
      this.eventId = params['id'];
    });
  }
  addReview(userId:number){
    console.log("clicked ->"+this.eventId);
    let userReview = this.reviewForm.get("Review")?.value;
    let review =  new CreateEventReview(userId,userReview); 

    this.apiService.post(`events/${this.eventId}/review`,review)
    .subscribe({
      next:response=>{
        console.log(response);
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
