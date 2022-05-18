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
import {AddToCartComponent} from "../dialogs/add-to-cart/add-to-cart.component";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-product-offer',
  templateUrl: './product-offer.component.html',
  styleUrls: ['./product-offer.component.css']
})
export class ProductOfferComponent implements OnInit {
  @Input()id!: number;

  amountValue: number=0;
  size!: String;
  urls!: String[];

  productInfo!: ProductInfo;

  productOff!: ProductOffer[];

  selectedValue!: ProductOffer;

  error?: String;

  constructor(
    private photoService: PhotoService,
    private infoService: ProductInfoService,
    private productService: ProductService,
    private cartService: CartService,
    private dialog: MatDialog,
    private authService: AuthService
  ) { }

  images!: Array<ImageItem>;

  ngOnInit(): void {

  }

  ngOnChanges() {
    const _this = this;
    this.getItems();
    this.productService.getProductsByCategoryId(this.id).subscribe(data => {
      while (this.productOff == undefined){
      this.productOff = data;
      }
    });
    this.infoService.getInfoById(this.id).subscribe(info => {
      this.productInfo = info;
    });
  }

  getItems() {

    this.photoService.getPhotosUrls(this.id).subscribe(photo => {
      var array=new Array<ImageItem>();
      photo.forEach(value => {
        array.push(new ImageItem({ src:value.toString(), thumb:value.toString() }));
      })
      this.images = array;
    });
  }

  addToCart() {
    if(this.selectedValue == null) {
      this.error = "Wybierz rozmiar";
    } else if(this.amountValue == 0) {
      this.error = "Wybierz ilosc";
    } else {
        const token = this.authService.token;
        if(token) {
          const userToken = this.authService.getUserToken(token);
          if(userToken) {
            this.cartService.addToCart(userToken.id,this.selectedValue.id,this.amountValue).subscribe();
          }
        } else {
          if(this.images[0].data.src != null)
            this.cartService.addItem(new CartItem(this.selectedValue,this.amountValue,this.images[0].data.src,this.productInfo.productName,this.productInfo.sellingPrice));
        }
        this.dialog.open(AddToCartComponent)
    }
  }

  change($event: Event) {

  }

}

