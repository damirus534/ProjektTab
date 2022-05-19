import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NavibarComponent } from './navibar/navibar.component';
import {MatIconModule} from "@angular/material/icon";
import {MatToolbarModule} from "@angular/material/toolbar";
import { SidenavComponent } from './sidenav/sidenav.component';
import {MatSidenavModule} from "@angular/material/sidenav";
import {MatExpansionModule} from "@angular/material/expansion";
import {MatGridListModule} from "@angular/material/grid-list";
import { CardComponent } from './card/card.component';
import {MatCardModule} from "@angular/material/card";
import {MatButtonModule} from "@angular/material/button";
import {MatTooltipModule} from '@angular/material/tooltip';

import {AppRoutingModule} from "./app-routing.module";
import { MainpageComponent } from './mainpage/mainpage.component';
import { CartComponent } from './cart/cart.component';
import { LoginAndRegisterComponent } from './login-and-register/login-and-register.component';
import {MatInputModule} from '@angular/material/input';
import {MatDividerModule} from '@angular/material/divider';
import { CartElementComponent } from './cart-element/cart-element.component';
import { HttpClientModule } from '@angular/common/http';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { ProductListComponent } from './product-list/product-list.component';
import { ProductOfferComponent} from './product-offer/product-offer.component';
import {GalleryModule} from "ng-gallery";
import {MatSelectModule} from "@angular/material/select";
import { AuthInterceptorProvider } from './interceptors/auth.interceptor';
import {AddCategoryDialogComponent} from "./dialogs/add-category-dialog/add-category-dialog.component";
import {EditCategoryDialogComponent} from "./dialogs/edit-category-dialog/edit-category-dialog.component";
import {AddProductDialogComponent} from "./dialogs/add-product-dialog/add-product-dialog.component";
import {EditProductDialogComponent} from "./dialogs/edit-product-dialog/edit-product-dialog.component";
import {AdminPanelComponent} from "./admin-panel/admin-panel.component";
import {MatTabsModule} from "@angular/material/tabs";
import {MatListModule} from "@angular/material/list";
import {MatTableModule} from "@angular/material/table";
import { MatDialogModule} from "@angular/material/dialog";
import {MatSortModule} from "@angular/material/sort";
import {MatSnackBarModule} from '@angular/material/snack-bar';

@NgModule({
  declarations: [
    AppComponent,
    NavibarComponent,
    SidenavComponent,
    CardComponent,
    MainpageComponent,
    CartComponent,
    CardComponent,
    LoginAndRegisterComponent,
    CartElementComponent,
    ProductListComponent,
    ProductOfferComponent,
    AdminPanelComponent,
    AddCategoryDialogComponent,
    EditCategoryDialogComponent,
    AddProductDialogComponent,
    EditProductDialogComponent
  ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        MatIconModule,
        MatToolbarModule,
        MatSidenavModule,
        MatExpansionModule,
        MatGridListModule,
        MatCardModule,
        MatButtonModule,
        MatTooltipModule,
        AppRoutingModule,
        MatTooltipModule,
        MatInputModule,
        MatDividerModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        GalleryModule,
        MatSelectModule,
        MatTabsModule,
        MatListModule,
        MatTableModule,
        MatTabsModule,
        MatTableModule,
        MatSelectModule,
        MatListModule,
        MatDialogModule,
        MatSortModule,
        MatSnackBarModule
    ],
  providers: [AuthInterceptorProvider],
  bootstrap: [AppComponent]
})
export class AppModule { }
