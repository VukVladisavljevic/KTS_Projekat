import { BrowserModule } from '@angular/platform-browser';
import {CUSTOM_ELEMENTS_SCHEMA, NgModule} from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { RouterModule } from '@angular/router';
import { TimetableComponent} from './timetable/timetable.component';
import { HttpClientModule } from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
import { AddDepartureDialogComponent } from './timetable/add-departure-dialog/add-departure-dialog.component';
import { ListExistingDeparturesDialogComponent } from './timetable/list-existing-departures-dialog/list-existing-departures-dialog.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import { ReactiveFormsModule } from "@angular/forms";
import { LinesMapComponent } from './lines-map/lines-map.component';
import { LinesComponent } from './lines/lines.component';
import { AgmCoreModule, GoogleMapsAPIWrapper } from '@agm/core';
import { AgmDirectionModule } from 'agm-direction';
import * as _ from 'lodash';
import {ToasterModule} from 'angular2-toaster';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';
import {AuthService} from './services/auth/auth.service';
import {JwtService} from './services/auth/jwt.service';

import {ShowCurrentPricelistDialogComponent} from './pricelist/show-current-pricelist/show-current-pricelist-dialog.component';

import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatRippleModule,
  MatFormFieldModule,
  MatInputModule, MatButtonModule, MatOptionModule, MatSelectModule, MatTableModule
} from '@angular/material';
import {AddPricelistDialogComponent} from './pricelist/add-pricelist-dialog/add-pricelist-dialog.component';
import { TicketComponent } from './ticket/ticket.component';

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
    RegistrationComponent,
    LinesMapComponent,
    LinesComponent,
    ListExistingDeparturesDialogComponent,
    AddPricelistDialogComponent,
    ShowCurrentPricelistDialogComponent,
    TicketComponent
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
    AppRoutingModule,
    ReactiveFormsModule,
    AgmCoreModule.forRoot({apiKey: 'apikey' }),
    AgmDirectionModule,
    MatOptionModule,
    MatSelectModule,
    BrowserAnimationsModule,
    MatTableModule
  ],
  providers: [
    HttpClientModule, JwtService, AuthService,
  { provide: MatDialogRef, useValue: {} }
, { provide: MAT_DIALOG_DATA, useValue: [] }
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddDepartureDialogComponent, ListExistingDeparturesDialogComponent, AddPricelistDialogComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule { }
