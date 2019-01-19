import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StationsService} from '../../services/stations.service';
import {MatTableModule, MatTableDataSource, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import * as _ from 'lodash';
import {LinesService} from "../../services/lines.service";
import {LineModel} from '../../models/line.model';

@Component({
  selector: 'app-add-line',
  templateUrl: './add-line.component.html',
  styleUrls: ['./add-line.component.css']
})
export class AddLineComponent implements OnInit {

  form: FormGroup;
  private lineName;
  private stations;
  private lineStations;
  private selectedStation;
  private dataSource;

  constructor(private fb: FormBuilder,
              private stationsService: StationsService,
              private linesService: LinesService,
              private dialogRef: MatDialogRef<AddLineComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.form = this.fb.group({
      time: this.lineName,
      model: this.stations
    });
  }


  ngOnInit() {
    this.lineStations = [];
    this.stations = this.data;
  }

  addStation() {
    _.remove(this.stations, { id: this.selectedStation.id});
    this.lineStations.push(this.selectedStation);
    this.selectedStation = null;
  }

  deleteStation(station) {
    _.remove(this.lineStations, { id: station.id});
    this.stations.push(station);
  }

  addLine() {
    let line: LineModel = new LineModel(this.lineName, this.lineStations);
    this.linesService.addLine(line);
    this.dialogRef.close();
  }

}
