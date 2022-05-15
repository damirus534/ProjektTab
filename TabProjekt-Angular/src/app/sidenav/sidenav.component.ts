import { Component, OnInit } from '@angular/core';

import {ProductService} from "../core/product/product.service";
import {ProductMainSide} from "../core/product/productMainSide";
import {newArray} from "@angular/compiler/src/util";
import {Cart} from "../core/website-service/cart/cart";
import {CartService} from "../core/website-service/cart/cart.service";
import {MainSideService} from "../core/website-service/main-side/main-side.service";
import {CategoryService} from "../core/category/category.service";
import {Category} from "../core/category/category";
import {Observable} from "rxjs";


@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  productList!: Array<ProductMainSide>;
  productService!:ProductService
  mainService!:MainSideService

  constructor( productService:ProductService,mainService:MainSideService, private categoryService: CategoryService) {
    productService.getProducts().subscribe(product=>this.productList=product)
    this.productService=productService
    this.productService.getProducts().subscribe(product=>this.productList=product)
    this.returnId=0
    this.mainService=mainService
    this.categoryService.unLogCategories().subscribe(date=>this.categorys=date)
  }
  returnId!:number;
  categorys!:Category[];

  ngOnInit(): void {



  }
  getFilter(id:number){

    this.productService.getProductsByFilter(id).subscribe(product=>this.productList=product)

  }

  select($event: MouseEvent, type: Category) {
    productService:ProductService;
    this.getFilter(type.id)
    this.returnId=0
    this.mainService.setMain(0)
    /*switch (type){
      case 'Wszystkie':{
        this.productService.getProducts().subscribe(product=>this.productList=product)
        this.returnId=0
        this.mainService.setMain(0)
      }break;
      case 'Odzież':{
        this.getFilter(1)
        this.returnId=0
        this.mainService.setMain(0)
      }break;
      case 'Obuwie':{
        this.getFilter(2)
        this.returnId=0
        this.mainService.setMain(0)
      }break;
      case 'Akcesoria':{
        this.getFilter(3)
        this.returnId=0
        this.mainService.setMain(0)
      }break;
    }*/
  }
  getService():number{
    return this.mainService.getMain()
  }
  selected($event: number) {

    this.returnId=$event;
    this.mainService.setMain($event)
  }
}
