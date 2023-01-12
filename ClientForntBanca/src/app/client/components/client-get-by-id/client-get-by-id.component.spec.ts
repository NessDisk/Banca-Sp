import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ClientGetByIDComponent } from './client-get-by-id.component';

describe('ClientGetByIDComponent', () => {
  let component: ClientGetByIDComponent;
  let fixture: ComponentFixture<ClientGetByIDComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ ClientGetByIDComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(ClientGetByIDComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
