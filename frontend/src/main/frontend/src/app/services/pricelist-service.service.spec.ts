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



  fit('should be created', () => {
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
      expect(data.startDate).toBe(pricelist.startDate);
      expect(data.endDate).toBe(pricelist.endDate);
      expect(data.ticketType).toBe(pricelist.ticketType);
      expect(data.price).toBe(pricelist.price);
    });

    const urlPath = "http://localhost:8080/api/pricelist/create";
    const req = httpMock.expectOne({ method: 'POST', url: urlPath });
    expect(req.request.method).toBe('POST');
    const retValAdd = {
      "startDate": "start",
      "endDate": "end",
      "ticketType": "1",
      "price" : 22.5
    };

    req.flush(retValAdd);
    httpMock.verify();

  });



  fit('should get all pricelists', () => {

    priceListService.getAllPricelists().then(data => {
      expect(data[0].startDate).toBe("01.02.2019")
      expect(data[0].endDate).toBe("30.02.2019")
      expect(data[0].ticketType).toBe("1")
      expect(data[0].price).toBe(22.5)

      expect(data[1].startDate).toBe("01.01.2019")
      expect(data[1].endDate).toBe("31.01.2019")
      expect(data[1].ticketType).toBe("1")
      expect(data[1].price).toBe(22.5)
    });


    const urlPath = "http://localhost:8080/api/pricelist/getall";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');

    const retValAdd = [{
      "startDate": "01.02.2019",
      "endDate": "30.02.2019",
      "ticketType": "1",
      "price" : 22.5
    }, {
      "startDate": "01.01.2019",
      "endDate": "31.01.2019",
      "ticketType": "1",
      "price" : 22.5
    } ];
    req.flush(retValAdd);
    httpMock.verify();

  });

  fit('should get current pricelist', () => {

    priceListService.getCurrentPricelist().then(data => {
      expect(data[0].startDate).toBe("01.02.2019")
      expect(data[0].endDate).toBe("30.02.2019")
      expect(data[0].ticketType).toBe("1")
      expect(data[0].price).toBe(22.5)
    });

    const urlPath = "http://localhost:8080/api/pricelist/getcurrent";
    const req = httpMock.expectOne({ method: 'GET', url: urlPath });
    expect(req.request.method).toBe('GET');
    const retValAdd = [{
      "startDate": "01.02.2019",
      "endDate": "30.02.2019",
      "ticketType": "1",
      "price" : 22.5
    }];

    req.flush(retValAdd);
    httpMock.verify();
  });
});

