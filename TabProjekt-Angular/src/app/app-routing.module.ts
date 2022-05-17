import { NgModule } from "@angular/core";
import { Routes,RouterModule}  from "@angular/router";
import { OfferComponent } from "./offer/offer.component";
import { MainpageComponent } from "./mainpage/mainpage.component";
import { CartComponent } from "./cart/cart.component";
import { LoginAndRegisterComponent } from "./login-and-register/login-and-register.component";
import { AuthGuard } from "./guards/auth.guard";
import { RoleGuard } from "./guards/role.guard";
import { AdminPanelComponent } from "./admin-panel/admin-panel.component";
import { AddProductDialogComponent } from "./dialogs/add-product-dialog/add-product-dialog.component";

const routes: Routes = [
  {
    path: '',
    component: MainpageComponent,
    canActivate: [RoleGuard],
    data: {
      roles: ['user', 'guest']
    }
  },
  {
    path: 'login',
    component: LoginAndRegisterComponent
  },
  {
    path: 'offer',
    component: OfferComponent,
    canActivate: [RoleGuard],
    data: {
      roles: ['user', 'guest']
    }
  },
  {
    path: 'cart',
    component: CartComponent,
    canActivate: [RoleGuard],
    data: {
      roles: ['user', 'guest']
    }
  },
  {
    path: 'admin',
    component: AdminPanelComponent,
    canActivate: [AuthGuard, RoleGuard],
    data: {
      roles: ['admin']
    }
  }
];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
export const routingComponents = [OfferComponent]
