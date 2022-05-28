import { TestBed } from '@angular/core/testing';

import { AdminRaportServiceService } from './admin-raport-service.service';

describe('AdminRaportServiceService', () => {
  let service: AdminRaportServiceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AdminRaportServiceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
