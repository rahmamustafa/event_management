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
  constructor(
    private activatedRoute: ActivatedRoute,
    private apiService: ApiService,
    private sanitizer: DomSanitizer
  ) { }
  ngOnInit(): void {
    this.getSpeakers();

  }
  getSpeakers() {
    this.apiService.get("speakers/sp").subscribe({
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
}
