import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CartElement} from "./cartElement";

@Component({
  selector: 'cart-element',
  templateUrl: './cart-element.component.html',
  styleUrls: ['./cart-element.component.css']
})
export class CartElementComponent implements OnInit {

  @Input()
  element?: CartElement;
  @Output() eventTask = new EventEmitter<number>();

  constructor() {

  }
   
  ngOnChange():void{

  }

  ngOnInit(): void {

  }

  select() {
    this.eventTask.emit(this.element?.id);
  }
}
