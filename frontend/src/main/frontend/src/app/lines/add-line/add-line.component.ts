import {Component, EventEmitter, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {StationsService} from '../../services/stations.service';
import {MatTableModule, MatTableDataSource, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import * as _ from 'lodash';
import {LinesService} from "../../services/lines.service";
import {LineModel} from '../../models/line.model';
import {ToasterService} from "angular2-toaster";

@Component({
  selector: 'app-add-line',
  templateUrl: './add-line.component.html',
  providers: [ToasterService],
  styleUrls: ['./add-line.component.css']
})
export class AddLineComponent implements OnInit {

  form: FormGroup;
  private lineName;
    onAdd = new EventEmitter();
  private stations;
  private lineStations;
  private selectedStation;
  private dataSource;

  constructor(private fb: FormBuilder,
              private stationsService: StationsService,
              private linesService: LinesService,
              private dialogRef: MatDialogRef<AddLineComponent>,
              private toasterService: ToasterService,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.form = this.fb.group({
      lineName: ['', Validators.required ],
      stations: this.stations
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
    this.linesService.addLine(line).then(response => {
      if(response) {
        this.onAdd.emit(response);
        this.dialogRef.close();
      } else{
        this.toasterService.pop('error', 'Error', 'Line name already taken!');
      }
    });

  }

}
