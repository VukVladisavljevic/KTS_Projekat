import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material";
import {FormBuilder, FormGroup} from '@angular/forms';
import {PricelistService} from '../../services/pricelist-service.service';
import {Pricelist} from '../../models/pricelist';



@Component({
  selector: 'app-add-pricelist-dialog',
  templateUrl: './add-pricelist-dialog.component.html',
  styleUrls: ['./add-pricelist-dialog.component.css']
})
export class AddPricelistDialogComponent implements OnInit {

  form: FormGroup;
  public startDate;
  public endDate;
  public ticketType;
  private price;

  constructor(
    private fb: FormBuilder,
    private pricelistService: PricelistService,
    private dialogRef: MatDialogRef<AddPricelistDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any)  {}



  ngOnInit() {

    this.startDate = {year: 2019, month: 6, day: 3};
    this.endDate = {year: 2019, month: 6, day: 3};
    this.ticketType = 1;
    this.price = 0;
  }



  save() {
    this.form = this.fb.group({
      startDate: this.startDate,
      endDate: this.endDate,
      ticketType: this.ticketType,
      price: this.price
    });


    //let time : string = ("0" + this.time.hour).slice(-2) + ":" + ("0" + this.time.minute).slice(-2);

    if (this.ticketType === 1){
      this.ticketType = "SINGLE";
    } else if (this.ticketType === 2) {
      this.ticketType = "DAILY";
    } else {
      this.ticketType = "MONTHLY";
    }

    if (this.startDate.day < 10){
      this.startDate.day = "0" + this.startDate.day.toString();
    }

    if (this.endDate.day < 10){
      this.endDate.day = "0" + this.endDate.day.toString();
    }

    if (this.startDate.month < 10){
      this.startDate.month = "0" + this.startDate.month.toString();
    }

    if (this.endDate.month < 10){
      this.endDate.month = "0" + this.endDate.month.toString();
    }

    var startDateJson = this.startDate.year.toString() + "-" + this.startDate.month.toString() + "-" + this.startDate.day.toString() + "T00:00:00.000+0100";
    var endDateJson = this.endDate.year.toString() + "-" + this.endDate.month.toString() + "-" + this.endDate.day.toString() + "T00:00:00.0000+0100";

    var prl: Pricelist = new Pricelist(startDateJson, endDateJson, this.ticketType, this.price);

    //console.log(prl)

    this.pricelistService.addPricelist(prl)
      .then(response => {
          if (response == null){
            alert("Greska");
          } else {
            alert("Pricelist successfully added.");
          }
      });

    this.dialogRef.close(this.form.value);
  }



  close() {
    this.dialogRef.close();
  }


}
