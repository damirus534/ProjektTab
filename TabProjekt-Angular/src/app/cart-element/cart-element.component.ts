import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CartElement} from "./cartElement";

@Component({
  selector: 'cart-element',
  templateUrl: './cart-element.component.html',
  styleUrls: ['./cart-element.component.css']
})
export class CartElementComponent implements OnInit {
  visible?:boolean=true
  @Input()
  element?:CartElement;
  @Output() eventTask=new EventEmitter<number>();

  constructor() {

   }
ngOnChange():void{

}
  ngOnInit(): void {

  }

  select() {
    console.log(this.element?.id)
    this.eventTask.emit(this.element?.id)
    this.element=undefined
    this.visible=false
  }
}
