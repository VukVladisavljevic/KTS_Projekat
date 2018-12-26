import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ListExistingDeparturesDialogComponent } from './list-existing-departures-dialog.component';

describe('ListExistingDeparturesDialogComponent', () => {
  let component: ListExistingDeparturesDialogComponent;
  let fixture: ComponentFixture<ListExistingDeparturesDialogComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ListExistingDeparturesDialogComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ListExistingDeparturesDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
