import { Component, OnInit } from '@angular/core';
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
export class ScheduleComponent implements OnInit{
  today: string|null;
  currentDate: string|null;
  tomorrowDate: string|null;
  afterTomorrowDate: string|null;
  events: eventTable[] = [];
  currentPage:number;
  Pagination :number;
  activeButton: number = 1;
  constructor(private datePipe: DatePipe,private apiService:ApiService) {
    this.setDate();

  }
  ngOnInit(): void {
   
    this.getTableData(this.today,1);
   
  }
  getTableData(date:string|null,page:number=1):void{
    console.log("  getTableData->"+date+" page ->"+page);
    this.changeDate(date);
    
    this.apiService.get(`events/dates?date=${date}&page=${page-1}&size=4`)
    .subscribe({
      next:(response: any)=>{
        console.log(response.content);
        this.events =response.content;
        this.Pagination =response.totalPages;
        console.log(this.events);
      },
      error:(error: any)=>{
        console.log("error->"+error);
      }
    }
    );
  }
  
  setDate(){
    const today = new Date();
    const tomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 1);
    const AfterTomorrow = new Date(today.getFullYear(), today.getMonth(), today.getDate() + 2);

    this.today = this.datePipe.transform(today, 'd MMMM  y');
    this.tomorrowDate = this.datePipe.transform(tomorrow, 'd MMMM  y');
    this.afterTomorrowDate = this.datePipe.transform(AfterTomorrow, 'd MMMM  y');
  }
  changeDate(date:string|null){
    console.log("current date 0->"+date);
    this.currentDate = date;
  }
  getNumberRange(max: number): number[] {
    return Array.from({ length: max }, (_, index) => index + 1);
  }
 

  setActiveButton(buttonNumber: number): void {
    this.activeButton = buttonNumber;
  }
  handleButtonClick(num:number,event: Event): void {
    event.preventDefault();
    console.log(num);
    this.setActiveButton(num);
    this.getTableData(this.currentDate,num);
  }
  convertTo12HourFormat(time: string): string {
    const [hours, minutes] = time.split(':');
    let hour = parseInt(hours);
    const suffix = hour >= 12 ? 'PM' : 'AM';
    
    if (hour === 0) {
      hour = 12;
    } else if (hour > 12) {
      hour -= 12;
    }

    return `${hour}:${minutes} ${suffix}`;
  }
}
