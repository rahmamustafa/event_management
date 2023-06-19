import { Component } from '@angular/core';
import { DatePipe } from '@angular/common';
import { HttpClient } from '@angular/common/http';
import { ApiService } from 'src/app/services/api.service';
import { eventTable } from 'src/app/models/home-models/event-table.model';

@Component({
  selector: 'app-schedule',
  templateUrl: './schedule.component.html',
  styleUrls: ['./schedule.component.css'],
  providers: [DatePipe]
})
export class ScheduleComponent {
  currentDate: string|null;
  tomorrowDate: string|null;
  afterTomorrowDate: string|null;
  events: eventTable[] = [];
  currentPage:number;
  constructor(private datePipe: DatePipe,private apiService:ApiService) {
    this.setDate();
    this.getTableData(this.currentDate,1);

  }
  getTableData(date:string|null,page:number=1):void{
    this.apiService.get(`events/dates?date=${date}&page=${page}&size=1`)
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
  
  setDate(){
    const today = new Date();
    const tomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1);
    const AfterTomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 2);

    this.currentDate = this.datePipe.transform(today, 'd MMMM  y');
    this.tomorrowDate = this.datePipe.transform(tomorrow, 'd MMMM  y');
    this.afterTomorrowDate = this.datePipe.transform(AfterTomorrow, 'd MMMM  y');
  }
}
