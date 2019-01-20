import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogConfig, MatDialogRef, MatRadioChange} from '@angular/material';
import {TimeScheduleService} from '../../services/time-schedule-service.service';
import {Departure} from '../../models/departure';
import {TimeScheduleItemModel} from '../../models/timeScheduleItem.model';
import {log} from 'util';
import {logger} from 'codelyzer/util/logger';
import {TicketsService} from '../../services/tickets.service';
import {Ticket} from '../../models/ticket';

@Component({
  selector: 'app-list-existing-departures-dialog',
  templateUrl: './list-owned-tickets-dialog.component.html',
  styleUrls: ['./list-owned-tickets-dialog.component.css']
})
export class ListOwnedTicketsDialogComponent implements OnInit {

  form: FormGroup;
  public tickets: Ticket[];

  constructor(
    private fb: FormBuilder,
    private ticketService: TicketsService,
    private dialogRef: MatDialogRef<ListOwnedTicketsDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any)  {

  }


  ngOnInit() {
    this.ticketService.getOwnedTickets(this.data.token)
      .then(response => {
        console.log(response);
        this.tickets = response as Ticket[];
      });
  }

  activateTicket(item: Ticket): void {

    this.ticketService.activateTicket(item)
      .then(response => {
        console.log(response);
        let ticketGot = response as Ticket;
        item.active = ticketGot.active;
        item.startTime = ticketGot.startTime;
        item.endTime = ticketGot.endTime;
      });
  }

  archiveTicket(item: Ticket): void {

    this.ticketService.archiveTicket(item)
      .then(response => {
        //this.tickets.splice()
      });
  }


  close() {
    this.dialogRef.close();
  }

}
