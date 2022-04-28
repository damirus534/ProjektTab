import { NgModule } from "@angular/core";
import { Routes,RouterModule}  from "@angular/router";
import { OfferComponent } from "./offer/offer.component";
import { MainpageComponent } from "./mainpage/mainpage.component";
import { CartComponent } from "./cart/cart.component";
import { LoginAndRegisterComponent } from "./login-and-register/login-and-register.component";
import { AuthGuard } from "./guards/auth.guard";

const routes:Routes=[
  {
    path:'',
    component:MainpageComponent
  },
  {
    path:'offer',
    component:OfferComponent,
    canActivate: [AuthGuard]
  },
  {
    path:'cart',
    component:CartComponent,
    canActivate: [AuthGuard]
  },
  {
    path:'login',
    component:LoginAndRegisterComponent
  }
];
@NgModule({
  imports:[RouterModule.forRoot(routes)],
  exports:[RouterModule]
})
export class AppRoutingModule{}
export const routingComponents=[OfferComponent]
