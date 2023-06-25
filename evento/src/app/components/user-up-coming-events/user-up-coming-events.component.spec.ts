import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UserUpComingEventsComponent } from './user-up-coming-events.component';

describe('UserUpComingEventsComponent', () => {
  let component: UserUpComingEventsComponent;
  let fixture: ComponentFixture<UserUpComingEventsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [UserUpComingEventsComponent]
    });
    fixture = TestBed.createComponent(UserUpComingEventsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
