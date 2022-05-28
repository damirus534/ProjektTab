import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AdminRaportComponent } from './admin-raport.component';

describe('AdminRaportComponent', () => {
  let component: AdminRaportComponent;
  let fixture: ComponentFixture<AdminRaportComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminRaportComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminRaportComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
