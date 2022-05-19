import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import {CartService} from "../core/website-service/cart/cart.service";
import {CartElement} from "../cart-element/cartElement";
import {BuyService} from "../core/website-service/buy-service/buy.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartContent: CartElement[] = [];
  cartSum = 0;

  constructor(
    private authService: AuthService,
    private cartService: CartService,
    private buyService: BuyService
  ) {

  }

  ngOnInit() {
    if(this.authService.token) {
      this.getContent();
    } else {
      this.refreshAnonymousCart();
    }
  }

  buyButt() {
    let userToken = this.authService.getUserToken(this.authService.token!);
    this.buyService.buyCart(userToken.id).subscribe(() => {
      this.cartContent = [];
      this.cartSum = 0;
    });
  }

  getRole(): string {
    return this.authService.token ? this.authService.userToken.role : "";
  }

  getContent() {
    const token = this.authService.getUserToken(this.authService.token!);
    this.cartService.getCartByUser(token.id).subscribe(userCart => {
      this.cartContent = userCart;
      this.refreshSum();
    });
  }

  deleteElement(itemId: number) {
    if(this.authService.token) {
      this.cartService.deleteCartItem(itemId).subscribe(() => {
        const elementToDelete = this.findCartElemenetById(itemId);
        const indexToDelete = this.cartContent.indexOf(elementToDelete);
        this.cartContent.splice(indexToDelete, 1);
        this.refreshSum();
      });
    }
    else {
      this.cartService.deleteCartItemUnLog(itemId);
      this.refreshAnonymousCart();
    }
  }

  private findCartElemenetById(id: number): CartElement {
    let value = this.cartContent.filter((element) => {
      return element.id === id;
    });
    return value[0];
  }

  private refreshAnonymousCart() {
    this.cartContent = [];
    this.cartService.getCartList().forEach(item => {
      if(item) {
        this.cartContent.push(new CartElement(item.product.id, item.productName, item.product.size, item.photoUrl, item.sellingPrize, item.amount));
      }
    });
    this.refreshSum();
  }
  
  private refreshSum() {
    this.cartSum = 0;
    this.cartContent.forEach((cartElement) => {
      this.cartSum += cartElement.price * cartElement.amount;
    });
  }

}
