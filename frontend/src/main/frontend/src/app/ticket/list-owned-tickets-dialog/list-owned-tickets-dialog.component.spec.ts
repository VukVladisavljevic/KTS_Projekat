import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListOwnedTicketsDialogComponent } from './list-owned-tickets-dialog';

describe('ListOwnedTicketsDialogComponent', () => {
  let component: ListOwnedTicketsDialogComponent;
  let fixture: ComponentFixture<ListOwnedTicketsDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListOwnedTicketsDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListOwnedTicketsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
