import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import {TimeScheduleItemModel} from '../models/timeScheduleItem.model';
import {MatDialog, MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {Departure} from '../models/departure';
import {Observable} from 'rxjs';


const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class TimeScheduleService {

  constructor(private http:HttpClient) {}



  private userUrl = '/api';

  public getTimeSchedules() {
    return this.http.get("http://localhost:8080/api/lines").toPromise();
  }

  public addDeparture(departure: Departure) {
    return this.http.post<Departure>("http://localhost:8080/api/time-schedule/create", departure).toPromise();
  }

  public getDepartures(lineName: string, day: string) {
    return this.http.get("http://localhost:8080/api/time-schedule/line/"+lineName+"/"+day).toPromise();
  }

  public deleteDeparture(lineName: string, day: string, index) {
    return this.http.delete("http://localhost:8080/api/time-schedule/line/"+lineName+"/"+day+"/"+index).toPromise();
  }

}
