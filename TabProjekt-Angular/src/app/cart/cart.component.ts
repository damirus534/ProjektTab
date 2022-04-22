import { Component, OnInit } from '@angular/core';
import { User } from '../core/user/user';

@Component({
  selector: 'app-cart',
  templateUrl: './cart.component.html',
  styleUrls: ['./cart.component.css']
})
export class CartComponent implements OnInit {
  
  cartContent : any[] = [
    {
      name: 'Wspaniałe buty',
      size: 'M',
      imageUrls: ['https://64.media.tumblr.com/e4a53bd7ea4addde5fe25c9b7b9fcad1/558ff8b529c84eeb-e0/s640x960/cb04c1fa2834c1dc75fb6444c17c144f98f98d9f.jpg'],
      price: 69.69,
      amount: 3
    },
    {
      name: 'Przepiękne rękawiczki',
      size: 'L',
      imageUrls: ['https://elektrykapradnietyka.com/wp-content/uploads/2021/03/kastet_na_trytytki-1.jpg'],
      price: 0.04,
      amount: 2
    },
    {
      name: 'Przepiękne rękawiczki',
      size: 'S',
      imageUrls: ['https://elektrykapradnietyka.com/wp-content/uploads/2021/03/kastet_na_trytytki-1.jpg'],
      price: 0.04,
      amount: 2
    },
    {
      name: 'Przepiękne rękawiczki',
      size: 'XXXL',
      imageUrls: ['https://elektrykapradnietyka.com/wp-content/uploads/2021/03/kastet_na_trytytki-1.jpg'],
      price: 0.04,
      amount: 2
    },
    {
      name: 'Przepiękne rękawiczki',
      size: 'XXL',
      imageUrls: ['https://elektrykapradnietyka.com/wp-content/uploads/2021/03/kastet_na_trytytki-1.jpg'],
      price: 0.04,
      amount: 2
    },
    {
      name: 'Przepiękne rękawiczki',
      size: 'XL',
      imageUrls: ['https://elektrykapradnietyka.com/wp-content/uploads/2021/03/kastet_na_trytytki-1.jpg'],
      price: 0.04,
      amount: 2
    },
    {
      name: 'Przepiękne rękawiczki',
      size: 'BigBoi',
      imageUrls: ['https://elektrykapradnietyka.com/wp-content/uploads/2021/03/kastet_na_trytytki-1.jpg'],
      price: 0.04,
      amount: 2
    }
  ];
  cartSum = 0;
  constructor() { 
    this.cartContent.forEach( (item) => 
      this.cartSum+=item.price * item.amount
    );
  }

  ngOnInit(): void {
  }

}
