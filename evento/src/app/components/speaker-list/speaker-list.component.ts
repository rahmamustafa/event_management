import { Component } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { ActivatedRoute } from '@angular/router';
import th from '@mobiscroll/angular/dist/js/i18n/th';
import { eventSpeakersDTO } from 'src/app/models/event-speakers.model';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-speaker-list',
  templateUrl: './speaker-list.component.html',
  styleUrls: ['./speaker-list.component.css']
})
export class SpeakerListComponent {
  speakers: eventSpeakersDTO[] = [];
  size: number = 8;
  pageNumbers: number=0;
  numbers: any[]=[];
  constructor(
    private activatedRoute: ActivatedRoute,
    private apiService: ApiService,
    private sanitizer: DomSanitizer
  ) { 
    this.numbers=this.getpageNumber();
  }
  ngOnInit(): void {
    this.getSpeakers(0);

  }
  getSpeakers(page: number) {
    this.apiService.get(`speakers/sp?page=${page}`).subscribe({
      next: (response: any) => {
        console.log(response);
        this.speakers = response;

      },
      error: (error: any) => { }
    });



  }
  getImage(image: any): any {
    const bytes = new Uint8Array(image);
    const base64 = btoa(String.fromCharCode(...bytes));
    let objectURL = 'data:image/png;base64,' + image;
    let return_image = this.sanitizer.bypassSecurityTrustUrl(objectURL);
    return return_image;
  }
  getpageNumber() :any{
    this.apiService.get(`speakers/pagesNumber/${this.size}`).subscribe({
      next: (response: number) => {
        console.log(response);
        this.pageNumbers =response;

       
        for(let i=1;i<this.pageNumbers;i++) {
          console.log(i);
         
            this.numbers.push(i)
            console.log( this.numbers)
        }
      
        return this.numbers;
      },
      error: (error: any) => {
        console.log("ERROR");
      }
    });
  
  }
  getNumberRange(max: number): number[] {
    return Array.from({ length: max }, (_, index) => index + 1);
  }
  setPageNumber(page: number) {
      this.getSpeakers(page);
  }

}
