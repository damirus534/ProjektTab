import {Input} from "@angular/core";

export class CartElement{
  constructor(id:number,name:string,size:string,imageUrl:string,price:number,amount:number) {
    this.id=id;
    this.amount=amount;
    this.name=name;
    this.size=size
    this.imageUrl=imageUrl;
    this.price=price

  }
  id!:number;

  name!: string;

  size! : string;

  imageUrl!: string;

  price!: number;

  amount! : number;
}