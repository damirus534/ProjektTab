import { NgModule } from "@angular/core";
import { Routes,RouterModule}  from "@angular/router";
import { OfferComponent } from "./offer/offer.component";
import { MainpageComponent } from "./mainpage/mainpage.component";
import { CartComponent } from "./cart/cart.component";
import { LoginAndRegisterComponent } from "./login-and-register/login-and-register.component";
import { AuthGuard } from "./guards/auth.guard";
import { RoleGuard } from "./guards/role.guard";

const routes:Routes=[
  {
    path: '',
    component: MainpageComponent
  },
  {
    path: 'offer',
    component: OfferComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: {
      roles: ['admin']
    }
  },
  {
    path: 'cart',
    component: CartComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: {
      roles: ['admin', 'user']
    }
  },
  {
    path: 'login',
    component: LoginAndRegisterComponent
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
export const routingComponents = [OfferComponent]
