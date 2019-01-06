import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { RouterModule } from '@angular/router';
import { FormBuilder, FormGroup } from '@angular/forms'
import { ReactiveFormsModule } from '@angular/forms';
import { TimetableComponent} from './timetable/timetable.component';
import { HttpClientModule } from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';

import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatRippleModule,
  MatFormFieldModule,
  MatInputModule, MatButtonModule
} from '@angular/material';

import { AddDepartureDialogComponent } from './timetable/add-departure-dialog/add-departure-dialog.component';
import { AddPricelistDialogComponent } from './pricelist/add-pricelist-dialog/add-pricelist-dialog.component';
import { ListExistingDeparturesDialogComponent } from './timetable/list-existing-departures-dialog/list-existing-departures-dialog.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { AddPricelistDialogComponentComponent } from './add-pricelist-dialog-component/add-pricelist-dialog-component.component';
import {ShowCurrentPricelistDialogComponent} from './pricelist/show-current-pricelist/show-current-pricelist-dialog.component';
import { ShowCurrentPricelistComponentDialogComponent } from './show-current-pricelist-component-dialog/show-current-pricelist-component-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginComponent,
    RegistrationComponent,
    TimetableComponent,
    AddPricelistDialogComponent,
    AddDepartureDialogComponent,
    ListExistingDeparturesDialogComponent,
    AddPricelistDialogComponentComponent,
    ShowCurrentPricelistDialogComponent,
    ShowCurrentPricelistComponentDialogComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule,
    ReactiveFormsModule,
    HttpClientModule,
    MatDialogModule,
    BrowserAnimationsModule,
    MatButtonModule,
    MatFormFieldModule,
    MatInputModule,
    MatRippleModule,
    NgbModule,
    AppRoutingModule
  ],
  providers: [
    HttpClientModule,
  { provide: MatDialogRef, useValue: {} }
, { provide: MAT_DIALOG_DATA, useValue: [] }
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddDepartureDialogComponent, ListExistingDeparturesDialogComponent, AddPricelistDialogComponent]
})
export class AppModule { }
