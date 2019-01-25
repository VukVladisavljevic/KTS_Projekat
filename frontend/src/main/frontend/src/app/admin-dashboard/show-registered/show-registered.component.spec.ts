import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ShowRegisteredComponent } from './show-registered.component';

describe('ShowRegisteredComponent', () => {
  let component: ShowRegisteredComponent;
  let fixture: ComponentFixture<ShowRegisteredComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ShowRegisteredComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ShowRegisteredComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
