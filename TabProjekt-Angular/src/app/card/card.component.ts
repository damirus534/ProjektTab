import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-card',
  templateUrl: './card.component.html',
  styleUrls: ['./card.component.css']
})
export class CardComponent implements OnInit {

  constructor() { }
  title='ZX 2K BOOST 2.0 SHOES';
  url='https://assets.adidas.com/images/h_840,f_auto,q_auto,fl_lossy,c_fill,g_auto/2c43c4cdaf59417b99e4ad2100c3f874_9366/ZX_2K_Boost_2.0_Shoes_Czern_GZ7740_01_standard.jpg'
  describe='Być może lata 90. to dla Ciebie odległa historia, ale buty Ozelia są zakorzenione w tej bezkompromisowej, eksperymentalnej erze. Śmiały model inspirowany archiwalnymi butami adidas ma solidną konstrukcję, która sprawia, że nie sposób go nie zauważyć. Gładkie buty mają biegowy charakter, a kiedy Twoje dni nabierają tempa, amortyzacja Adiprene zapewnia stopom wygodę.\n' +
    '\n' +
    'Ten produkt zawiera materiały pochodzące z recyklingu w ramach naszych wysiłków zmierzających do rozwiązania problemu plastikowych odpadów. 20% elementów użytych do wykonania cholewki zawiera co najmniej 50% materiałów pochodzących z recyklingu.'
  ngOnInit(): void {
  }

}
