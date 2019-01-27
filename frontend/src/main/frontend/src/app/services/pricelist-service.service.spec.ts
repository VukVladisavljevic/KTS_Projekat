import { TestBed } from '@angular/core/testing';

import { PricelistService } from './pricelist-service.service';
import {StationsService} from './stations.service';
import {HttpClient} from '@angular/common/http';
import {HttpClientTestingModule, HttpTestingController} from '@angular/common/http/testing';
import {Pricelist} from '../models/pricelist';
import {Departure} from '../models/departure';

describe('PricelistService', () => {

  let priceListService: PricelistService;
  let http: HttpClient;
  let httpMock: HttpTestingController;


  beforeEach(() => {
    TestBed.configureTestingModule({
      imports: [HttpClientTestingModule],
      providers: [PricelistService]
    });

    priceListService = TestBed.get(PricelistService);
    http = TestBed.get(HttpClient);
    httpMock = TestBed.get(HttpTestingController);
  });



  afterEach(() => {
    httpMock.verify();
  });

  fit('should be created', () => {
    expect(priceListService).toBeTruthy();
  });



  it('should be created', () => {
    const service: PricelistService = TestBed.get(PricelistService);
    expect(service).toBeTruthy();
  });


  fit('should create pricelist', () => {

    const pricelist: Pricelist = {
      "startDate": "start",
      "endDate": "end",
      "ticketType": "1",
      "price" : 22.5
    };

    priceListService.addPricelist(pricelist).then(data => {
      //expect(data.ticketType).toBe(pricelist.ticketType);
    });

    const urlPath = "http://localhost:8080/api/pricelist/create";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      "startDate": !null,
      "endDate": !null,
      "ticketType": !null,
      "price" : !null
    };

    req.flush(retValAdd);
    httpMock.verify();

  });



  fit('should get all pricelists', () => {

    priceListService.getAllPricelists().then(data => {
      expect(data).toBe(!null);
    });

    const urlPath = "http://localhost:8080/api/pricelist/getall";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');

    httpMock.verify();

  });

  fit('should get all pricelists', () => {

    priceListService.getCurrentPricelist().then(data => {
      expect(data).toBe(!null);
    });

    const urlPath = "http://localhost:8080/api/pricelist/getcurrent";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');

    httpMock.verify();

  });
});

