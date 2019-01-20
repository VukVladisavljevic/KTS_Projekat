import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPricelistDialogComponent } from './add-pricelist-dialog.component';

describe('AddPricelistDialogComponent', () => {
  let component: AddPricelistDialogComponent;
  let fixture: ComponentFixture<AddPricelistDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ AddPricelistDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(AddPricelistDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
