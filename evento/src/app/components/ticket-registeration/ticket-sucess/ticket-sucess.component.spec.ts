import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TicketSucessComponent } from './ticket-sucess.component';

describe('TicketSucessComponent', () => {
  let component: TicketSucessComponent;
  let fixture: ComponentFixture<TicketSucessComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TicketSucessComponent]
    });
    fixture = TestBed.createComponent(TicketSucessComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
