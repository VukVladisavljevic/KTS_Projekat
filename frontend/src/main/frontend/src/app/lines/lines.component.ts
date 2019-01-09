import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StationsService} from '../services/stations.service';
import { MatTableModule, MatTableDataSource } from '@angular/material';
import * as _ from 'lodash';
import {LinesService} from "../services/lines.service";
import {LineModel} from '../models/line.model';
import {Departure} from "../models/departure";

@Component({
  selector: 'app-lines',
  templateUrl: './lines.component.html',
  styleUrls: ['./lines.component.css']
})
export class LinesComponent implements OnInit {
  displayedColumns: string[] = ['address', 'lat', 'long'];
  form: FormGroup;
  private lineName;
  private stations;
  private lineStations;
  private selectedStation;
  private dataSource;

  constructor(private fb: FormBuilder,
              private stationsService: StationsService,
              private linesService: LinesService) {
    this.form = this.fb.group({
      time: this.lineName,
      model: this.stations
    });
  }


  ngOnInit() {
    this.lineStations = [];
    this.dataSource = new MatTableDataSource(this.lineStations);
    this.stationsService.getStations()
      .then(response => {
        this.stations = response;
        console.log(response);
      });
  }

  addStation() {
    _.remove(this.stations, { id: this.selectedStation.id});
    this.lineStations.push(this.selectedStation);
    this.selectedStation = null;
    this.dataSource._updateChangeSubscription()
  }

  addLine() {
    let line: LineModel = new LineModel(this.lineName, this.lineStations);
    this.linesService.addLine(line);
  }

}
