import { TestBed } from '@angular/core/testing';

import { SyndicService } from './syndic.service';

describe('SyndicService', () => {
  let service: SyndicService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SyndicService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
