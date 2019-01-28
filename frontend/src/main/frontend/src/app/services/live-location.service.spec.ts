import { TestBed } from '@angular/core/testing';

import { LiveLocationService } from './live-location.service';
import {HttpClient} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Ticket} from '../models/ticket';

describe('LiveLocationService', () => {
  let locationService: LiveLocationService;
  let http: HttpClient;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LiveLocationService]
    });

    locationService = TestBed.get(LiveLocationService);
    http = TestBed.get(HttpClient);
    httpMock = TestBed.get(HttpTestingController);
  });


  afterEach(() => {
    httpMock.verify();
  });

  fit('should be created', () => {
    expect(locationService).toBeTruthy();
  });


  fit('should get all lines', () => {

    locationService.getLines().then(response => {
      expect(response).toBeDefined();
      expect(response[0].name).toBe("A1");
      expect(response[1].name).toBe("A2");
    });

    const urlPath = "http://localhost:8080/api/lines";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');

    const retValAdd = [
      {
        "name": "A1"
      },
      {
        "name": "A2"
      }
    ];

    expect(retValAdd.length).toBe(2);
    req.flush(retValAdd);
    httpMock.verify();
  });
});



