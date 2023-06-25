import { Component } from '@angular/core';
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

    constructor(
        private activatedRoute: ActivatedRoute,
        private apiService: ApiService
    ) { }
    ngOnInit(): void {

        this.getEventAttendance();
        console.log("in Event AttendanceComponent on INit");
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
            error: (error) => { }
        });
    }
}
