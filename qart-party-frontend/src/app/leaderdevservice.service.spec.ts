import { TestBed } from '@angular/core/testing';

import { LeaderdevserviceService } from './leaderdevservice.service';

describe('LeaderdevserviceService', () => {
  let service: LeaderdevserviceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(LeaderdevserviceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
