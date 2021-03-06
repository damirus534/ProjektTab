import {Input} from "@angular/core";

export class CartElement {

  id!:number;
  name!: string;
  size! : string;
  imageUrl!: string;
  price!: number;
  amount! : number;
  amountAvailable!: number;

  constructor(id: number, name: string, size: string, imageUrl: string, price: number, amount: number, amountAvailable: number) {
    this.id = id;
    this.amount = amount;
    this.name = name;
    this.size = size;
    this.imageUrl = imageUrl;
    this.price = price;
    this.amountAvailable = amountAvailable;
  }

}
