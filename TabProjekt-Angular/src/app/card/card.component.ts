import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from "../core/product/product";


@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor() { }
  @Input() product!:Product ;
  @Output() eventTask=new EventEmitter<number>();
  ngOnInit(): void {
  }
  select(){
    this.eventTask.emit(this.product.id);
  }
}
