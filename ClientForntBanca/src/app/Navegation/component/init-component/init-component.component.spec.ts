import { ComponentFixture, TestBed } from '@angular/core/testing';

import { InitComponentComponent } from './init-component.component';

describe('InitComponentComponent', () => {
  let component: InitComponentComponent;
  let fixture: ComponentFixture<InitComponentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ InitComponentComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(InitComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
