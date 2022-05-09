import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { Category } from 'src/app/core/category/category';
import { PhotoService } from 'src/app/core/photo/photo.service';
import { ProductInfo } from 'src/app/core/product-info/product-info';
import { ProductInfoService } from 'src/app/core/product-info/product-info.service';
import { ProductService } from 'src/app/core/product/product.service';

@Component({
  selector: 'app-add-product-dialog',
  templateUrl: './add-product-dialog.component.html',
  styleUrls: ['./add-product-dialog.component.css']
})
export class AddProductDialogComponent implements OnInit {

  categoryList: Category[] = [];
  urlFromInput: string = '';
  urlList: string[] = [];
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
    if(this.isValidUrl(this.urlFromInput)) {
      this.urlList.push(this.urlFromInput);
      this.urlInvalid = false;
      this.urlFromInput = '';
    } else {
      this.urlInvalid = true;
    }
  }

  deleteUrl(index: number) {
    this.urlList.splice(index, 1);
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
    if(this.urlList.length === 0 || this.addForm.invalid) {
      if(this.urlList.length === 0) {
        this.urlListEmpty = true;
      }
      return;
    }
    this.urlListEmpty = false;
    
    let sizes = this.arrayOfSizes(this.addForm);
    this.productInfoService.saveProductInfo(this.addForm).subscribe((result) => {
      this.productInfoService.assignCategoryToProductInfo(result.id, this.addForm.controls['category'].value.id).subscribe(() => {
        for(let url of this.urlList) {
          this.photoService.savePhoto(url).subscribe((resultPhoto) => {
            this.photoService.assignProductInfoToPhoto(resultPhoto.id, result.id).subscribe();
          });
        }
        for(let size of sizes) {
          this.productService.saveProduct(size.name, size.control.value).subscribe((resultProduct) => {
            this.productService.assignProductInfoToProduct(resultProduct.id, result.id).subscribe();
          });
        }
        this.productInfoService.getProductInfo().subscribe((result) => {
          this.dialogRef.close({
            data: result
          });
        });
      });
    });
  }

  
  private arrayOfSizes(form: FormGroup): any[] {
    let array = [];
    array.push({ name: 'S', control: form.controls['sizeS']});
    array.push({ name: 'M', control: form.controls['sizeM']});
    array.push({ name: 'L', control: form.controls['sizeL']});
    array.push({ name: 'XL', control: form.controls['sizeXL']});
    array.push({ name: 'XXL', control: form.controls['sizeXXL']});
    return array;
  }

}
