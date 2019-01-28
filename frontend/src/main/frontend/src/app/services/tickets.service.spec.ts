import { TestBed } from '@angular/core/testing';

import { TicketsService } from '../services/tickets.service';
import {HttpClient} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Ticket} from '../models/ticket';

describe('TicketsService', () => {

  let ticketsService: TicketsService;
  let http: HttpClient;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TicketsService]
    });

    ticketsService = TestBed.get(TicketsService);
    http = TestBed.get(HttpClient);
    httpMock = TestBed.get(HttpTestingController);
  });


  afterEach(() => {
    httpMock.verify();
  });

  fit('should be created', () => {
    expect(ticketsService).toBeTruthy();
  });

  fit('should buy one use ticket', () => {
    const oneUseTicket: Ticket = {
      "id": "1",
      "token":"token1",
      "ticketType":"oneUse",
      "startTime":null,
      "endTime":null,
      "active":null,
      "price":null
    };

    ticketsService.buyOneWayTicket(oneUseTicket).then(data => {
      expect(data.token).toBe("token1");
      expect(data.ticketType).toBe("oneUse");
    });

    // ticketsService.getOwnedTickets("token1")


    const urlPath = "http://localhost:8080/api/ticket/create";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      'id': 1,
      "token":"token1",
      "ticketType":"oneUse",
      "startTime":!null,
      "endTime":!null,
      "active":!null,
      "price":null
    };
    req.flush(retValAdd);

    httpMock.verify();

  });

  fit('should buy monthly ticket', () => {
    const multipleUseTicket: Ticket = {
      "id": "1",
      "token":"token1",
      "ticketType":"Monthly",
      "startTime":null,
      "endTime":null,
      "active":null,
      "price":null
    };

    ticketsService.buyMultipleUseTicket(multipleUseTicket).then(data => {
      expect(data.active).toBe(true);
      expect(data.token).toBe("token1");
    });

    const urlPath = "http://localhost:8080/api/ticket/createMultipleUse";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      'id': 1,
      "token":"token1",
      "ticketType":"Monthly",
      "startTime":!null,
      "endTime":!null,
      "active":!null,
      "price":null
    };
    req.flush(retValAdd);

    httpMock.verify();

  });

  fit('should buy yearly ticket', () => {
    const multipleUseTicket: Ticket = {
      "id": "1",
      "token":"token1",
      "ticketType":"Yearly",
      "startTime":null,
      "endTime":null,
      "active":null,
      "price":null
    };

    ticketsService.buyMultipleUseTicket(multipleUseTicket).then(data => {
      expect(data.active).toBe(!null);
    });

    const urlPath = "http://localhost:8080/api/ticket/createMultipleUse";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      'id': 1,
      "token":"token1",
      "ticketType":"Yearly",
      "startTime":!null,
      "endTime":!null,
      "active":!null,
      "price":null
    };
    req.flush(retValAdd);

    httpMock.verify();

  });

  fit('should activate one use ticket', () => {
    const oneUseTicket: Ticket = {
      "id": "1",
      "token":"token1",
      "ticketType":"oneUse",
      "startTime":null,
      "endTime":null,
      "active":null,
      "price":null
    };

    ticketsService.activateTicket(oneUseTicket).then(data => {
      expect(data.active).toBe(true);
    });

    // ticketsService.getOwnedTickets("token1")


    const urlPath = "http://localhost:8080/api/ticket/activate";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      'id': 1,
      "token":"token1",
      "ticketType":"oneUse",
      "startTime":!null,
      "endTime":!null,
      "active":true,
      "price":null
    };
    req.flush(retValAdd);

    httpMock.verify();

  });

  fit('should get all tickets', () => {

    ticketsService.getOwnedTickets("token1").then(response => {
      console.log(response);
    });


    const urlPath = "http://localhost:8080/api/ticket/token1";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');
    const retValAdd = [Ticket];

    req.flush(retValAdd);

    httpMock.verify();

  });

});
