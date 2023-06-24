import { Component } from '@angular/core';

@Component({
  selector: 'app-bar-chart-component',
  templateUrl: './bar-chart-component.component.html',
  styleUrls: ['./bar-chart-component.component.css']
})
export class BarChartComponentComponent {
public barChartOptions: ChartOptions = {
    responsive: true,
  };
  public barChartLabels: Label[] = [];
  public barChartData: ChartDataSets[] = [
    { data: [], label: 'Attendance' }
  ];
}
