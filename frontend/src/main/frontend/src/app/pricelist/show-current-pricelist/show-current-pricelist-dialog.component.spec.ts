import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowCurrentPricelistDialogComponent } from './show-current-pricelist-dialog.component';

describe('ShowCurrentPricelistDialogComponent', () => {
  let component: ShowCurrentPricelistDialogComponent;
  let fixture: ComponentFixture<ShowCurrentPricelistDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowCurrentPricelistDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowCurrentPricelistDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
