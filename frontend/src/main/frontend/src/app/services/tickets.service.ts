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

  public getOwnedTickets(token: String) {
    return this.http.get("http://localhost:8080/api/ticket/"+token).toPromise();
  }

  public buyOneWayTicket(ticket: Ticket) {
    return this.http.post<Ticket>("http://localhost:8080/api/ticket/create", ticket).toPromise();
  }

  public activateTicket(ticket: Ticket) {
    return this.http.post<Ticket>("http://localhost:8080/api/ticket/activate", ticket).toPromise();
  }

  public archiveTicket(ticket: Ticket) {
    return this.http.delete("http://localhost:8080/api/ticket/" + ticket.id).toPromise();
  }

  public buyMultipleUseTicket(ticket: Ticket) {
    return this.http.post<Ticket>("http://localhost:8080/api/ticket/createMultipleUse", ticket).toPromise();
  }


}
