import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material/dialog';
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

  productList: ProductInfo[] = [];
  productColumnNames = ['product-id', 'product-name', 'product-category', 'product-buying-price', 'product-selling-price', 'edit-action', 'delete-action'];

  isCategoriesShown: boolean = true;
  isProductsShown: boolean = false;
  isAdminAccountShown: boolean = false;

  constructor(
    private categoryService: CategoryService,
    private productInfoService: ProductInfoService,
    public dialog: MatDialog
  ) {
    this.getCategories();
    this.getProductInfo();
  }

  ngOnInit(): void {
  }
  
  private getCategories() {
    this.categoryService.getCategories().subscribe((data) => {
      this.categoryList = data;
    });
  }

  openAddCategoryDialog() {
    const dialogRef = this.dialog.open(AddCategoryDialogComponent);
    
    dialogRef.afterClosed().subscribe((result) => {
      if(result) {
        this.categoryList = result.data;
      }
    });
  }

  openEditCategoryDialog(id: number) {
    const dialogRef = this.dialog.open(EditCategoryDialogComponent, {
      data: {
        category: this.getCategoryById(id) ?? null
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
    } catch (e) {
      console.log(e);
    }
  }

  openAddProductDialog() {
    const dialogRef = this.dialog.open(AddProductDialogComponent, {
      data: {
        categories: this.categoryList
      }
    });
    dialogRef.afterClosed().subscribe((result) =>{
      this.productList = result.data;
    });
  }

  private getProductInfo() {
    this.productInfoService.getProductInfo().subscribe((data) => {
      this.productList = data;
    });
  }

  matchCategoryToProduct(categoryId: number): string {
    let value = this.categoryList.filter((obj) => {
      return obj.id === categoryId;
    });
    return value.length ? value[0].categoryName : '-';
  }

  openEditProductDialog(id: number) {
    const dialogRef = this.dialog.open(EditProductDialogComponent, {
      data: {
        productInfo: this.getProductInfoById(id) ?? null
      }
    });
  }

  private getProductInfoById(id: number) {
    let value = this.productList.filter((obj) => {
      return obj.id === id;
    });
    return value ? value[0] : null;
  }

  showCategories() {
    this.isCategoriesShown = true;
    this.isProductsShown = false;
    this.isAdminAccountShown = false;
  }

  showProducts() {
    this.isCategoriesShown = false;
    this.isProductsShown = true;
    this.isAdminAccountShown = false;
  }

  showAdminAccount() {
    this.isCategoriesShown = false;
    this.isProductsShown = false;
    this.isAdminAccountShown = true;
  }

}
