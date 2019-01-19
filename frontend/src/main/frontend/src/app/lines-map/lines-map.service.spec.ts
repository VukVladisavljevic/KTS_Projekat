import { TestBed } from '@angular/core/testing';

import { LinesMapService } from './lines-map.service';

describe('LinesMapService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: LinesMapService = TestBed.get(LinesMapService);
    expect(service).toBeTruthy();
  });
});
