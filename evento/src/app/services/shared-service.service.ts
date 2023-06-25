import { Injectable } from '@angular/core';
import { Subject } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class SharedServiceService {

  private refreshSubject = new Subject();

  refresh$ = this.refreshSubject.asObservable();

  refreshComponent(id:number) {
    this.refreshSubject.next(id);
  }
}
