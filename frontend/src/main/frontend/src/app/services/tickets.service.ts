import { Injectable } from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Departure} from '../models/departure';
import {Ticket} from '../models/ticket';

@Injectable({
  providedIn: 'root'
})
export class TicketsService {

  constructor(private http:HttpClient) {}

  private userUrl = '/api';

  public getTimeSchedules() {
    return this.http.get("http://localhost:8080/api/").toPromise();
  }

  public buyOneWayTicket(ticket: Ticket) {
    return this.http.post<Ticket>("http://localhost:8080/api/ticket/create", ticket).toPromise();
  }


}
