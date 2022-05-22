import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AmountIncorrectSnackbarComponent } from './amount-incorrect-snackbar.component';

describe('AmountIncorrectSnackbarComponent', () => {
  let component: AmountIncorrectSnackbarComponent;
  let fixture: ComponentFixture<AmountIncorrectSnackbarComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AmountIncorrectSnackbarComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AmountIncorrectSnackbarComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
