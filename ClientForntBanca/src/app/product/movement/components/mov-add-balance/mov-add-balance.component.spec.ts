import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovAddBalanceComponent } from './mov-add-balance.component';

describe('MovAddBalanceComponent', () => {
  let component: MovAddBalanceComponent;
  let fixture: ComponentFixture<MovAddBalanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovAddBalanceComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovAddBalanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
