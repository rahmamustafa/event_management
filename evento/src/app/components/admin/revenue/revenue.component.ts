import { Component, OnInit } from '@angular/core';
import { RevenueByDate } from 'src/app/models/revenue/revenue-by-date.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-revenue',
  templateUrl: './revenue.component.html',
  styleUrls: ['./revenue.component.css']
})
export class RevenueComponent implements OnInit {

  revenueByDate:RevenueByDate[]=[];
  chartOptions :any={};
  constructor(private apiService:ApiService) {
    
  }
  ngOnInit(): void {
    this.generateRevenueByDate();
  }

  dateConverter(date:string):Date{
    const parts = date.split('/'); // Split the string by '/'
    const day = parseInt(parts[0], 10); // Extract the day as a number
    const month = parseInt(parts[1], 10) - 1; // Extract the month as a number (subtract 1 as months are zero-based in JavaScript)

    const currentYear = new Date().getFullYear(); // Get the current year
    console.log("date ->"+new Date(currentYear, month, day));
    return new Date(currentYear, month, day);

  }

  generateRevenueByDate(){
    let arr: any[]=[];
    this.apiService.get(`api/admin/revenue/byDate`)
    .subscribe({
      next:(response:any)=>{
        this.revenueByDate=response;
        response.forEach((re:any) => 
        { 
          console.log("revenue date ->"+re.x+" "+re.y);
          arr.push({x:this.dateConverter(re.x),y:re.y});
          
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
      valueFormatString: "",
      suffix: ""
      },
      data: [{
      type: "splineArea",
      color: "rgba(54,158,173,.7)",
      dataPoints: arr
      }]
    }	
  }
}
