import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from "./home/home.component";
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import { LinesMapComponent } from './lines-map/lines-map.component';
import {TimetableComponent} from './timetable/timetable.component';
import {ShowCurrentPricelistDialogComponent} from './pricelist/show-current-pricelist/show-current-pricelist-dialog.component';
import { AddPricelistDialogComponent } from './pricelist/add-pricelist-dialog/add-pricelist-dialog.component';
import {LinesComponent} from "./lines/lines.component";
import {LiveLocationComponent} from "./live-location/live-location.component";
import {TicketComponent} from './ticket/ticket.component';
import {StationsComponent} from "./stations/stations.component";
import {PricelistComponent} from "./pricelist/pricelist.component";

import { AdminDashboardComponent } from './admin-dashboard/admin-dashboard.component';
import { RegisterEmployeeComponent } from './admin-dashboard/register-employee/register-employee.component';
import { ShowRegisteredComponent } from './admin-dashboard/show-registered/show-registered.component';
import { DailyReportsComponent } from './admin-dashboard/daily-reports/daily-reports.component';
import { MonthlyReportsComponent } from './admin-dashboard/monthly-reports/monthly-reports.component';
import { YearlyReportsComponent } from './admin-dashboard/yearly-reports/yearly-reports.component';
import { ReportsComponent } from './admin-dashboard/reports/reports.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegistrationComponent },
  { path: 'timetable', component: TimetableComponent },
  { path: 'tickets', component: TicketComponent},
  { path: 'addpricelist', component: AddPricelistDialogComponent},
  { path: 'showpricelist', component: ShowCurrentPricelistDialogComponent},
  { path: 'lines-map', component: LinesMapComponent },
  { path: 'lines', component: LinesComponent },
  { path: 'live-location', component: LiveLocationComponent },
  { path: 'stations', component: StationsComponent },
  { path: 'pricelist', component: PricelistComponent },
  { path: 'admin-dashboard', component: AdminDashboardComponent,
    children: [
      { path: 'register-new', component: RegisterEmployeeComponent },
      { path: 'show-registered', component: ShowRegisteredComponent },
      /*{ path: 'show-daily', component: DailyReportsComponent },
      { path: 'show-monthly', component: MonthlyReportsComponent },
      { path: 'show-yearly', component: YearlyReportsComponent },*/
      { path: 'reports', component: ReportsComponent }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
