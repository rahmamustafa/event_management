import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketRegisterationComponent } from './ticket-registeration.component';

describe('TicketRegisterationComponent', () => {
  let component: TicketRegisterationComponent;
  let fixture: ComponentFixture<TicketRegisterationComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TicketRegisterationComponent]
    });
    fixture = TestBed.createComponent(TicketRegisterationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
