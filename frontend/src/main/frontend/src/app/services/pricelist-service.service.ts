import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {Pricelist} from '../models/pricelist';
import {Observable} from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class PricelistService {

  constructor(private http:HttpClient) {}



  private userUrl = '/api';

  public getCurrentPricelist() {
    return this.http.get("http://localhost:8080/api/pricelist/getcurrent").toPromise();
  }

  public getAllPricelists() {
    return this.http.get("http://localhost:8080/api/pricelist/getall").toPromise();
  }

  public addPricelist(pricelist: Pricelist) {
    console.log(pricelist);
    return this.http.post<Pricelist>("http://localhost:8080/api/pricelist/create", pricelist).toPromise();
  }

}
