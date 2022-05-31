import { Injectable } from '@angular/core';
import {Observable, tap} from "rxjs";
import {CartItem} from "./CartItem";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {CartElement} from "../../../cart-element/cartElement";
import { Cart } from './cart';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  actualCartList: Array<CartItem> = new Array<CartItem>();
  saveAnonCart: boolean = false;
  saveAnonCartVerificationId: string | null = null;

  constructor(private httpClient: HttpClient) {
    
  }

  addToCart(userId: number, productId: number, amount: number): Observable<any> {
    return this.httpClient.put(`${environment.baseUrl}/carts/${userId}/product/${productId}/amount/${amount}`, null);
  }

  getCartByUser(userId: number): Observable<Array<CartElement>> {
    return this.httpClient.get<Array<CartElement>>(`${environment.baseUrl}/carts/users?id=` + userId).pipe(
      tap(result => {
        return result;
      })
    );
  }

  deleteCartItem(id: number): Observable<CartElement> {
    return this.httpClient.delete<CartElement>(`${environment.baseUrl}/carts/delete/${id}`);
  }

  getCartList(): Array<CartItem> {
    return this.actualCartList;
  }
  
  deleteCartItemUnLog(id: number) {
    const cartItemIndex = this.actualCartList.findIndex(e => e.product.id == id);
    this.actualCartList.splice(cartItemIndex, 1);
  }

  addItem(item: CartItem) {
    const i = this.actualCartList.findIndex(data => data.product.id == item.product.id);
    if(i >= 0)
      this.actualCartList[i].amount += item.amount;
    else
      this.actualCartList.push(item);
  }

  findCartItemByProductIdAndUserId(productId: number, userId: number): Observable<Cart> {
    return this.httpClient.get<Cart>(`${environment.baseUrl}/carts/product/${productId}/user/${userId}`);
  }

}
