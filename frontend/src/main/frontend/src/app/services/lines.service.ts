import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LineModel} from "../models/line.model";

@Injectable({
  providedIn: 'root'
})
export class LinesService {

  constructor(private http:HttpClient) {}

  public addLine(line: LineModel) {
    return this.http.post<LineModel>("http://localhost:8080/api/line/create", line).toPromise();

  }
}
