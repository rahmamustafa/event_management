import { Component, OnInit } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent implements OnInit {

  constructor( private formBuilder:FormBuilder){}

  selectedDateTime: Date = new Date();

  categeories:string[] =["Sport","Science"];
  selectedCategeory:string;

  speakerForm: FormGroup;
  speakers: string[] = ['Item 1', 'Item 2', 'Item 3'];
  selectedSpeakers: string[] = [];


  ngOnInit() {
    this.speakerForm = this.formBuilder.group({
      speakerGroup: this.formBuilder.array([])
    });
  }

  dateTime = new FormGroup({
    datetimeCtrl: new FormControl(''),
  });


  get speakerGroup(): FormArray {
    return this.speakerForm.get('speakerGroup') as FormArray;
  }

  collectCheckedSpeakers() {
    this.selectedSpeakers = this.speakerGroup.value;
    console.log(this.selectedSpeakers);
  }
  submitForm(){
    this.collectCheckedSpeakers();
  }

}
