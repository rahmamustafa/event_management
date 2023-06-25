import { animate, state, style, transition, trigger } from '@angular/animations';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { EventReviewCreateDto } from 'src/app/models/event-details-models/review-models/event-review-create-dto.model';
import { ApiService } from 'src/app/services/api.service';
import { SharedServiceService } from 'src/app/services/shared-service.service';
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
  reviewForm: FormGroup;
  showSection = false;
  eventId: any;
  isReviewed:boolean;
  userId:any;
  userEmail:any;
  
  constructor(private _form:FormBuilder,private sharedService:SharedServiceService, private apiService: ApiService,private http: HttpClient,private route: ActivatedRoute,private userService:UserService){
    
  }
 
  triggerRefresh() {
    this.sharedService.refreshComponent(this.eventId);
  }
  ngOnInit(): void {

    this.userEmail = this.userService.getuserEmail();

    this.reviewForm = this._form.group({
      Review: ['', [Validators.required, Validators.min(4)]]
    });
    this.route.params.subscribe((params: { [x: string]: string; }) => {
      this.eventId = params['id'];
    });

    this.http.post<any>("http://localhost:8888/user", { "email": this.userEmail })
      .subscribe({
        next: (response: any) => {
          this.apiService.get(`events/${this.eventId}/review?user=${response}`)
            .subscribe({
              next: (response: boolean) => {
                this.isReviewed = response;
              },
              error: (error: any) => { }
            }
            );
        },
        error: (error: any) => {
          this.userId = null;
        }
      }
      );


  }

  addReview() {
    const httpOptions = {
      headers: new HttpHeaders({
        'Content-Type': 'application/json'
      })
    };
    //get User Data

    console.log("clicked ->" + this.userEmail);
    this.http.post<any>("http://localhost:8888/user", { "email": this.userEmail })
      .subscribe({
        next: (response: any) => {

          let userReview = this.reviewForm.get("Review")?.value;
          let eventReview = new EventReviewCreateDto();
          eventReview.review = userReview;
          eventReview.user_id = response;
          console.log("userId ->" + this.userId);
          this.http.post<any>(`http://localhost:8888/events/${this.eventId}/review`, eventReview)
          .subscribe({
            next:(response: any)=>{
              console.log(response);
              this.reviewForm = this._form.group({
                Review: ''
              });
              this.showSection =false;
              this.isReviewed = true;
              this.triggerRefresh();
            },
            error:(error: any)=>{
              console.log("error->"+error);
            }
           } );

        },
        error: (error: any) => {
          this.userId = null;
        }
      }
      );
    // end 

  }


  toggleSection() {
    this.showSection = !this.showSection;
  }
}
