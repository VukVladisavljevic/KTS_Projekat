import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {StationsService} from '../services/stations.service';
import {MatTableModule, MatTableDataSource, MatDialogConfig} from '@angular/material';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import * as _ from 'lodash';
import {LinesService} from "../services/lines.service";
import {LineModel} from '../models/line.model';
import {AddLineComponent} from "./add-line/add-line.component";

@Component({
  selector: 'app-lines',
  templateUrl: './lines.component.html',
  styleUrls: ['./lines.component.css']
})
export class LinesComponent implements OnInit {
  private lines;
  private stations;

  constructor(private stationsService: StationsService,
              private linesService: LinesService,
              private dialog: MatDialog) {
  }


  ngOnInit() {
    this.stationsService.getStations()
      .then(response => {
        this.stations = response;
      });
    this.linesService.getLines()
      .then(response => {
        this.lines = response;
      });
  }
  openAddLineModal(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = _.cloneDeep(this.stations);
    this.dialog.open(AddLineComponent, dialogConfig);
  }

  deleteLine(item) {
    _.remove(this.lines, { idLine: item.idLine});
    this.linesService.deleteLine(item);
  }

}
