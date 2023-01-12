import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PIComponentComponent } from './pi-component.component';

describe('PIComponentComponent', () => {
  let component: PIComponentComponent;
  let fixture: ComponentFixture<PIComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PIComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PIComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
