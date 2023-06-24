import { Component, OnInit } from '@angular/core';
import { MatDatetimepickerType } from '@mat-datetimepicker/core/datetimepicker/datetimepicker-type';
import { Chart } from 'chart.js';
import { RevenueByDate } from 'src/app/models/revenue/revenue-by-date.model';
import { TotalRevenue } from 'src/app/models/revenue/total-revenue.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-revenue',
  templateUrl: './revenue.component.html',
  styleUrls: ['./revenue.component.css']
})
export class RevenueComponent implements OnInit {

  totalrevenue:TotalRevenue[]=[];
  revenueByDate:RevenueByDate[]=[];
  chartOptions :any={};
  chart: Chart<"bar", string[], string>;
  labels:string[]=[];
  datasets:number[]=[];
  colors: string[];
  constructor(private apiService:ApiService) {
    
  }
  ngOnInit(): void {
    this.generateRevenueByDate();
    this.generateTotalEventsRevenue();
  }
  generateColors(length:number): void {
    const numberOfDataPoints = length;
    this.colors = [];

    for (let i = 0; i < numberOfDataPoints; i++) {
      const hue = (i * 360 / numberOfDataPoints) % 360;
      const color = `hsl(${hue}, 70%, 50%)`; // Adjust saturation and lightness as needed
      this.colors.push(color);
    }
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
      
        this.generateColors(this.labels.length); 
        this.createTotalRevenueChart();
      },
      error:(error: any)=>{
        console.log("error->"+error);
      }
    }
    );
  }
  createTotalRevenueChart(){
    var myChart = new Chart("myChart", {
      type: 'bar',
      data: {
          labels: this.labels,
          datasets: [{
              label: '# of Votes',
              data: this.datasets,
              backgroundColor:this.colors,
              
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
  dateConverter(date:string):Date{
    const parts = date.split('/'); // Split the string by '/'
    const day = parseInt(parts[0], 10); // Extract the day as a number
    const month = parseInt(parts[1], 10) - 1; // Extract the month as a number (subtract 1 as months are zero-based in JavaScript)

    const currentYear = new Date().getFullYear(); // Get the current year
    return new Date(currentYear, month, day);

  }

  generateRevenueByDate(){
    let arr: any[]=[];
    this.apiService.get(`api/admin/revenue/byDate`)
    .subscribe({
      next:(response:any)=>{
        this.revenueByDate=response;
        response.forEach((re:any) => 
        { arr.push({x:this.dateConverter(re.x),y:re.y});
          
        });
        
        this.createRevenueByDateChart(arr);

      },
      error:(error: any)=>{
        console.log("error->"+error);
      }
    }
    );
  }
  createRevenueByDateChart(arr:any[]){
    this.chartOptions = {
      animationEnabled: true,
      title:{
      text: "Revenue Ber Date"
      }, 
      axisY: {
      title: "Revenue",
      valueFormatString: "#0,,.",
      suffix: "EGP"
      },
      data: [{
      type: "splineArea",
      color: "rgba(54,158,173,.7)",
      xValueFormatString: "dd/mm/yyyy",
      dataPoints: arr
      }]
    }	
  }
}
