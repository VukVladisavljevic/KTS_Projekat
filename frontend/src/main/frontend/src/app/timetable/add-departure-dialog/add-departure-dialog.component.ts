import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormGroup} from '@angular/forms';
import {TimeScheduleService} from '../../services/time-schedule-service.service';
import {Departure} from '../../models/departure';



@Component({
  selector: 'app-add-departure-dialog',
  templateUrl: './add-departure-dialog.component.html',
  styleUrls: ['./add-departure-dialog.component.css']
})
export class AddDepartureDialogComponent implements OnInit {

  form: FormGroup;
  public time;
  public model;
  private lineName;

  constructor(
    private fb: FormBuilder,
    private scheduleService: TimeScheduleService,
    private dialogRef: MatDialogRef<AddDepartureDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any)  {}



  ngOnInit() {

    this.time = {hour: 12, minute: 0};
    this.model = 1;
    this.lineName = this.data.lineName;
  }



  save() {
    this.form = this.fb.group({
      time: this.time,
      model: this.model,
      lineName: this.data.lineName
    });


    let time : string = ("0" + this.time.hour).slice(-2) + ":" + ("0" + this.time.minute).slice(-2);

    var dep: Departure = new Departure(time, this.model, this.lineName);

    this.scheduleService.addDeparture(dep)
      .then(response => {
        //alert("odgovor" + response);
      });

    this.dialogRef.close(this.form.value);
  }



  close() {
    this.dialogRef.close();
  }


}
