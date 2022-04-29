import {ProductOffer} from "../../product/productOffer";

export class CartItem {
  product?:ProductOffer;
  amount?:number;
  constructor(product:ProductOffer,amout:number) {
    this.product=product
    this.amount=amout
  }
}
