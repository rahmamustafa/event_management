import { Component , ViewChild} from '@angular/core';

import { ChartComponent } from "ng-apexcharts";

import {
  ApexNonAxisChartSeries,
  ApexResponsive,
  ApexChart
} from "ng-apexcharts";
import { map } from 'rxjs';
import { ApiService } from 'src/app/services/api.service';

export type ChartOptions = {
  series: any;
  chart: any;
  responsive: any;
  labels: any;
};
@Component({
  selector: 'app-country',
  templateUrl: './country.component.html',
  styleUrls: ['./country.component.css']
})
export class CountryComponent {
  @ViewChild("chart") chart: ChartComponent;
  public chartOptions: Partial<ChartOptions>;
  country:Map<string,number>;
  serios:any=[];
  label:any=[];
  constructor(private apiService: ApiService) {
    
   
  }
  ngOnInit(): void {

    this.getCountries();
    console.log("in Event AttendanceComponent on INit");
    console.log("serios->>>>>>>>>>"+this.serios)
   
   }
  getCountries(){
    this.apiService.get("api/admin/country" )
    .subscribe({
      next: (response:any) => {
        console.log(response);
        this.country = new Map<string, number>(Object.entries(response));
        this.serios=Array.from(this.country.values());
        this.label=Array.from(this.country.keys());
        console.log( this.serios)
        this.chartOptions = {
          series:  this.serios,
          chart: {
            width: 380,
            type: "pie"
          },
          labels: this.label,
          responsive: [
            {
              breakpoint: 480,
              options: {
                chart: {
                  width: 200
                },
                legend: {
                  position: "bottom"
                }
              }
            }
          ]
        };
      },
      error: (error:any) => { }
    }
    );
  }
}
