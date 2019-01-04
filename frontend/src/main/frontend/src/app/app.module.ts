import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HomeComponent} from './home/home.component';
import {NavbarComponent} from './navbar/navbar.component';
import {LoginComponent} from './login/login.component';
import {RegistrationComponent} from './registration/registration.component';
import {RouterModule} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms'
import {TimetableComponent} from './timetable/timetable.component';
import {HttpClientModule} from '@angular/common/http';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import {ToasterModule} from 'angular2-toaster';
import {BsDropdownModule} from 'ngx-bootstrap/dropdown';

import {MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, MatRippleModule} from '@angular/material';

import {AddDepartureDialogComponent} from './timetable/add-departure-dialog/add-departure-dialog.component';
import {ListExistingDeparturesDialogComponent} from './timetable/list-existing-departures-dialog/list-existing-departures-dialog.component';
import {BrowserAnimationsModule} from '@angular/platform-browser/animations';
import {AuthService} from './services/auth/auth.service';
import {JwtService} from './services/auth/jwt.service';

@NgModule({
  declarations: [
    AppComponent,
    HomeComponent,
    NavbarComponent,
    LoginComponent,
    RegistrationComponent,
    TimetableComponent,
    AddDepartureDialogComponent,
    ListExistingDeparturesDialogComponent
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
    BsDropdownModule.forRoot(),
    ToasterModule
  ],
  providers: [
    HttpClientModule, JwtService, AuthService
  ],
  bootstrap: [AppComponent],
  entryComponents: [AddDepartureDialogComponent, ListExistingDeparturesDialogComponent]
})
export class AppModule { }
