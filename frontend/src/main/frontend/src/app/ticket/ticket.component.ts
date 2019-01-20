import { Component, OnInit } from '@angular/core';
import {MatDialog, MatDialogConfig} from '@angular/material';
import {Router} from '@angular/router';
import {TicketsService} from '../services/tickets.service';
import {TimeScheduleService} from '../services/time-schedule-service.service';
import {TimeScheduleItemModel} from '../models/timeScheduleItem.model';
import {AddDepartureDialogComponent} from '../timetable/add-departure-dialog/add-departure-dialog.component';
import {ListExistingDeparturesDialogComponent} from '../timetable/list-existing-departures-dialog/list-existing-departures-dialog.component';
import {Departure} from '../models/departure';
import {Ticket} from '../models/ticket';
import {ListOwnedTicketsDialogComponent} from './list-owned-tickets-dialog/list-owned-tickets-dialog';

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  selectedOption: string;

  options = [
    { name: "One Use", value: "One Use" },
    { name: "Monthly", value: "Monthly" },
    { name: "Yearly", value: "Yearly" }
  ];

  constructor(private dialog: MatDialog, private router: Router, private ticketService: TicketsService) {

  }


  ngOnInit() {
    this.selectedOption = "One Use"
    // this.ticke.getTimeSchedules()
    //   .then(response => {
    //     //this.scheduleItems = response as TimeScheduleItemModel[];
    //   });
  }

  openOwnedTicketsDialog(): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
      token: localStorage.getItem('Authentication-Token')
    };

    this.dialog.open(ListOwnedTicketsDialogComponent, dialogConfig);


  }

  buyTicket() {
    if(this.selectedOption == "One Use") {
      this.buyOneWayTicket();
    } else if (this.selectedOption == "Monthly"){
      this.buyMonthlyTicket();
    } else if (this.selectedOption == "Yearly") {
      this.buyYearlyTicket();
    } else {
      alert("Something went wrong");
    }
  }

  buyOneWayTicket() {
    var ticket: Ticket = new Ticket(localStorage.getItem('Authentication-Token'), "oneUse", null, null, null, false);
    this.ticketService.buyOneWayTicket(ticket)
    .then(response => {
        alert("One-way ticket successfully bought! ");
      });
  }

  buyMonthlyTicket() {
    var ticket: Ticket = new Ticket(localStorage.getItem('Authentication-Token'), "monthly", null, null, null, false);
    this.ticketService.buyMultipleUseTicket(ticket)
      .then(response => {
        alert("Monthly ticket successfully bought! ");
      });
  }

  buyYearlyTicket() {
    var ticket: Ticket = new Ticket(localStorage.getItem('Authentication-Token'), "yearly", null, null, null, false);
    this.ticketService.buyMultipleUseTicket(ticket)
      .then(response => {
        alert("Yearly ticket successfully bought! ");
      });
  }

}
