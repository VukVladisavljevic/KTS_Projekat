import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Injectable({
  providedIn: 'root'
})
export class LinesMapService {

  constructor(private http:HttpClient) { }

  public getLineStations() {
    return this.http.get("http://localhost:8080/api/lines/map").toPromise();
  }
}
