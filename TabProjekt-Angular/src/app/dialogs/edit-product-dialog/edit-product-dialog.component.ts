import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/core/category/category';
import { Photo } from 'src/app/core/photo/photo';
import { PhotoService } from 'src/app/core/photo/photo.service';
import { ProductInfoService } from 'src/app/core/product-info/product-info.service';
import { ProductInfoAdmin } from 'src/app/core/product-info/ProductInfoAdmin';
import { Product } from 'src/app/core/product/product';
import { ProductService } from 'src/app/core/product/product.service';

@Component({
  selector: 'app-edit-product-dialog',
  templateUrl: './edit-product-dialog.component.html',
  styleUrls: ['./edit-product-dialog.component.css']
})
export class EditProductDialogComponent implements OnInit {

  productList: Product[] = [];
  categoryList: Category[] = [];
  savedPhotoList: Photo[] = [];
  photoList: Photo[] = [];
  urlFromInput: string = '';
  urlInvalid = false;
  urlListEmpty = false;

  editForm: FormGroup = new FormGroup({
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
    private dialogRef: MatDialogRef<EditProductDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: any,
    private photoService: PhotoService,
    private productService: ProductService,
    private productInfoService: ProductInfoService
  ) {
    if(this.data) {
      this.categoryList = this.data.categoryList;
      this.photoService.getPhotosByProductInfoId(this.data.productInfo.id).subscribe((result) => {
        this.savedPhotoList = result;
      })
    }
  }

  ngOnInit(): void {
    this.editForm.controls['name'].setValue(this.data.productInfo.productName);
    this.editForm.controls['descrption'].setValue(this.data.productInfo.description);
    this.editForm.controls['category'].setValue(this.data.productInfo.category);
    this.editForm.controls['buyingPrice'].setValue(this.data.productInfo.buyingPrice);
    this.editForm.controls['sellingPrice'].setValue(this.data.productInfo.sellingPrice);
    this.productService.getProductByProductInfoId(this.data.productInfo.id).subscribe((result) => {
      this.productList = result;
      this.editForm.controls['sizeS'].setValue(this.getProductBySize('S')?.amountAvailable);
      this.editForm.controls['sizeM'].setValue(this.getProductBySize('M')?.amountAvailable);
      this.editForm.controls['sizeL'].setValue(this.getProductBySize('L')?.amountAvailable);
      this.editForm.controls['sizeXL'].setValue(this.getProductBySize('XL')?.amountAvailable);
      this.editForm.controls['sizeXXL'].setValue(this.getProductBySize('XXL')?.amountAvailable);
    });
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

  edit() {
    this.urlInvalid = false;
    if((this.photoList.length === 0 && this.savedPhotoList.length === 0) || this.editForm.invalid) {
      if(this.photoList.length === 0 && this.savedPhotoList.length === 0) {
        this.urlListEmpty = true;
      }
      return;
    }
    this.urlListEmpty = false;

    this.loadAmountAvailable(this.editForm);
    this.productInfoService.editProductInfo(this.editForm, this.data.productInfo.id).subscribe((updatedProductInfo) => {
      if(this.photoList.length !== 0) {
        this.setProductInfoOfPhotos(updatedProductInfo);
        this.photoService.batchSavePhoto(this.photoList).subscribe();
      }
      this.productService.batchEditProduct(this.productList).subscribe();

      const index = this.data.productInfoList.indexOf(this.data.productInfo);
      this.data.productInfoList[index] = updatedProductInfo;
      this.dialogRef.close({
        productInfoList: this.data.productInfoList
      });
    });
  }

  compareCategories(category1: Category, category2: Category): boolean {
    return category1 && category2 && category1.id === category2.id;
  }

  private getProductBySize(size: string) {
    let value = this.productList.filter((obj) => {
      return obj.size === size;
    });
    return value ? value[0] : null;
  }

  deleteSavedPhoto(id: number, indexInArray: number) {
    this.photoService.deletePhotoById(id).subscribe(() => {
      this.savedPhotoList.splice(indexInArray, 1);
    });
  }

  private loadAmountAvailable(form: FormGroup) {
    this.getProductBySize('S')!.amountAvailable = form.controls['sizeS'].value;
    this.getProductBySize('M')!.amountAvailable = form.controls['sizeM'].value;
    this.getProductBySize('L')!.amountAvailable = form.controls['sizeL'].value;
    this.getProductBySize('XL')!.amountAvailable = form.controls['sizeXL'].value;
    this.getProductBySize('XXL')!.amountAvailable = form.controls['sizeXXL'].value;
  }
    
  private setProductInfoOfPhotos(productInfo: ProductInfoAdmin) {
    for(let element of this.photoList) {
      element.productInfo = productInfo;
    }
  }

}
