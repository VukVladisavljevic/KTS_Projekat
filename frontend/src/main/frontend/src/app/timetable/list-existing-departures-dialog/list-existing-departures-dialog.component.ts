import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogConfig, MatDialogRef, MatRadioChange} from '@angular/material';
import {TimeScheduleService} from '../../services/time-schedule-service.service';
import {Departure} from '../../models/departure';
import {TimeScheduleItemModel} from '../../models/timeScheduleItem.model';
import {log} from 'util';
import {logger} from 'codelyzer/util/logger';

@Component({
  selector: 'app-list-existing-departures-dialog',
  templateUrl: './list-existing-departures-dialog.component.html',
  styleUrls: ['./list-existing-departures-dialog.component.css']
})
export class ListExistingDeparturesDialogComponent implements OnInit {

  form: FormGroup;
  private time;
  public day;
  private lineName;
  public departures: string[];

  constructor(
    private fb: FormBuilder,
    private scheduleService: TimeScheduleService,
    private dialogRef: MatDialogRef<ListExistingDeparturesDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any)  {

      this.day = "1";
  }


  ngOnInit() {

    this.scheduleService.getDepartures(this.data.lineName, this.day)
      .then(response => {
        console.log(response);
        this.departures = response as string[];

      });
  }

  radioChange(event: MatRadioChange) {
   // this.departures = null;
    this.scheduleService.getDepartures(this.data.lineName, this.day)
      .then(response => {
        console.log(response);
        this.departures = response as string[];
      });
  }

  removeDeparture(item): void {
    this.scheduleService.deleteDeparture(this.data.lineName, this.day, item)
      .then(response => {
        console.log(response);
        this.departures.splice(item, 1);
      });
  }

  close() {
    this.dialogRef.close();
  }

}
