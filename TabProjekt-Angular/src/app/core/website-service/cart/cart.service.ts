import { Injectable } from '@angular/core';
import {map, Observable, tap} from "rxjs";
import {Cart} from "./cart";
import {CartItem} from "./CartItem";
import {HttpClient, HttpParams} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import {CartElement} from "../../../cart-element/cartElement";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  actualCartList:Array<CartItem>=new Array<CartItem>()
  constructor(private httpClient:HttpClient) {

  }
  addItem(item:CartItem){
    let i=this.actualCartList.findIndex(date=>
      date.product.id==item.product.id
    )
    if(i>=0){
      this.actualCartList[i].amount+=item.amount;
    }
    else this.actualCartList.push(item)

  }
  getCartList():Array<CartItem>{
    return this.actualCartList
  }
  addToCart(userId:number,productId:number,amount:number):Observable<any>{

    return this.httpClient.put(`${environment.baseUrl}/carts/${userId}/product/${productId}/amount/${amount}`,null)
  }
  getCartByUser(userId:number):Observable<Array<CartElement>>{
    return this.httpClient.get<Array<CartElement>>(`${environment.baseUrl}/carts/users?id=`+userId).pipe(
      tap(result => {
        return result;
      })
    );
  }

  deleteCartsItem(id: number) {

      this.httpClient.delete(`${environment.baseUrl}/carts/delete/${id}`).subscribe()
  }
  deleteCartItemUnLog(id:number){
    let a=this.actualCartList.findIndex(e=>e.product.id==id)
    this.actualCartList.splice(a,1)



  }
}
