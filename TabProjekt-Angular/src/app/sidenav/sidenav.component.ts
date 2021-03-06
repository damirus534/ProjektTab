import { Component, OnInit } from '@angular/core';

import {ProductService} from "../core/product/product.service";
import {ProductMainSide} from "../core/product/productMainSide";
import {MainSideService} from "../core/website-service/main-side/main-side.service";
import {CategoryService} from "../core/category/category.service";
import {Category} from "../core/category/category";


@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  productList!: Array<ProductMainSide>;

  constructor(private productService: ProductService, public mainService: MainSideService, private categoryService: CategoryService) {
    this.productService.getProducts().subscribe(product => this.productList = product);
    this.returnId = 0;
    this.categoryService.getActiveCategories().subscribe(activeCategories => this.categories = activeCategories);
  }
  returnId!: number;
  categories!: Category[];

  ngOnInit(): void {
    this.mainService.getEvent().subscribe(event => {
      if(event == true) {
        this.reset();
        this.mainService.setEvent(false);
      }
    })
  }
  ngOnChange() {

  }
  getFilter(id: number) {
    this.productService.getProductsByFilter(id).subscribe(product => this.productList = product);
  }

  select($event: MouseEvent, type: Category) {
    this.getFilter(type.id!);
    this.mainService.category=type.id!;
    this.returnId = 0;
    this.mainService.setMain(0);
  }

  getService(): number {
    return this.mainService.getMain();
  }

  selected($event: number) {
    this.returnId = $event;
    this.mainService.setMain($event);
  }

  reset() {
    this.productService.getProducts().subscribe(data => this.productList = data);
  }

}
