import {Component, Input, OnInit} from '@angular/core';
import {AdminRaportService} from "../../core/admin-raport-service/admin-raport-service";
import {ProductInfoAdmin} from "../../core/product-info/ProductInfoAdmin";

@Component({
  selector: 'app-admin-raport',
  templateUrl: './admin-raport.component.html',
  styleUrls: ['./admin-raport.component.css']
})
export class AdminRaportComponent implements OnInit {
  @Input()
  body?:AdminRaportService[]
  @Input()
  id?:number
  @Input()
  suma?:number

  sum:number[]=[]
  ngOnChange(){
    this.body?.forEach(date=>{
      if(date.orderAmount!=null&&date.orderSellPrice!=null) {
        this.sum.push(date.orderAmount * date.orderSellPrice)
      }
    })
    console.log(this.suma);
  }

  constructor() { }

  ngOnInit(): void {

  }


}
