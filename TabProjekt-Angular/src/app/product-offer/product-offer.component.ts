import {Component, Input, OnInit} from '@angular/core';
import {ImageItem} from "ng-gallery"
import {ProductService} from "../core/product/product.service";
import {PhotoService} from "../core/photo/photo.service";
import {ProductInfoService} from "../core/product-info/product-info.service";
import {ProductInfo} from "../core/product-info/product-info";
import {ProductOffer} from "../core/product/productOffer";
import {CartService} from "../core/website-service/cart/cart.service";
import {CartItem} from "../core/website-service/cart/CartItem";
import {MatDialog} from "@angular/material/dialog";
import {AuthService} from "../services/auth.service";
import { MatSnackBar } from '@angular/material/snack-bar';
import {Router} from "@angular/router";
import {MainSideService} from "../core/website-service/main-side/main-side.service";

@Component({
  selector: 'app-product-offer',
  templateUrl: './product-offer.component.html',
  styleUrls: ['./product-offer.component.css']
})
export class ProductOfferComponent implements OnInit {

  @Input() id!: number;

  amountValue: number = 0;
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
    private authService: AuthService,
    private _snackBar: MatSnackBar,
    private mainService: MainSideService
  ) { }

  images!: Array<ImageItem>;

  ngOnInit(): void {

  }

  ngOnChanges() {
    if(this.id==0){
      this.id=this.mainService.getMain();
    }
    const _this = this;
    this.getItems();
    this.productService.getProductsByCategoryId(this.id).subscribe(data => {
      while (this.productOff == undefined) {
      this.productOff = data;
      }
    });
    this.infoService.getInfoById(this.id).subscribe(info => {
      this.productInfo = info;
    });
  }

  getItems() {
    this.photoService.getPhotosUrls(this.id).subscribe(photo => {
      var array = new Array<ImageItem>();
      photo.forEach(value => {
        array.push(new ImageItem({ src:value.toString(), thumb:value.toString() }));
      })
      this.images = array;
    });
  }

  addToCart() {
    if (this.selectedValue == null) {
      this.error = "Wybierz rozmiar";
    } else if (this.amountValue == 0) {
      this.error = "Wybierz ilosc";
    } else if (this.amountValue > this.selectedValue.amountAvailable) {
      this.error = "Niewystarczaj??ca ilo???? produkt??w w magazynie";
    } else {
      const token = this.authService.token;
      if (token) {
        const userToken = this.authService.getUserToken(token);
        if (userToken) {
          this.cartService.findCartItemByProductIdAndUserId(this.selectedValue.id, userToken.id).subscribe((result) => {
            if (result && result.amount + this.amountValue > result.product?.amountAvailable!) {
                this._snackBar.open("Produkt nie zosta?? dodany, poniewa?? suma produkt??w w koszyku przekroczy??a by ilo???? w magazynie.", "OK");
            } else {
              this.cartService.addToCart(userToken.id, this.selectedValue.id, this.amountValue).subscribe();
              this.displaySuccessSnackbar();
            }
          });
          
        }
      } else {
        const duplicatedCartRecords = this.cartService.actualCartList.filter((cartItem) => {
          return cartItem.product.id === this.selectedValue.id;
        });
        if (duplicatedCartRecords.length > 0 && duplicatedCartRecords[0].amount + this.amountValue > duplicatedCartRecords[0].product.amountAvailable) {
            this._snackBar.open("Produkt nie zosta?? dodany, poniewa?? suma produkt??w w koszyku przekroczy??a by ilo???? w magazynie.", "OK");
        } else {
          this.cartService.addItem(new CartItem(this.selectedValue, this.amountValue, this.images[0].data.src!, this.productInfo.productName, this.productInfo.sellingPrice));
          this.displaySuccessSnackbar();
        }
      }
    }
  }

  change($event: Event) {

  }

  private displaySuccessSnackbar() {
    this._snackBar.open("Element zosta?? dodany do koszyka!", "OK", {
      duration: 3000,
      panelClass: ['success-snackbar']
    });
  }

  return() {
    this.mainService.setMain(0)
    this.mainService.setEvent(true);
  }

  validateNo($event: KeyboardEvent):boolean {
    const charCide=$event.which?$event.which:$event.keyCode;
    if(charCide>31&&(charCide<48||charCide>57)){
      return false
    }
    return true
  }
}

