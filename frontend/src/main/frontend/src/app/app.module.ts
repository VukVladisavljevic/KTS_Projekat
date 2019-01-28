import { BrowserModule } from '@angular/platform-browser';
import { NgModule, CUSTOM_ELEMENTS_SCHEMA, ErrorHandler } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { NavbarComponent } from './navbar/navbar.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { RouterModule } from '@angular/router';
import { TimetableComponent} from './timetable/timetable.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {FormsModule} from '@angular/forms';
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

import { MatFormFieldModule, MatOptionModule, MatSelectModule , MatTableModule } from
    '@angular/material';
import {
  MatDialog,
  MatDialogRef,
  MAT_DIALOG_DATA,
  MatDialogModule,
  MatRippleModule,
  MatInputModule, MatButtonModule
} from '@angular/material';
import { TicketComponent } from './ticket/ticket.component';

import { AddDepartureDialogComponent } from './timetable/add-departure-dialog/add-departure-dialog.component';
import { AddPricelistDialogComponent } from './pricelist/add-pricelist-dialog/add-pricelist-dialog.component';
import { ListExistingDeparturesDialogComponent } from './timetable/list-existing-departures-dialog/list-existing-departures-dialog.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {ShowCurrentPricelistDialogComponent} from './pricelist/show-current-pricelist/show-current-pricelist-dialog.component';
import {ListOwnedTicketsDialogComponent} from './ticket/list-owned-tickets-dialog/list-owned-tickets-dialog';
import {LiveLocationComponent} from './live-location/live-location.component';
import { AddLineComponent } from './lines/add-line/add-line.component';
import { StationsComponent } from './stations/stations.component';
import { AddStationComponent } from './stations/add-station/add-station.component';
import { PricelistComponent } from './pricelist/pricelist.component';
import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { RegisterEmployeeComponent } from './admin-dashboard/register-employee/register-employee.component';
import { ShowRegisteredComponent } from './admin-dashboard/show-registered/show-registered.component';
import { DailyReportsComponent } from './admin-dashboard/daily-reports/daily-reports.component';
import { MonthlyReportsComponent } from './admin-dashboard/monthly-reports/monthly-reports.component';
import { YearlyReportsComponent } from './admin-dashboard/yearly-reports/yearly-reports.component';
import { ReportsComponent } from './admin-dashboard/reports/reports.component';
import { AdminGuard } from './shared/guards/admin.guard';
import { AuthGuard } from './shared/guards/auth.guard';
import { AnonymousGuard } from './shared/guards/anonymous.guard';
import { ControllerGuard } from './shared/guards/controller.guard';
import { ProfileComponent } from './profile/profile.component';
import { LocationStrategy, HashLocationStrategy } from '@angular/common';
import { JwtInterceptor } from './shared/interceptors/jwt-interceptor';


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
    ShowCurrentPricelistDialogComponent,
    RegistrationComponent,
    LinesMapComponent,
    AddLineComponent,
    LinesComponent,
    ShowCurrentPricelistDialogComponent,
    TicketComponent,
    ListOwnedTicketsDialogComponent,
    LiveLocationComponent,
    StationsComponent,
    AddStationComponent,
    PricelistComponent,
    AdminDashboardComponent,
    RegisterEmployeeComponent,
    ShowRegisteredComponent,
    DailyReportsComponent,
    MonthlyReportsComponent,
    YearlyReportsComponent,
    ReportsComponent,
    ProfileComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    RouterModule,
    HttpClientModule,
    MatDialogModule,
    MatButtonModule,
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
    AgmCoreModule.forRoot({apiKey: 'api' }),
    AgmDirectionModule,
    MatOptionModule,
    MatSelectModule,
    MatFormFieldModule,
    BrowserAnimationsModule,
    MatTableModule,
    BsDropdownModule.forRoot(),
    ToasterModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: JwtInterceptor,
      multi: true
    },
    HttpClientModule, AuthService, JwtService, GoogleMapsAPIWrapper, AdminGuard, AuthGuard, AnonymousGuard, ControllerGuard, { provide: MatDialogRef, useValue: {} }
, { provide: MAT_DIALOG_DATA, useValue: [] }
  ],
  bootstrap: [AppComponent],
  entryComponents: [ListOwnedTicketsDialogComponent, AddDepartureDialogComponent, ListExistingDeparturesDialogComponent, AddLineComponent, AddPricelistDialogComponent
    ,AddStationComponent],
  schemas: [
    CUSTOM_ELEMENTS_SCHEMA
  ]
})
export class AppModule { }
