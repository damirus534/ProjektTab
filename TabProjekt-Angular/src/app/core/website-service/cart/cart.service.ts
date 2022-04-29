import { Injectable } from '@angular/core';
import {Observable, tap} from "rxjs";
import {Cart} from "./cart";
import {CartItem} from "./CartItem";

@Injectable({
  providedIn: 'root'
})
export class CartService {
  actualCartList:Array<CartItem>=new Array<CartItem>()
  constructor() {

  }
  addItem(item:CartItem){
    this.actualCartList.push(item)
    console.log(this.actualCartList)
  }
  getCartList():Array<CartItem>{
    return this.actualCartList
  }

}
