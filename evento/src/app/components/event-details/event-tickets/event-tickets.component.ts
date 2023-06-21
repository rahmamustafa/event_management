import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { EventTicket } from 'src/app/models/event-details-models/ticket-model/event-ticket.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-event-tickets',
  templateUrl: './event-tickets.component.html',
  styleUrls: ['./event-tickets.component.css']
})
export class EventTicketsComponent implements OnInit{

 tickets:EventTicket[];
  
  constructor(private _activatedRoute:ActivatedRoute,private apiService:ApiService){
  
  }
  
  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe((params) => {
      const id = params.get('id');
      console.log(id);
      this.getTickets(id);
    });
  }

  getTickets(id:any) {
  
      this.apiService.get("event/"+id+"/tickets")
      .subscribe({
        next:response=>{
          this.tickets=response;
          console.log(response);
      },
      error:error=>{}
    }
        );
     
}
  
}
