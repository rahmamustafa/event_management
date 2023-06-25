import {  Component, OnInit, ViewChild, ElementRef, AfterViewInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { Attendance } from 'src/app/models/Attendance.models';

@Component({
    selector: 'app-attendance',
    templateUrl: './attendance.component.html',
    styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent {
    eventAttendanceData: Map<string, number>;
    public barChartLabels: any = [];
    public barChartData: any = [
        { data: [], label: 'Event Attendance' }
    ];
    public barChartType = 'bar'
    attendance: Attendance;
    @ViewChild('myCanvas') canvasRef: ElementRef;
    private context: CanvasRenderingContext2D;
    constructor(
        private activatedRoute: ActivatedRoute,
        private apiService: ApiService
    ) { }
    ngOnInit(): void {
        
        this.getEventAttendance();
        console.log("in Event AttendanceComponent on INit");
    }
    ngAfterViewInit() {
        this.context = this.canvasRef.nativeElement.getContext('2d');
      }
      displayNoDataMessage() {
        // Clear the canvas
        this.context.clearRect(0, 0, this.canvasRef.nativeElement.width, this.canvasRef.nativeElement.height);
    
        // Set the font and align the text
        this.context.font = '20px Arial';
        this.context.textAlign = 'center';
    
        // Set the text message
        const message = 'No data available';
    
        // Position the text message in the center of the canvas
        const canvasWidth = this.canvasRef.nativeElement.width;
        const canvasHeight = this.canvasRef.nativeElement.height;
        const x = canvasWidth / 2;
        const y = canvasHeight / 2;
    
        // Draw the text message on the canvas
        this.context.fillText(message, x, y);
      }
    
    getEventAttendance() {
        this.apiService.get("events/attendance").subscribe({
            next: (response) => {
                console.log("in Event AttendanceComponent");
                console.log(response);
                this.eventAttendanceData = new Map < string, number > (Object.entries(response));
                console.log(this.eventAttendanceData);
                this.barChartLabels = Array.from(this.eventAttendanceData.keys());
                this.barChartData[0].data = Array.from(this.eventAttendanceData.values());
                console.log(this.eventAttendanceData.keys());
                console.log(this.eventAttendanceData.values());
                console.log(Object.entries(response)[0][1]);
            },
            error: (error) => { 
                this.displayNoDataMessage();
            }
        });
    }
}
