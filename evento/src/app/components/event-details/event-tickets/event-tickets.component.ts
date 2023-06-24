import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EventTicket } from 'src/app/models/event-details-models/ticket-model/event-ticket.model';
import { ApiService } from 'src/app/services/api.service';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-event-tickets',
  templateUrl: './event-tickets.component.html',
  styleUrls: ['./event-tickets.component.css']
})
export class EventTicketsComponent implements OnInit{

 tickets:EventTicket[];
  
  constructor(private _activatedRoute:ActivatedRoute,private apiService:ApiService,private authService:AuthService,
    private router:Router){
  
  }
  eventId:any;
  ngOnInit(): void {
    this._activatedRoute.paramMap.subscribe((params) => {
      const id = params.get('id');
      this.eventId=id;
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
buyTicket(id:any) {
  if (this.authService.isUserLoggedIn()) {
    this.router.navigate(['/event/'+this.eventId+'/register/'+id]);
   }
   else{
    this.router.navigate(['/login']);
   }
}
  
}
