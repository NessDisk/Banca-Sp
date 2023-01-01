import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MovTransferComponent } from './mov-transfer.component';

describe('MovTransferComponent', () => {
  let component: MovTransferComponent;
  let fixture: ComponentFixture<MovTransferComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MovTransferComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MovTransferComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
