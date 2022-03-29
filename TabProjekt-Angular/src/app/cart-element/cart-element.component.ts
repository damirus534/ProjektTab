import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'cart-element',
  templateUrl: './cart-element.component.html',
  styleUrls: ['./cart-element.component.css']
})
export class CartElementComponent implements OnInit {
  
  @Input()
  name!: string;
  @Input()
  size! : number;
  @Input()
  imageUrls!: string[];
  @Input()
  price!: number;
  @Input()
  amount! : number;

  constructor() {
    
   }

  ngOnInit(): void {
  }

}
