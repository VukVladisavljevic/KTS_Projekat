import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {PricelistService} from '../services/pricelist-service.service';
import {MatTableModule, MatTableDataSource, MatDialogConfig} from '@angular/material';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import * as _ from 'lodash';
import {Pricelist} from '../models/pricelist';
import {AddPricelistDialogComponent} from "../pricelist/add-pricelist-dialog/add-pricelist-dialog.component";

@Component({
  selector: 'app-pricelist',
  templateUrl: './pricelist.component.html',
  styleUrls: ['./pricelist.component.css']
})
export class PricelistComponent implements OnInit {
  private pricelists;

  constructor(private pricelistService: PricelistService,
              private dialog: MatDialog) {
  }


  ngOnInit() {
    this.pricelistService.getAllPricelists()
      .then(response => {
        this.pricelists = response;
      });
  }
  openAddPricelistModal(): void {
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    //dialogConfig.data = _.cloneDeep(this.stations);
    this.dialog.open(AddPricelistDialogComponent, dialogConfig);
  }

}
