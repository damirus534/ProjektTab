import { Component, OnInit } from '@angular/core';

import {ProductService} from "../core/product/product.service";
import {Product} from "../core/product/product";
import {newArray} from "@angular/compiler/src/util";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {

  productList!: Array<Product>;
  productService!:ProductService
  constructor( productService:ProductService) {
    //productService.getProducts().subscribe(product=>this.productList=product)
    this.productService=productService
    this.productService.getProducts().subscribe(product=>this.productList=product)
    this.returnId=0
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
      }break;
      case 'Odzież':{
        this.getFilter(1)
        this.returnId=0
      }break;
      case 'Obuwie':{
        this.getFilter(2)
        this.returnId=0
      }break;
      case 'Akcesoria':{
        this.getFilter(3)
        this.returnId=0
      }break;
    }
  }

  selected($event: number) {
    this.productList=new Array()
    this.returnId=$event;

  }
}
