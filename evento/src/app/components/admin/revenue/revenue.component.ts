import { Component, OnInit } from '@angular/core';
import { MatDatetimepickerType } from '@mat-datetimepicker/core/datetimepicker/datetimepicker-type';
import { Chart } from 'chart.js';
import { TotalRevenue } from 'src/app/models/revenue/total-revenue.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-revenue',
  templateUrl: './revenue.component.html',
  styleUrls: ['./revenue.component.css']
})
export class RevenueComponent implements OnInit {

  totalrevenue:TotalRevenue[]=[];
  chartOptions :any;
  chart: Chart<"bar", string[], string>;
  labels:string[];
  datasets:number[];
  constructor(private apiService:ApiService) {
   
    
  }
  ngOnInit(): void {
    this.generateTotalEventsRevenue();
  }
  
  generateTotalEventsRevenue(){
    this.apiService.get(`api/admin/revenue/total`)
    .subscribe({
      next:(response:any)=>{
        this.totalrevenue=response;
        this.totalrevenue.forEach(obj => {
          this.labels.push(obj.label);
          this.datasets.push(obj.y);
        });
        this.createChart();
      },
      error:(error: any)=>{
        console.log("error->"+error);
      }
    }
    );
  }
  createChart(){
    var myChart = new Chart("myChart", {
      type: 'bar',
      data: {
          labels: this.labels,
          datasets: [{
              label: '# of Votes',
              data: this.datasets,
              backgroundColor: [
                  'rgba(255, 99, 132, 0.2)',
                  'rgba(54, 162, 235, 0.2)',
                  'rgba(255, 206, 86, 0.2)',
                  'rgba(75, 192, 192, 0.2)',
                  'rgba(153, 102, 255, 0.2)',
                  'rgba(255, 159, 64, 0.2)'
              ],
              borderColor: [
                  'rgba(255, 99, 132, 1)',
                  'rgba(54, 162, 235, 1)',
                  'rgba(255, 206, 86, 1)',
                  'rgba(75, 192, 192, 1)',
                  'rgba(153, 102, 255, 1)',
                  'rgba(255, 159, 64, 1)'
              ],
              borderWidth: 1
          }]
      },
      options: {
          scales: {
              y: {
                  beginAtZero: true
              }
          }
      }
  });
  }
}
