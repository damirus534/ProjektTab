import { NgModule } from "@angular/core";
import { Routes,RouterModule}  from "@angular/router";
import { MainpageComponent } from "./mainpage/mainpage.component";
import { CartComponent } from "./cart/cart.component";
import { LoginAndRegisterComponent } from "./login-and-register/login-and-register.component";
import { AuthGuard } from "./guards/auth.guard";
import { RoleGuard } from "./guards/role.guard";
import { AdminPanelComponent } from "./admin-panel/admin-panel.component";
import {OrderHistoryComponent} from "./order-history/order-history.component";

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
  },
  {
    path:'history',
    component:OrderHistoryComponent,
    canActivate:[AuthGuard,RoleGuard],
    data: {
      roles: ['user']
    }
  },
  {
    path: '**',
    redirectTo: ''
  },

];
@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
