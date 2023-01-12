import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovStatusComponent } from './mov-status.component';

describe('MovStatusComponent', () => {
  let component: MovStatusComponent;
  let fixture: ComponentFixture<MovStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovStatusComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
