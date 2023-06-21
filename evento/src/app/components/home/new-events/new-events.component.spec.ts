import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NewEventsComponent } from './new-events.component';

describe('NewEventsComponent', () => {
  let component: NewEventsComponent;
  let fixture: ComponentFixture<NewEventsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [NewEventsComponent]
    });
    fixture = TestBed.createComponent(NewEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
