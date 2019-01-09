import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class StationsService {

  constructor(private http:HttpClient) {}

  public getStations() {
    return this.http.get("http://localhost:8080/api/stations").toPromise();
  }

}
