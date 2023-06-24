import { Component, OnInit } from '@angular/core';
import { speaker } from 'src/app/models/home-models/speaker.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-speakers',
  templateUrl: './speakers.component.html',
  styleUrls: ['./speakers.component.css']
})
export class SpeakersComponent implements OnInit{
  speakers:speaker[]=[];

  constructor(private apiService:ApiService) {
  }

  ngOnInit(): void {
    this.apiService.get(`speakers/most-speaking`)
    .subscribe({
      next:(response: any)=>{
        console.log(response);
        this.speakers =response;
        console.log(this.speakers);
      },
      error:(error: any)=>{
        console.log("error->"+error);
      }
    }
    );
  }


}
