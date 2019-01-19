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
import {TicketComponent} from './ticket/ticket.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'timetable', component: TimetableComponent },
  { path: 'lines-map', component: LinesMapComponent },
  { path: 'lines', component: LinesComponent },
  { path: 'timetable', component: TimetableComponent },
  { path: 'tickets', component: TicketComponent},
  { path: 'addpricelist', component: AddPricelistDialogComponent},
  { path: 'showpricelist', component: ShowCurrentPricelistDialogComponent}
  ];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
