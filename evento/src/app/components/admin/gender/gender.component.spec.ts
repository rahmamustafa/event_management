import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GenderComponent } from './gender.component';

describe('GenderComponent', () => {
  let component: GenderComponent;
  let fixture: ComponentFixture<GenderComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GenderComponent]
    });
    fixture = TestBed.createComponent(GenderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
