import {Component, Input, OnInit} from '@angular/core';
import {GalleryItem,ImageItem} from "ng-gallery"
import {ProductService} from "../core/product/product.service";
import {PhotoService} from "../core/photo/photo.service";
import {newArray} from "@angular/compiler/src/util";
import {ProductInfoService} from "../core/product-info/product-info.service";
import {ProductInfo} from "../core/product-info/product-info";
import {ProductOffer} from "../core/product/productOffer";
import {Observable} from "rxjs";
import {CartService} from "../core/website-service/cart/cart.service";
import {CartItem} from "../core/website-service/cart/CartItem";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-product-offer',
  templateUrl: './product-offer.component.html',
  styleUrls: ['./product-offer.component.css']
})
export class ProductOfferComponent implements OnInit {
  @Input()id!:number


  infoService!:ProductInfoService

  productService!:ProductService

  amountValue:number=0;
  size!:String;
  urls!:String[]

  productInfo!:ProductInfo;

  productOff!:ProductOffer[];

  selectedValue!:ProductOffer;

  cartService!:CartService

  constructor(public photoService:PhotoService,infoService:ProductInfoService,productService:ProductService,cartService:CartService) {
    this.photoService=photoService
    this.infoService=infoService
    this.productService=productService
    this.cartService=cartService;

  }

  images!: Array<ImageItem>;
  ngOnInit(): void {

  }
  ngOnChanges() {
    const _this = this;
    this.getItems()
    this.productService.getProductsByCategoryId(this.id).subscribe(data=>{
      while (this.productOff==undefined){
      this.productOff=data
      }})


    this.infoService.getInfoById(this.id).subscribe(info=>{

      this.productInfo=info
    })



  }
  getItems(){

    this.photoService.getPhotosUrls(this.id).subscribe(photo=> {
      var array=new Array<ImageItem>()
      photo.forEach(value => {


        array.push(new ImageItem({src:value.toString(),thumb:value.toString()}))
      })
      this.images=array

    });







}

  addToCart() {

    this.cartService.addItem(new CartItem(this.selectedValue,this.amountValue))


  }

  change($event: Event) {


  }
}

