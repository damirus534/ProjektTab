import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/core/category/category';
import { Photo } from 'src/app/core/photo/photo';
import { PhotoService } from 'src/app/core/photo/photo.service';
import { ProductInfo } from 'src/app/core/product-info/product-info';
import { ProductInfoService } from 'src/app/core/product-info/product-info.service';
import { Product } from 'src/app/core/product/product';
import { ProductService } from 'src/app/core/product/product.service';
import {ProductInfoAdmin} from "../../core/product-info/ProductInfoAdmin";
import {ProductAdmin} from "../../core/product/productAdmin";

@Component({
  selector: 'app-add-product-dialog',
  templateUrl: './add-product-dialog.component.html',
  styleUrls: ['./add-product-dialog.component.css']
})
export class AddProductDialogComponent implements OnInit {

  categoryList: Category[] = [];
  urlFromInput: string = '';
  photoList: Photo[] = [];
  urlInvalid = false;
  urlListEmpty = false;

  addForm: FormGroup = new FormGroup({
    name: new FormControl('', [Validators.required]),
    descrption: new FormControl(''),
    category: new FormControl('', [Validators.required]),
    buyingPrice: new FormControl('', [Validators.required]),
    sellingPrice: new FormControl('', [Validators.required]),
    sizeS: new FormControl('0', [Validators.required, Validators.pattern("^[0-9]*$")]),
    sizeM: new FormControl('0', [Validators.required, Validators.pattern("^[0-9]*$")]),
    sizeL: new FormControl('0', [Validators.required, Validators.pattern("^[0-9]*$")]),
    sizeXL: new FormControl('0', [Validators.required, Validators.pattern("^[0-9]*$")]),
    sizeXXL: new FormControl('0', [Validators.required, Validators.pattern("^[0-9]*$")])
  });

  constructor(
    private productInfoService: ProductInfoService,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private dialogRef: MatDialogRef<AddProductDialogComponent>,
    private photoService: PhotoService,
    private productService: ProductService
  ) {
    this.categoryList = data.categories;
  }

  ngOnInit(): void {
  }

  addUrl() {
    this.urlListEmpty = false;
    if(this.isValidUrl(this.urlFromInput)) {
      this.photoList.push(new Photo(null, null, this.urlFromInput));
      this.urlInvalid = false;
      this.urlFromInput = '';
    } else {
      this.urlInvalid = true;
    }
  }

  deleteUrl(index: number) {
    this.photoList.splice(index, 1);
  }

  isValidUrl(input: string): boolean {
    let url_string;
    try {
      url_string = new URL(input);
    } catch (e) {
      return false;
    }
    return url_string.protocol === "http:" || url_string.protocol === "https:" ;
  }

  add() {
    this.urlInvalid = false;
    if(this.photoList.length === 0 || this.addForm.invalid) {
      if(this.photoList.length === 0) {
        this.urlListEmpty = true;
      }
      return;
    }
    this.urlListEmpty = false;

    this.productInfoService.saveProductInfo(this.addForm).subscribe((savedProductInfo) => {
      const productList = this.createProductList(this.addForm, savedProductInfo);
      this.setProductInfoOfPhotos(savedProductInfo);
      this.photoService.batchSavePhoto(this.photoList).subscribe();
      this.productService.batchSaveProduct(productList).subscribe();
      this.data.productInfoList.push(savedProductInfo);
      this.dialogRef.close({
        data: this.data.productInfoList
      });
    });
  }

  private createProductList(form: FormGroup, productInfo: ProductInfoAdmin): ProductAdmin[] {
    return new Array(
      new ProductAdmin(null, productInfo, 'S', form.controls['sizeS'].value),
      new ProductAdmin(null, productInfo, 'M', form.controls['sizeM'].value),
      new ProductAdmin(null, productInfo, 'L', form.controls['sizeL'].value),
      new ProductAdmin(null, productInfo, 'XL', form.controls['sizeXL'].value),
      new ProductAdmin(null, productInfo, 'XXL', form.controls['sizeXXL'].value)
    )
  }

  private setProductInfoOfPhotos(productInfo: ProductInfoAdmin) {
    for(let element of this.photoList) {
      element.productInfo = productInfo;
    }
  }

}
