import { Component } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-add-event',
  templateUrl: './add-event.component.html',
  styleUrls: ['./add-event.component.css']
})
export class AddEventComponent {
  selectedDateTime: Date = new Date();
  categeories:string[] =["Sport","Science"];
  title = 'datetimepicker';
  selectedCategeory:string;

  dateTime = new FormGroup({
    datetimeCtrl: new FormControl(''),
  });

}
