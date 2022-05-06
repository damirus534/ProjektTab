import { Component, Inject, OnInit } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ProductInfo } from 'src/app/core/product-info/product-info';

@Component({
  selector: 'app-edit-product-dialog',
  templateUrl: './edit-product-dialog.component.html',
  styleUrls: ['./edit-product-dialog.component.css']
})
export class EditProductDialogComponent implements OnInit {

  public name!: string;
  public sizes: { name: string, amount: number }[] = [];
  private editedProductInfo!: ProductInfo;

  constructor(
    private dialogRef: MatDialogRef<EditProductDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any
  ) {
    if(this.data) {
      this.name = this.data.productInfo.productName;
      this.editedProductInfo = this.data.productInfo;
    }
  }

  ngOnInit(): void {
  }

}
