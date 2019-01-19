import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { LinesMapComponent } from './lines-map/lines-map.component';
import {TimetableComponent} from './timetable/timetable.component';
import {LinesComponent} from "./lines/lines.component";
import {LiveLocationComponent} from "./live-location/live-location.component";

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'timetable', component: TimetableComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'lines-map', component: LinesMapComponent },
  { path: 'lines', component: LinesComponent },
  { path: 'live-location', component: LiveLocationComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
