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

  it('should be created', () => {
    expect(locationService).toBeTruthy();
  });


  it('should get all tickets', () => {

    locationService.getLines().then(response => {
      console.log(response);
    });

    const urlPath = "http://localhost:8080/api/lines";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');


    httpMock.verify();

  });
});



