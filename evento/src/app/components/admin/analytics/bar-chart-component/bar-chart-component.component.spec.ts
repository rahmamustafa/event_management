import { ComponentFixture, TestBed } from '@angular/core/testing';

import { BarChartComponentComponent } from './bar-chart-component.component';

describe('BarChartComponentComponent', () => {
  let component: BarChartComponentComponent;
  let fixture: ComponentFixture<BarChartComponentComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [BarChartComponentComponent]
    });
    fixture = TestBed.createComponent(BarChartComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
