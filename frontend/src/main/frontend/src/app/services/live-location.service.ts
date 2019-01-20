import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class LiveLocationService {

  constructor(private http:HttpClient) { }

  public getLines() {
    return this.http.get("http://localhost:8080/api/lines").toPromise();
  }
}
