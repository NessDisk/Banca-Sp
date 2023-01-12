import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovWithdrawComponent } from './mov-withdraw.component';

describe('MovWithdrawComponent', () => {
  let component: MovWithdrawComponent;
  let fixture: ComponentFixture<MovWithdrawComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovWithdrawComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovWithdrawComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
