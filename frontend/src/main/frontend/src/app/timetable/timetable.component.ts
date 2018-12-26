import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import {TimeScheduleService} from '../services/time-schedule-service.service';
import {TimeScheduleItemModel} from '../models/timeScheduleItem.model';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA, MatDialogConfig} from '@angular/material';
import {AddDepartureDialogComponent} from './add-departure-dialog/add-departure-dialog.component';
import {ListExistingDeparturesDialogComponent} from './list-existing-departures-dialog/list-existing-departures-dialog.component';
import {Departure} from '../models/departure';

@Component({
  selector: 'app-timetable',
  templateUrl: './timetable.component.html',
  styleUrls: ['./timetable.component.css']
})
export class TimetableComponent implements OnInit {

  scheduleItems: TimeScheduleItemModel[];

  constructor(private dialog: MatDialog, private router: Router, private scheduleService: TimeScheduleService) {

  }


  ngOnInit() {
    this.scheduleService.getTimeSchedules()
      .then(response => {
        this.scheduleItems = response as TimeScheduleItemModel[];
      });
  }


  openAddDepartureDialog(item): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
      "lineName": item.name
    };

    const dialogRef = this.dialog.open(AddDepartureDialogComponent, dialogConfig);


  }

  openDeparturesListDialog(item): void {
    const dialogConfig = new MatDialogConfig();

    dialogConfig.disableClose = true;
    dialogConfig.autoFocus = true;

    dialogConfig.data = {
      "lineName": item.name,
      "day":"5"
    };

   // this.dialog.open(ListExistingDeparturesDialogComponent, dialogConfig);

    const dialogRef = this.dialog.open(ListExistingDeparturesDialogComponent, dialogConfig);

  }
}
