import { Component, OnInit } from '@angular/core';
import { Chart } from 'chart.js';
import { TotalRevenue } from 'src/app/models/revenue/total-revenue.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-total-revenue',
  templateUrl: './total-revenue.component.html',
  styleUrls: ['./total-revenue.component.css']
})
export class TotalRevenueComponent implements OnInit {
  
  totalrevenue:TotalRevenue[]=[];
 
  chart: Chart<"bar", string[], string>;
  labels:string[]=[];
  datasets:number[]=[];
  colors: string[];
  constructor(private apiService:ApiService) {
    
  }
  ngOnInit(): void {
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
              label: '# of Revenues',
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
}
