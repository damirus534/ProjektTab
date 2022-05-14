import { Component, OnInit } from '@angular/core';
import { AuthService } from '../services/auth.service';
import {CartService} from "../core/website-service/cart/cart.service";
import {PhotoService} from "../core/photo/photo.service";
import {CartElement} from "../cart-element/cartElement";
import {Observable} from "rxjs";
import {newArray} from "@angular/compiler/src/util";
import { of } from 'rxjs';
import {BuyService} from "../core/website-service/buy-service/buy.service";

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {

  cartContent !: CartElement[] ;
  cartSum = 0;
  content!:Observable<Array<CartElement>>

  constructor(private authService: AuthService,private catsService:CartService,private buyService:BuyService) {

  }

  ngOnInit() {
    if(this.authService.token!=null){

      this.getContent()

    }
    else{

      this.cartContent=new Array();
      this.catsService.getCartList().forEach(item=>{
        if(item!=null) {
          this.cartContent.push(new CartElement(item.product.id, item.productName, item.product.size, item.photoUrl, item.sellingPrize, item.amount))
        }
        this.content=of(this.cartContent)
      })


    }


  }
  async calculate(prize:number,amount:number){
    this.cartSum=prize*amount;
    return this.cartSum
  }
  buyButt(){
    let num=this.authService.getUserToken(this.authService.token!)
    this.content=new Observable<Array<CartElement>>();

    this.buyService.buyCart(num.id).subscribe();
  }

  getRole(): string {
    return this.authService.token ? this.authService.userToken.role : "";
  }

  async getContent() {
    if (this.authService.token != null) {


      const token =  await this.authService.getUserToken(this.authService.token)


     this.content=this.catsService.getCartByUser(token.id)
      this.catsService.getCartByUser(token.id).subscribe(date=>{
        date.forEach(element=>{
          this.cartSum+=element.price*element.amount
        })
      })
    }
  }

  selected($event: number) {

    if(this.authService.token!=null){
      const token = this.authService.getUserToken(this.authService.token)
      this.catsService.deleteCartsItem($event)
      console.log()



    }
    else{
      this.catsService.deleteCartItemUnLog($event)

    }
  }
}
