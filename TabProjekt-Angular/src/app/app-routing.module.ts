import {NgModule} from "@angular/core";
import {Routes,RouterModule} from "@angular/router";
import {OfferComponent} from "./offer/offer.component";
import {MainpageComponent} from "./mainpage/mainpage.component";
import {CartComponent} from "./cart/cart.component";
import {LoginAndRegisterComponent} from "./login-and-register/login-and-register.component";

const routes:Routes=[
  {path:'offer',component:OfferComponent},
  {path:'',component:MainpageComponent},
  {path:'cart',component:CartComponent},
  {path:'login',component:LoginAndRegisterComponent}
];
@NgModule({
  imports:[RouterModule.forRoot(routes)],
  exports:[RouterModule]
})
export class AppRoutingModule{}
export const routingComponents=[OfferComponent]
