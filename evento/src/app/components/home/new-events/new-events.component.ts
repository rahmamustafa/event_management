import { Component, OnInit } from '@angular/core';
import { NewEvent } from 'src/app/models/home-models/new-event.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-new-events',
  templateUrl: './new-events.component.html',
  styleUrls: ['./new-events.component.css']
})
export class NewEventsComponent implements OnInit{
  events:NewEvent[]=[];
  constructor(private apiService:ApiService) {

  }
  ngOnInit(): void {
    this.apiService.get(`events/new`)
    .subscribe({
      next:response=>{
        console.log(response);
        this.events =response;
        console.log(this.events);
      },
      error:error=>{
        console.log("error->"+error);
      }
    }
    );
  }
 

}
