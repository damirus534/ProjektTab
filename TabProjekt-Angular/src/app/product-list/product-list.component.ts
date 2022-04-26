import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {Product} from "../core/product/product";
import {$e} from "@angular/compiler/src/chars";

@Component({
  selector: 'app-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.css']
})
export class ProductListComponent implements OnInit {

  constructor() { }
  @Input() productList!: Array<Product>;
  @Output() eventTask=new EventEmitter<number>();
  ngOnInit(): void {
  }

  selected($event: number) {
    let id: number = $event
    this.eventTask.emit(id)
  }
}
