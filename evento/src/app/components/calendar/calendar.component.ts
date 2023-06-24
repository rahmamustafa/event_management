import { Component, signal, ChangeDetectorRef, OnInit } from '@angular/core';
import { CalendarOptions, DateSelectArg, EventClickArg, EventApi, EventInput } from '@fullcalendar/core';
import interactionPlugin from '@fullcalendar/interaction';
import dayGridPlugin from '@fullcalendar/daygrid';
import timeGridPlugin from '@fullcalendar/timegrid';
import listPlugin from '@fullcalendar/list';
// import { INITIAL_EVENTS, createEventId } from 'src/app/models/event/event-utils';
import { ApiService } from 'src/app/services/api.service';
import moment from 'moment';
// import { EventByDate, getEvents } from 'src/app/models/event/eventByDate';

@Component({
  selector: 'app-calendar',
  templateUrl: './calendar.component.html',
  styleUrls: ['./calendar.component.css']
})
export class CalendarComponent implements OnInit {

  dateFormat= new Date(1687644000000);
  
  Events = [
    {  date: '2023-06-23',title: "Event 1"},
    { title: "Event 2", date: '2023-06-23' },
    { title: "Event 3", date: new Date(1687361400000)},
  ];

  eventInput: EventInput[] = [];
  // oneEventInput: any = [
  //   { title: "Event 1", date: '2023-06-23' },
  //   { title: "Event 2", date: '2023-06-23T00:00:00' },
  // ];

  // oneEventInput: EventByDate[] = [];

  // events: EventByDate[] = [];
  // eventDate: any;
  date: any;
  test:any[];

  eventApi:EventApi[];

  calendarVisible = signal(true);

  currentEvents = signal<EventApi[]>([]);
  
  calendarOptions:CalendarOptions;





  constructor(
    private changeDetector: ChangeDetectorRef,
    private apiService: ApiService
  ) { }

  ngOnInit(): void {
    this.calendarOptions = {
      plugins: [
        interactionPlugin,
        dayGridPlugin,
        timeGridPlugin,
        listPlugin,
      ],
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay,listWeek'
      },
      initialView: 'dayGridMonth',
      weekends: true,
      editable: true,
      selectable: true,
      selectMirror: true,
      dayMaxEvents: true,
      events: [] 
    };

      this.apiService.get('all-events').subscribe({
        next: (response: { title: string; date: string }[]) => {
          console.log('Response:', response);
      
          const convertedEvents = response.map(event => ({
            title: event.title,
            date: new Date(event.date).toISOString().split('T')[0]
          }));
          console.log('Converted Events:', convertedEvents);
          this.calendarOptions.events = convertedEvents;

          this.eventApi=this.test;
          this.currentEvents.set(this.eventApi);
          this.changeDetector.detectChanges();
          console.log(this.eventApi);
        },
        error: (error) => { }
      });

    };



}

