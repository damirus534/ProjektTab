import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor() { }
  desc='Zainspirowane cyfrowymi technologiami i codziennością. Stworzone dla Twojej satysfakcji. Odblaskowe akcenty podkreślają techniczną cholewkę butów ZX 2K BOOST 2.0. Załóż je i poczuj niewiarygodną miękkość. Amortyzacja Boost daje satysfakcję z każdego kroku.';
  photos=[{
    title:'ZX 2K BOOST 2.0 SHOES',
    url:'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/2c43c4cdaf59417b99e4ad2100c3f874_9366/ZX_2K_Boost_2.0_Shoes_Czern_GZ7740_01_standard.jpg',
    describe:this.desc
  },
    {
      title:'ZX 2K BOOST 2.0 SHOES',
      url:'https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/2c43c4cdaf59417b99e4ad2100c3f874_9366/ZX_2K_Boost_2.0_Shoes_Czern_GZ7740_01_standard.jpg',
      describe:this.desc
    }

  ]

  ngOnInit(): void {
  }

}
