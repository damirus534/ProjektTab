import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductInfo} from "./product-info";
import {environment} from "../../../environments/environment";
import {FormGroup} from "@angular/forms";
import {ProductInfoAdmin} from "./ProductInfoAdmin";

@Injectable({
  providedIn: 'root'
})
export class ProductInfoService {
 private baseUrl="http://localhost:8080/product-info";

  constructor(private httpClient: HttpClient) { }
  getInfoById(id:number):Observable<ProductInfo>{
    return this.httpClient.get<ProductInfo>(`${this.baseUrl}/info?id=`+id)
  }
  getProductInfo(): Observable<ProductInfoAdmin[]> {
    return this.httpClient.get<ProductInfoAdmin[]>(`${environment.baseUrl}/product-info`);
  }

  saveProductInfo(form: FormGroup): Observable<ProductInfoAdmin> {
    let productInfo: ProductInfo = new ProductInfo;
    productInfo.productName = form.controls['name'].value;
    productInfo.description = form.controls['descrption'].value;
    productInfo.buyingPrice = form.controls['buyingPrice'].value;
    productInfo.sellingPrice = form.controls['sellingPrice'].value;
    return this.httpClient.post<ProductInfoAdmin>(`${environment.baseUrl}/product-info`, productInfo);
  }

  assignCategoryToProductInfo(id: number, categoryId: number): Observable<ProductInfo> {
    return this.httpClient.put<ProductInfo>(`${environment.baseUrl}/product-info/${id}/category/${categoryId}`, {});
  }
  editProductInfo(form: FormGroup, id: number): Observable<ProductInfoAdmin> {
    let productInfo: ProductInfoAdmin = new ProductInfoAdmin();
    productInfo.productName = form.controls['name'].value;
    productInfo.category = form.controls['category'].value;
    productInfo.description = form.controls['descrption'].value;
    productInfo.buyingPrice = form.controls['buyingPrice'].value;
    productInfo.sellingPrice = form.controls['sellingPrice'].value;
    return this.httpClient.put<ProductInfoAdmin>(`${environment.baseUrl}/product-info/edit/${id}`, productInfo);
  }

  changeIsActive(productInfo: ProductInfoAdmin): Observable<ProductInfoAdmin> {
    return this.httpClient.put<ProductInfoAdmin>(`${environment.baseUrl}/product-info/edit/${productInfo.id}`, { isActive: productInfo.isActive })
  }
}
