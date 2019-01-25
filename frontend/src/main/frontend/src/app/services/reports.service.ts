import { Injectable } from '@angular/core';
import { catchError } from 'rxjs/operators';
import { BadRequestError } from '../shared/errors/bad-request-error';
import { NotFoundError } from '../shared/errors/not-found-error';
import { ForbiddenError } from '../shared/errors/forbidden-error';
import { AppError } from '../shared/errors/app-error';
import { HttpClient } from '@angular/common/http';
import {throwError as observableThrowError,  Observable } from 'rxjs';
import { RequestDate } from '../models/requestDate';

@Injectable({
  providedIn: 'root'
})
export class ReportsService {

  constructor(private http: HttpClient) { }

  getReports(requestDate : RequestDate): Observable<any> {
    return this.http.post<any>(`http://localhost:8080/api/reports/get_total`, requestDate).pipe(
      catchError(this.handleErrors));
  }

  private handleErrors(response: Response) {
    if(response.status === 400)
      return observableThrowError(new BadRequestError());
    else if(response.status === 404)
      return observableThrowError(new NotFoundError());
    else if(response.status === 403)
      return observableThrowError(new ForbiddenError());
    return observableThrowError(new AppError(response));
  }
}
