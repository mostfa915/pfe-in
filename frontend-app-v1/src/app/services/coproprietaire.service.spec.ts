import { TestBed } from '@angular/core/testing';

import { CoproprietaireService } from './coproprietaire.service';

describe('CoproprietaireService', () => {
  let service: CoproprietaireService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CoproprietaireService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
