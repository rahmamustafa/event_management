import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { co } from '@fullcalendar/core/internal-common';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  constructor( private formBuilder:FormBuilder , private apiService:ApiService, private _router: Router){}

  eventForm :FormGroup;
  dateTime: FormGroup

  isOnline:boolean;
  selectedImage: File;

  categeories :string[] = [];
  selectedCategory:string;

  titleExists: boolean;
  title: string;

  speakers = 
  [{ id: 1,name: 'order 1' },
  { id: 2, name: 'order 2' },
  { id: 3, name: 'order 3' },
  { id: 4, name: 'order 4' }
];

  ticketPrice: { [key: string]: string } = {};
  ticketNumber: { [key: string]: string } = {};



  ngOnInit() {
    this.apiService.get("categories")
    .subscribe({
      next: (response:{type:string}[]) => {
        // const convertedCat = response.map(cat => ({
        //   type: cat.type
        // }));
        // this.categeories = convertedCat;

        // console.log("cattttt " + convertedCat);
        response.forEach(cat=>this.categeories.push(cat.type))
      },
      error: (error: any) => { }
    }
    );

    this.apiService.get("speakers/all-speakers")
    .subscribe({
      next: (response: { id: number; name: string; }[]) => {
         this.speakers = response;
      },
      error: (error: any) => { }
    }
    );


    this.eventForm = this.formBuilder.group({
      title: [''],
      description: [''],
      category: [''],
      eventDate: [new FormControl('')],
      speakers: new FormArray([]),
      ticketPrice: [''],
      ticketNumber: [''],
      location: [''],
      isOnline: [''],
      image:[""]

    });

    this.dateTime = this.formBuilder.group({
      datetimeCtrl: new FormControl(''),
    });

  }



  submitForm(){
    const sanitizedTicketPrice = Object.assign({}, this.ticketPrice);
    const sanitizedTicketNumber = Object.assign({}, this.ticketNumber);

    this.eventForm.controls["eventDate"].setValue(this.dateTime.get('datetimeCtrl')?.value);
    this.eventForm.controls["ticketPrice"].setValue(sanitizedTicketPrice);
    this.eventForm.controls["ticketNumber"].setValue(sanitizedTicketNumber);
    const event = this.eventForm.value;
    const eventDate = new FormData();
    eventDate.append('image', this.selectedImage);
    eventDate.append('event',JSON.stringify(event));
    this.apiService.post("api/admin/events", eventDate)
      .subscribe({
        next: (response: any) => {
           
          console.log("success")
          this._router.navigateByUrl('/events');


        },
        error: (error: any) => { }
      }
      );

  }
  onImageSelected(event: any) {
    this.selectedImage = event.target.files[0];
  }

  updateTicketPrice(key: string, event: any) {
    this.ticketPrice[key] = event.target.value;
  
  }

  updateTicketNumber(key: string, event: any) {
    this.ticketNumber[key] = event.target.value;
   
  }


  
  onCheckChange(event:any) {
    const formArray: FormArray = this.eventForm.get('speakers') as FormArray;
  
    /* Selected */
    if(event.target.checked){
      // Add a new control in the arrayForm
      formArray.push(new FormControl(event.target.value));
    }
    /* unselected */
    else{
      // find the unselected element
      let i: number = 0;
      formArray.controls.forEach((ctrl:any) => {
        if(ctrl.value == event.target.value) {
          // Remove the unselected element from the arrayForm
          formArray.removeAt(i);
          return;
        }
  
        i++;
      });
    }
  }


  display: any;
  center: google.maps.LatLngLiteral = {
      lat: 24,
      lng: 12
  };
  zoom = 4;
  moveMap(event: google.maps.MapMouseEvent) {
      if (event.latLng != null) this.center = (event.latLng.toJSON());
  }
  move(event: google.maps.MapMouseEvent) {
      if (event.latLng != null) this.display = event.latLng.toJSON();
  }

  validateEvent(){
    console.log(this.title);
    this.apiService.post("api/admin/event-title/exist",this.title)
    .subscribe({
      next:(response: any)=>{
        console.log("resp " + response)
        this.titleExists=response
      },
      error:(error: any)=>{
        return null;
      }
    }
    );
}

}
