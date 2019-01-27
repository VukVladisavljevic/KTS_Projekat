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
import {Pricelist} from "../models/pricelist";
import {PricelistService} from "../services/pricelist-service.service";

@Component({
  selector: 'app-ticket',
  templateUrl: './ticket.component.html',
  styleUrls: ['./ticket.component.css']
})
export class TicketComponent implements OnInit {

  selectedOption: string;
  ticketPrices = [];
  selectedPrice = 0;
  options = [];

  constructor(private dialog: MatDialog,
              private router: Router,
              private ticketService: TicketsService,
              private pricelistService: PricelistService) {

  }


  ngOnInit() {
    this.pricelistService.getCurrentPricelist()
      .then(response => {
        this.ticketPrices = response.map((item) => {
          return {type:item.ticketType, price: item.price};
        })
        this.options = response.map((item) => {
          if (item.ticketType === 'SINGLE') {
              return {name: "One Use", value: "One Use"};
          } else if (item.ticketType === 'MONTHLY') {
              return {name: "Monthly", value: "Monthly"};
          } else if (item.ticketType === 'YEARLY') {
              return { name: "Yearly", value: "Yearly" };
          }
        })
        console.log(response[0].price);
        this.selectedOption = this.options[0].name || "";
        this.selectedPrice = response[0].price !== null ? response[0].price : 0;
      });
  }
  onChange($event) {
    if(this.selectedOption == "One Use") {
      this.selectedPrice = _.find(this.ticketPrices, {type:'SINGLE'}).price;
    } else if (this.selectedOption == "Monthly"){
      this.selectedPrice = _.find(this.ticketPrices, {type:'MONTHLY'}).price;
    } else if (this.selectedOption == "Yearly") {
      this.selectedPrice =_.find(this.ticketPrices, {type:'YEARLY'}).price;
    }
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
    ticket.price = this.selectedPrice;
    this.ticketService.buyOneWayTicket(ticket)
    .then(response => {
        alert("One-way ticket successfully bought! ");
      });
  }

  buyMonthlyTicket() {
    var ticket: Ticket = new Ticket(localStorage.getItem('Authentication-Token'), "monthly", null, null, null, false);
    ticket.price = this.selectedPrice;
    this.ticketService.buyMultipleUseTicket(ticket)
      .then(response => {
        alert("Monthly ticket successfully bought! ");
      });
  }

  buyYearlyTicket() {
    var ticket: Ticket = new Ticket(localStorage.getItem('Authentication-Token'), "yearly", null, null, null, false);
    ticket.price = this.selectedPrice;
    this.ticketService.buyMultipleUseTicket(ticket)
      .then(response => {
        alert("Yearly ticket successfully bought! ");
      });
  }

}
