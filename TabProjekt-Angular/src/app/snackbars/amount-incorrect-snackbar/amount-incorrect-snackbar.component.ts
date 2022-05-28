import { Component, Inject, OnInit } from '@angular/core';
import { MatSnackBarRef, MAT_SNACK_BAR_DATA } from '@angular/material/snack-bar';
import { CartElement } from 'src/app/cart-element/cartElement';

@Component({
  selector: 'app-amount-incorrect-snackbar',
  templateUrl: './amount-incorrect-snackbar.component.html',
  styleUrls: ['./amount-incorrect-snackbar.component.css']
})
export class AmountIncorrectSnackbarComponent implements OnInit {

  constructor(@Inject(MAT_SNACK_BAR_DATA) public data: CartElement[],
    private snackBarRef: MatSnackBarRef<AmountIncorrectSnackbarComponent>
  ) { }

  ngOnInit(): void {
  }

  dismiss() {
    this.snackBarRef.dismiss();
  }

}
