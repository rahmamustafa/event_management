import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TotalRevenueComponent } from './total-revenue.component';

describe('TotalRevenueComponent', () => {
  let component: TotalRevenueComponent;
  let fixture: ComponentFixture<TotalRevenueComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [TotalRevenueComponent]
    });
    fixture = TestBed.createComponent(TotalRevenueComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
