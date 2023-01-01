import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductChangeStatusComponent } from './product-change-status.component';

describe('ProductChangeStatusComponent', () => {
  let component: ProductChangeStatusComponent;
  let fixture: ComponentFixture<ProductChangeStatusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ProductChangeStatusComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ProductChangeStatusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
