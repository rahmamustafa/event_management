import { Component } from '@angular/core';
import { ApiService } from 'src/app/services/api.service';
import { ActivatedRoute } from '@angular/router';
import { Label } from 'ng2-charts';
import { ChartDataSets, ChartOptions, ChartType } from 'chart.js';
import { ChartsModule } from 'ng2-charts/ng2-charts';


@Component({
  selector: 'app-attendance',
  templateUrl: './attendance.component.html',
  styleUrls: ['./attendance.component.css']
})
export class AttendanceComponent {

    eventAttendanceData:Map<string,number>;
     public barChartOptions: ChartOptions = {
        responsive: true
      };
      public barChartLabels: Label[] = [];
      public barChartData: ChartDataSets[] = [
        { data: [], label: 'Event Attendance' }
      ];
      public barChartType: ChartType = 'bar';


constructor(
    private activatedRoute: ActivatedRoute,
    private apiService: ApiService
  ) {}
  ngOnInit(): void {
    this.getEventAttendance();
    console.log("in Event AttendanceComponent on INit");
  }
  getEventAttendance(){
  this.apiService.get("events/attendance").subscribe({
        next: (response) => {
          console.log("in Event AttendanceComponent");
          console.log(response);
            this.barChartLabels = Object.keys(response);
            this.barChartData[0].data = Object.values(response);
        },
        error: (error) => {}
      });
  }
}
