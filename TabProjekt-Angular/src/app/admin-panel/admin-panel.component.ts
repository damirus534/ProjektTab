import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
import { MatTableDataSource } from '@angular/material/table';
import { Category } from '../core/category/category';
import { CategoryService } from '../core/category/category.service';
import { ProductInfo } from '../core/product-info/product-info';
import { ProductInfoService } from '../core/product-info/product-info.service';
import { AddCategoryDialogComponent } from '../dialogs/add-category-dialog/add-category-dialog.component';
import { AddProductDialogComponent } from '../dialogs/add-product-dialog/add-product-dialog.component';
import { EditCategoryDialogComponent } from '../dialogs/edit-category-dialog/edit-category-dialog.component';
import { EditProductDialogComponent } from '../dialogs/edit-product-dialog/edit-product-dialog.component';

@Component({
  selector: 'app-admin-panel',
  templateUrl: './admin-panel.component.html',
  styleUrls: ['./admin-panel.component.css']
})
export class AdminPanelComponent implements OnInit {

  categoryList: Category[] = [];
  categoryColumnNames = ['category-id', 'category-name', 'edit-action', 'delete-action'];
  categoryDataSource = new MatTableDataSource<Category>();

  productInfoList: ProductInfo[] = [];
  productColumnNames = ['product-id', 'product-name', 'product-category', 'product-buying-price', 'product-selling-price', 'edit-action', 'block-resume-action'];
  productInfoDataSource = new MatTableDataSource<ProductInfo>();

  isCategoriesShown: boolean = true;
  isProductsShown: boolean = false;

  constructor(
    private categoryService: CategoryService,
    private productInfoService: ProductInfoService,
    public dialog: MatDialog
  ) {
  }

  ngOnInit(): void {
    this.getCategories();
    this.getProductInfo();
  }
  
  private getCategories() {
    this.categoryService.getCategories().subscribe((categoryList) => {
      this.categoryList = categoryList;
      this.refreshCategoryDataSource();
    });
  }

  private refreshCategoryDataSource() {
      this.categoryDataSource.data = this.categoryList;
  }

  private refreshProductInfoDataSource() {
    this.productInfoDataSource.data = this.productInfoList;
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

  deleteCategory(id: number) {
    try {
      this.categoryService.delete(id).subscribe();
      this.categoryList = this.categoryList.filter((category) => {
        return category.id !== id;
      });
      this.refreshCategoryDataSource();
    } catch (e) {
      console.log(e);
    }
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

  blockOrResumeOffer(productInfo: ProductInfo) {
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

}
