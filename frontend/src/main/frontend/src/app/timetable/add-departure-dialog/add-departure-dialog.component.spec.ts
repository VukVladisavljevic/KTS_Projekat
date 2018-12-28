import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddDepartureDialogComponent } from './add-departure-dialog.component';

describe('AddDepartureDialogComponent', () => {
  let component: AddDepartureDialogComponent;
  let fixture: ComponentFixture<AddDepartureDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddDepartureDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddDepartureDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
