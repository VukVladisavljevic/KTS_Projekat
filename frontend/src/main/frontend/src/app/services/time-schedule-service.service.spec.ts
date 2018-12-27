import { TestBed } from '@angular/core/testing';

import { TimeScheduleService } from './time-schedule-service.service';

describe('TimeScheduleServiceService', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: TimeScheduleService = TestBed.get(TimeScheduleService);
    expect(service).toBeTruthy();
  });
});
