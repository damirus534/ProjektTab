import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RaportUserDialogComponent } from './raport-user-dialog.component';

describe('RaportUserDialogComponent', () => {
  let component: RaportUserDialogComponent;
  let fixture: ComponentFixture<RaportUserDialogComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RaportUserDialogComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RaportUserDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
