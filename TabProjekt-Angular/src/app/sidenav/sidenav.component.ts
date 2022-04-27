import { Component, OnInit } from '@angular/core';

import {ProductService} from "../core/product/product.service";
import {Product} from "../core/product/product";
import {newArray} from "@angular/compiler/src/util";
import {Cart} from "../core/website-service/cart/cart";
import {CartService} from "../core/website-service/cart/cart.service";
import {MainSideService} from "../core/website-service/main-side/main-side.service";


@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  productList!: Array<Product>;
  productService!:ProductService
  mainService!:MainSideService
  constructor( productService:ProductService,mainService:MainSideService) {
    //productService.getProducts().subscribe(product=>this.productList=product)
    this.productService=productService
    this.productService.getProducts().subscribe(product=>this.productList=product)
    this.returnId=0
    this.mainService=mainService
  }
  returnId!:number;
  types=['Wszystkie','Odzież','Obuwie','Akcesoria'];

  ngOnInit(): void {


  }
  getFilter(id:number){

    this.productService.getProductsByFilter(id).subscribe(product=>this.productList=product)

  }

  select($event: MouseEvent, type: string) {
    productService:ProductService;
    switch (type){
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
    }
  }
  getService():number{
    console.log(this.mainService.getMain())
    return this.mainService.getMain()
  }
  selected($event: number) {

    this.returnId=$event;
    this.mainService.setMain($event)
  }
}
