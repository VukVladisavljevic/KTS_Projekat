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
import { ReportsComponent } from './admin-dashboard/reports/reports.component';
import { AuthGuard } from './shared/guards/auth.guard';
import { AnonymousGuard } from './shared/guards/anonymous.guard';
import { AdminGuard } from './shared/guards/admin.guard';
import { ControllerGuard } from './shared/guards/controller.guard';
import { JwtService } from './services/auth/jwt.service';

const routes: Routes = [
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  { path: 'login', component: LoginComponent, canActivate: [AnonymousGuard]},
  { path: 'register', component: RegistrationComponent, canActivate: [AnonymousGuard]},
  { path: 'timetable', component: TimetableComponent },
  { path: 'tickets', component: TicketComponent},
  { path: 'addpricelist', component: AddPricelistDialogComponent, canActivate: [AdminGuard, ControllerGuard]},
  { path: 'showpricelist', component: ShowCurrentPricelistDialogComponent},
  { path: 'lines-map', component: LinesMapComponent },
  { path: 'lines', component: LinesComponent, canActivate: [AdminGuard]},
  { path: 'live-location', component: LiveLocationComponent },
  { path: 'stations', component: StationsComponent, canActivate: [AdminGuard] },
  { path: 'pricelist', component: PricelistComponent, canActivate: [AdminGuard] },
  { path: 'admin-dashboard', component: AdminDashboardComponent,
    children: [
      { path: 'register-new', component: RegisterEmployeeComponent },
      { path: 'show-registered', component: ShowRegisteredComponent },
      { path: 'reports', component: ReportsComponent }
    ],
    canActivate: [AdminGuard]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
