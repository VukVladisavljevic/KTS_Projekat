import { TestBed } from '@angular/core/testing';

import { StationsService } from './stations.service';
import {TimeScheduleService} from './time-schedule-service.service';
import {HttpClient} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Departure} from '../models/departure';
import {LinesService} from './lines.service';
import {StationModel} from '../models/station.model';

describe('StationsService', () => {

  let stationService: StationsService;
  let http: HttpClient;
  let httpMock: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [StationsService]
    });

    stationService = TestBed.get(StationsService);
    http = TestBed.get(HttpClient);
    httpMock = TestBed.get(HttpTestingController);
  });


  afterEach(() => {
    httpMock.verify();
  });

  fit('should be created', () => {
    expect(stationService).toBeTruthy();
  });


  fit('should add one station', () => {


    stationService.addStation("stanica1").then(data => {
      expect(data).toBeDefined();
    });

    const urlPath = "http://localhost:8080/api/station";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      "name":"stanica1",
      "address": "adresa1"
    }

    req.flush(retValAdd);
    httpMock.verify();

  });

  fit('should get all stations', () => {

    stationService.getStations().then(data => {
      expect(data[0].name).toBe("stanica1");
      expect(data[1].name).toBe("stanica2");
    });

    const urlPath = "http://localhost:8080/api/stations";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');
    const retValAdd = [{
      "name":"stanica1",
      "address":"adresa1"
    },
      {"name":"stanica2",
      "address":"adresa2"
      }];
    req.flush(retValAdd);
    httpMock.verify();

  });
});
