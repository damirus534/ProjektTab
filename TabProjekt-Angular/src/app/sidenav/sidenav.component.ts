import { Component, OnInit } from '@angular/core';
import {Product} from "../core/product";

@Component({
  selector: 'app-sidenav',
  templateUrl: './sidenav.component.html',
  styleUrls: ['./sidenav.component.css']
})
export class SidenavComponent implements OnInit {
  public productList:Product={id:1,
    name:"Adidas zx",
    imageUrls:["https://assets.adidas.com/images/w_600,f_auto,q_auto/c2d47865bc8143acb3c0ad2401570a53_9366/Ozelia_Shoes_Czern_H04250_01_standard.jpg"],
    price:17,
    description:"Być może lata 90. to dla Ciebie odległa historia, ale buty Ozelia są zakorzenione w tej bezkompromisowej, eksperymentalnej erze. Śmiały model inspirowany archiwalnymi butami adidas ma solidną konstrukcję, która sprawia, że nie sposób go nie zauważyć. Gładkie buty mają biegowy charakter, a kiedy Twoje dni nabierają tempa, amortyzacja Adiprene zapewnia stopom wygodę.\n" +
      "\n" +
      "Ten produkt zawiera materiały pochodzące z recyklingu w ramach naszych wysiłków zmierzających do rozwiązania problemu plastikowych odpadów. 20% elementów użytych do wykonania cholewki zawiera co najmniej 50% materiałów pochodzących z recyklingu."};
  constructor() {


  }

  types=['Odzież','Obuwie','Akcesoria'];
  ngOnInit(): void {
    this.productList.name="Adidas";
  }
  selected(id:number){
    if(this.productList.id==id){}

  }

}
