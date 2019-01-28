import { TestBed } from '@angular/core/testing';

import { LinesService } from './lines.service';
import {HttpClient} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Departure} from '../models/departure';
import {LineModel} from '../models/line.model';

describe('LinesService', () => {
  let linesService: LinesService;
  let http: HttpClient;
  let httpMock: HttpTestingController;

  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [LinesService]
    });

    linesService = TestBed.get(LinesService);
    http = TestBed.get(HttpClient);
    httpMock = TestBed.get(HttpTestingController);
  });


  afterEach(() => {
    httpMock.verify();
  });

  fit('should be created', () => {
    expect(linesService).toBeTruthy();
  });


  fit('should add one line', () => {

    const line: LineModel = new LineModel("A1", null);

    linesService.addLine(line).then(data => {
      expect(data).toBeDefined();
      expect(data.name).toBe(line.name);
    });

    const urlPath = "http://localhost:8080/api/line/create";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      "name":"A1"
    };
    req.flush(retValAdd);
    httpMock.verify();

  });


  fit('should get all lines', () => {

    linesService.getLines().then(response => {
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




  fit('should delete line', () => {
    const line = {
      "idLine":0
    };

    linesService.deleteLine(line).then(response => {
        expect(response).toBeDefined();
    });


    const urlPath = "http://localhost:8080/api/line/0";
    const req = httpMock.expectOne({ method: 'DELETE', url: urlPath });
    expect(req.request.method).toBe('DELETE');

    const retValAdd =
      {
        "idLine": "0"
      };

    req.flush(retValAdd);
    httpMock.verify();

  });

});

