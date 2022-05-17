import {ProductOffer} from "../../product/productOffer";

export class CartItem {
  product!:ProductOffer;
  amount!:number;
  photoUrl!:string
  productName!:string;
  sellingPrize!:number;
  constructor(product:ProductOffer,amout:number,photo :string,name:string,prize:number) {
    this.product=product
    this.amount=amout
    this.photoUrl=photo
    this.productName=name;
    this.sellingPrize=prize
  }
}
