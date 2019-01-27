import { TestBed } from '@angular/core/testing';

import { TimeScheduleService } from './time-schedule-service.service';
import {TicketsService} from './tickets.service';
import {HttpClient} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Ticket} from '../models/ticket';
import {Departure} from '../models/departure';
import {LinesService} from './lines.service';

describe('TimeScheduleServiceService', () => {
  let timetableService: TimeScheduleService;
  let http: HttpClient;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [TimeScheduleService]
    });

    timetableService = TestBed.get(TimeScheduleService);
    http = TestBed.get(HttpClient);
    httpMock = TestBed.get(HttpTestingController);
  });


  afterEach(() => {
    httpMock.verify();
  });

  fit('should be created', () => {
    expect(timetableService).toBeTruthy();
  });

  fit('should add one departure', () => {

    const departure: Departure = {
      "time":"22:00",
      "dayOfWeek":"1",
      "lineName":"A1"
    };

    timetableService.addDeparture(departure).then(data => {
      expect(data.time).toBe(departure.time);
    });

    const urlPath = "http://localhost:8080/api/time-schedule/create";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      "time":"22:00",
      "dayOfWeek":"1",
      "lineName":"A1"
    };
    req.flush(retValAdd);
    httpMock.verify();

  });


  fit('should get all time schedules', () => {

    timetableService.getTimeSchedules().then(response => {
      expect(response).toBe(!null);
    });

    const urlPath = "http://localhost:8080/api/lines";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');

    httpMock.verify();
  });

  fit('should get all departures for line', () => {

    timetableService.getDepartures("A1", "1").then(response => {
      console.log(response)
      // expect(response).toBe(!null);
    });


    const urlPath = "http://localhost:8080/api/time-schedule/line/A1/1";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');
    const retValAdd = [Departure];

    expect(retValAdd.length).toBe(1);
    req.flush(retValAdd);

    httpMock.verify();

  });

  fit('should delete added departure', () => {

    timetableService.deleteDeparture("A1", "1", 0).then(response => {
      // expect(response).toBe(!null);
    });


    const urlPath = "http://localhost:8080/api/time-schedule/line/A1/1/0";
    const req = httpMock.expectOne({ method: 'DELETE', url: urlPath });
    expect(req.request.method).toBe('DELETE');


    httpMock.verify();

  });

});
