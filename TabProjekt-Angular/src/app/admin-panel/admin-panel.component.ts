import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { Sort } from '@angular/material/sort';
import { MatTableDataSource } from '@angular/material/table';
import { Category } from '../core/category/category';
import { CategoryService } from '../core/category/category.service';
import { ProductInfo } from '../core/product-info/product-info';
import { ProductInfoService } from '../core/product-info/product-info.service';
import { AddCategoryDialogComponent } from '../dialogs/add-category-dialog/add-category-dialog.component';
import { AddProductDialogComponent } from '../dialogs/add-product-dialog/add-product-dialog.component';
import { EditCategoryDialogComponent } from '../dialogs/edit-category-dialog/edit-category-dialog.component';
import { EditProductDialogComponent } from '../dialogs/edit-product-dialog/edit-product-dialog.component';
import {ProductInfoAdmin} from "../core/product-info/ProductInfoAdmin";
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {AdminRaportServiceService} from "../core/admin-raport-service/admin-raport-service.service";
import {filter} from "rxjs";
import {AdminRaportReqbody} from "../core/admin-raport-service/admin-raport-reqbody";
import {AdminRaportService} from "../core/admin-raport-service/admin-raport-service";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  categoryRadio: string[] = ['Zyski','Przychody'];
  public categoryList: Category[] = [];
  categoryColumnNames = ['category-id', 'category-name', 'edit-action', 'block-resume-action'];
  categoryDataSource = new MatTableDataSource<Category>();
  private activeCategorySort: Sort | null = null;
  categoryId: number | null = null;
  private productInfoList: ProductInfoAdmin[] = [];
  productColumnNames = ['product-id', 'product-name', 'product-category', 'product-buying-price', 'product-selling-price', 'edit-action', 'block-resume-action'];
  productInfoDataSource = new MatTableDataSource<ProductInfoAdmin>();
  private activeProductSort: Sort | null = null;
  suma=0;

  isCategoriesShown: boolean = true;
  isProductsShown: boolean = false;
  raportBody!: AdminRaportService[];
  generateButton: boolean = false;
  inputHidden = true;

  radio1:number=1;
  outPut?:number;
  range= new FormGroup({
    start: new FormControl(null, [Validators.required]),
    end: new FormControl(null, [Validators.required]),
  });
  dateForm = new FormGroup({
    beginningDateControl: new FormControl(null, [Validators.required]),
    endingDateControl: new FormControl(null, [Validators.required])
  });
  matTabIndex?: number;



  constructor(
    private categoryService: CategoryService,
    private productInfoService: ProductInfoService,
    public dialog: MatDialog,
    private adminRaportServiceService : AdminRaportServiceService
  ) {
  }

  ngOnInit(): void {
    this.getCategories();
    this.getProductInfo();
  }

  displayOrHideDatapickers() {
    this.inputHidden= !this.inputHidden;
  }

  disableGenerateButton(): boolean {
    if (!this.inputHidden && this.range.invalid) {
      return true;
    }
    else if (this.inputHidden || !this.inputHidden && this.range.valid) {
      return false;
    }
    else {
      return true;
    };
  }

  generateRaport() {
    /*if(this.dateForm.controls['beginningDateControl'].value != null && this.dateForm.controls['endingDateControl'].value != null){
      const beginningDate =formatDate(this.dateForm.controls['beginningDateControl'].value,'yyyy-MM-dd','en-US')

      const endingDate = formatDate(this.dateForm.controls['endingDateControl'].value,'yyyy-MM-dd','en-US')

      this.adminRaportServiceService.getAdminRaport(new AdminRaportReqbody(this.categoryId,
        beginningDate,endingDate,
        this.radio1)).subscribe(date=>{
        this.raportBody=date
        console.log(date)
      })
    }*/
    this.suma=0;
    if(this.range.controls['start'].value != null && this.range.controls['end'].value !=null){
      const beginningDate = formatDate(this.range.controls['start'].value, 'yyyy-MM-ddT00:00:00', 'en-US');
      const endingDate = formatDate(this.range.controls['end'].value, 'yyyy-MM-ddT23:59:59', 'en-US');
      this.adminRaportServiceService.getAdminRaport(new AdminRaportReqbody(this.categoryId,
        beginningDate,endingDate,
        this.radio1)).subscribe(date=>{
        this.raportBody=date
        console.log(date)
        this.raportBody.forEach(date=>{
          if(date.orderAmount&&date.orderSellPrice){
            date.wholeSum=date.orderAmount*date.orderSellPrice;
            this.suma= this.suma + date.wholeSum;
          }
        })
      })
    }
    else {
    this.adminRaportServiceService.getAdminRaport(new AdminRaportReqbody(this.categoryId,
      null,null,
      this.radio1)).subscribe(date=>{
        this.raportBody=date
      console.log(date)
      this.raportBody.forEach(date=>{
        if(date.orderAmount&&date.orderSellPrice){
          date.wholeSum=date.orderAmount*date.orderSellPrice;
          this.suma= this.suma + date.wholeSum;
        }
      })
    })}
    this.outPut=this.radio1
  }

  private getCategories() {
    this.categoryService.getCategories().subscribe((categoryList) => {
      this.categoryList = categoryList;
      this.refreshCategoryDataSource();
    });
  }

  private refreshCategoryDataSource() {
    if(this.activeCategorySort) {
      this.sortCategories(this.activeCategorySort);
    } else {
      this.categoryDataSource.data = this.categoryList;
    }
  }

  private refreshProductInfoDataSource() {
    if(this.activeProductSort) {
      this.sortProducts(this.activeProductSort);
    } else {
      this.productInfoDataSource.data = this.productInfoList;
    }
  }

  openAddCategoryDialog() {
    const dialogRef = this.dialog.open(AddCategoryDialogComponent, {
      data: {
        categoryList: this.categoryList
      }
    });

    dialogRef.afterClosed().subscribe((result) => {
      if(result) {
        this.categoryList = result.categoryList;
        this.refreshCategoryDataSource();
      }
    });
  }

  openEditCategoryDialog(id: number) {
    const dialogRef = this.dialog.open(EditCategoryDialogComponent, {
      data: {
        category: this.getCategoryById(id) ?? null,
        categoryList: this.categoryList
      }
    });

    dialogRef.afterClosed().subscribe((result) => {
      if(result) {
        this.categoryList = result.categoryList;
        this.refreshCategoryDataSource();
      }
    });
  }

  private getCategoryById(id: number): Category | null {
    let value = this.categoryList.filter((obj) => {
      return obj.id === id;
    });
    return value ? value[0] : null;
  }

  blockOrResumeCategory(category: Category) {
    category.isActive = !category.isActive;
    this.categoryService.changeIsActive(category).subscribe();
  }

  openAddProductDialog() {
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      data: {
        categories: this.categoryList,
        productInfoList: this.productInfoList
      }
    });
    dialogRef.afterClosed().subscribe((result) =>{
      if(result) {
        this.productInfoList = result.data;
        this.refreshProductInfoDataSource();
      }
    });
  }

  private getProductInfo() {
    this.productInfoService.getProductInfo().subscribe((data) => {
      this.productInfoList = data;
      this.refreshProductInfoDataSource();
    });
  }

  matchCategoryToProduct(category: Category): void | string {
    if(!category)
      return;
    let value = this.categoryList.filter((obj) => {
      return obj.id === category.id;
    });
    return value.length ? value[0].categoryName : '-';
  }

  openEditProductDialog(id: number) {
    const dialogRef = this.dialog.open(EditProductDialogComponent, {
      data: {
        productInfo: this.getProductInfoById(id) ?? null,
        categoryList: this.categoryList,
        productInfoList: this.productInfoList
      }
    });

    dialogRef.afterClosed().subscribe((result) => {
      if(result) {
        this.productInfoList = result.productInfoList;
        this.refreshProductInfoDataSource();
      }
    });
  }

  private getProductInfoById(id: number) {
    let value = this.productInfoList.filter((obj) => {
      return obj.id === id;
    });
    return value ? value[0] : null;
  }

  blockOrResumeOffer(productInfo: ProductInfoAdmin) {
    productInfo.isActive = !productInfo.isActive;
    this.productInfoService.changeIsActive(productInfo).subscribe();
  }

  showCategories() {
    this.isCategoriesShown = true;
    this.isProductsShown = false;
  }

  showProducts() {
    this.isCategoriesShown = false;
    this.isProductsShown = true;
  }

  sortCategories(sort: Sort) {
    const data = this.categoryList.slice();
    this.activeCategorySort = sort;
    if (!sort.active || sort.direction === '') {
      this.categoryDataSource.data = data;
      return;
    }

    this.categoryDataSource.data = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'category-id':
          return this.compare(a.id!, b.id!, isAsc);
        case 'category-name':
          return this.compare(a.categoryName, b.categoryName, isAsc);
        default:
          return 0;
      }
    });
  }

  sortProducts(sort: Sort) {
    const data = this.productInfoList.slice();
    this.activeProductSort = sort;
    if (!sort.active || sort.direction === '') {
      this.productInfoDataSource.data = data;
      return;
    }

    this.productInfoDataSource.data = data.sort((a, b) => {
      const isAsc = sort.direction === 'asc';
      switch (sort.active) {
        case 'product-id':
          return this.compare(a.id, b.id, isAsc);
        case 'product-name':
          return this.compare(a.productName, b.productName, isAsc);
        case 'product-category':
          return this.compare(a.category.categoryName, b.category.categoryName, isAsc);
        case 'product-buying-price':
          return this.compare(a.buyingPrice, b.buyingPrice, isAsc);
        case 'product-selling-price':
          return this.compare(a.sellingPrice, b.sellingPrice, isAsc);
        default:
          return 0;
      }
    });
  }

  private compare(a: number | string, b: number | string, isAsc: boolean) {
    return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
  }

  onChange(id:number) {
    this.categoryId=id;
  }
}
