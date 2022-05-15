import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductMainSide} from "../core/product/productMainSide";


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor() { }
  @Input() product!:ProductMainSide ;
  @Output() eventTask=new EventEmitter<number>();
  ngOnInit(): void {
    console.log(this.product)
    console.log(this.product.prize)
  }
  select(){
    this.eventTask.emit(this.product.id);
  }
}
