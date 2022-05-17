import { TestBed } from '@angular/core/testing';

import { MainSideService } from './main-side.service';

describe('MainSideService', () => {
  let service: MainSideService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MainSideService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
