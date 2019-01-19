import {Component, Inject, OnInit} from '@angular/core';
import {FormBuilder, FormGroup} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogConfig, MatDialogRef, MatRadioChange} from '@angular/material';
import {PricelistService} from '../../services/pricelist-service.service';
import {Pricelist} from '../../models/pricelist';
import {log} from 'util';
import {logger} from 'codelyzer/util/logger';

@Component({
selector: 'app-show-current-pricelist-dialog',
templateUrl: './show-current-pricelist-dialog.component.html',
styleUrls: ['./show-current-pricelist-dialog.component.css']
})
export class ShowCurrentPricelistDialogComponent implements OnInit {

form: FormGroup;
public pricelists: Pricelist[];

constructor(
    private fb: FormBuilder,
    private pricelistService: PricelistService,
    private dialogRef: MatDialogRef<ShowCurrentPricelistDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any)  {

  }


  ngOnInit() {

    this.pricelistService.getCurrentPricelist()
      .then(response => {
        console.log(response);
        this.pricelists = response as Pricelist[];
        //this.departures = response as string[];

      });
  }

  close() {
    this.dialogRef.close();
  }

}
