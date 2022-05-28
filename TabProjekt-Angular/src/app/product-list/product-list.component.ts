import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ProductMainSide} from "../core/product/productMainSide";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  constructor() { }
  @Input() productList!: Array<ProductMainSide>;
  @Output() eventTask = new EventEmitter<number>();
  
  ngOnInit(): void {
  }

  selected($event: number) {
    let id: number = $event;
    this.eventTask.emit(id);
  }
}
