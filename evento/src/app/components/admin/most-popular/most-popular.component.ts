import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ApiService } from 'src/app/services/api.service';
import { DomSanitizer } from '@angular/platform-browser';
import { Event } from 'src/app/models/event/event';

@Component({
    selector: 'app-most-popular',
    templateUrl: './most-popular.component.html',
    styleUrls: ['./most-popular.component.css']
})
export class MostPopularComponent {
    popularEvent: Event[] = [];
    constructor(
        private activatedRoute: ActivatedRoute,
        private apiService: ApiService,
        private sanitizer: DomSanitizer
    ) { }
    ngOnInit(): void {
        console.log("Before Most Popular");
        this.getEvents();
        console.log("After Most Popular");
    }
    getEvents() {
        console.log("In The Method");
        this.apiService.get("api/admin/popular").subscribe({
            next: (response) => {
                console.log("dlhdjdhdkjjdkh" + response)
                this.popularEvent = response;
                console.log(this.popularEvent);
            },
            error: (error: any) => {
                console.log("ERROR");
            }
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
