import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {StationsService} from "../../services/stations.service";
import {LinesService} from "../../services/lines.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {LineModel} from "../../models/line.model";

@Component({
  selector: 'app-add-station',
  templateUrl: './add-station.component.html',
  styleUrls: ['./add-station.component.css']
})
export class AddStationComponent implements OnInit {

  form: FormGroup;
  private stationName = "";
  private longitude;
  private latitude;
  private stationAddress = "";

  constructor(private fb: FormBuilder,
              private stationsService: StationsService,
              private linesService: LinesService,
              private dialogRef: MatDialogRef<AddStationComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.form = this.fb.group({
      stationName: ['', Validators.required ],
      stationAddress: ['', Validators.required ],
    });
  }

  ngOnInit() {
    this.longitude = this.data.longitude;
    this.latitude = this.data.latitude;
  }

  addStation() {
    let station = {name: this.stationName, address:this.stationAddress, lat: this.latitude, lng:this.longitude};
    this.stationsService.addStation(station);
    this.dialogRef.close();
  }
}
