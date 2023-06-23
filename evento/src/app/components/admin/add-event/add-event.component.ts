import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { ApiService } from 'src/app/services/api.service';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  constructor( private formBuilder:FormBuilder , private apiService:ApiService){}

  eventForm :FormGroup;
  dateTime: FormGroup

  isOnline:boolean;
  selectedImage: File;

  categeories:string[] =["Sport","Science"];
  selectedCategory:string;

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
      next: response => {
         console.log("all cate" + response);
         this.categeories = response.forEach((cat:any) => cat.get('type'));
      },
      error: error => { }
    }
    );

    this.apiService.get("speakers/all-speakers")
    .subscribe({
      next: response => {
         this.speakers = response;
      },
      error: error => { }
    }
    );


    this.eventForm = this.formBuilder.group({
      // title: ['', [Validators.required, Validators.minLength(3), Validators.maxLength(20)]],
      // description: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(20)]],
      // category: ['', [Validators.required, Validators.email]],
      // time: ['', [Validators.required, Validators.pattern("^[0][1][0125][0-9]{8}$")]],
      // speakers: ['', [Validators.required]],
      // country: ['', [Validators.required]],
      // gender: ['', [Validators.required]],
      // birthDate: ['', [Validators.required]]

      title: [''],
      description: [''],
      category: [''],
      eventDate: [new FormControl('')],
      speakers: new FormArray([]),
      ticketPrice: [],
      ticketNumber: [],
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
    this.apiService.post("apii/events", eventDate)
      .subscribe({
        next: response => {
           
          console.log("success")

        },
        error: error => { }
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



}
