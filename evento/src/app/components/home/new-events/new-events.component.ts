import { DatePipe } from '@angular/common';
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
  constructor(private apiService:ApiService,private datePipe: DatePipe) {

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
  formatDate(dateString: string): string|null{
    const [day, month] = dateString.split('/');
    const date = new Date();
    date.setDate(parseInt(day, 10));
    date.setMonth(parseInt(month, 10) - 1); // Month is zero-based in JavaScript
    return this.datePipe.transform(date, 'dd MMM');
  }
}
