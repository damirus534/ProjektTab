import {NgModule} from "@angular/core";
import {Routes,RouterModule} from "@angular/router";
import {OfferComponent} from "./offer/offer.component";
import {MainpageComponent} from "./mainpage/mainpage.component";
import {CartComponent} from "./cart/cart.component";
import {LoginComponent} from "./login/login.component";

const routes:Routes=[
  {path:'offer',component:OfferComponent},
  {path:'',component:MainpageComponent},
  {path:'cart',component:CartComponent},
  {path:'login',component:LoginComponent}
];
@NgModule({
  imports:[RouterModule.forRoot(routes)],
  exports:[RouterModule]
})
export class AppRoutingModule{}
export const routingComponents=[OfferComponent]
