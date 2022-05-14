import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductInfo} from "./product-info";
import {environment} from "../../../environments/environment";
import {FormGroup} from "@angular/forms";

@Injectable({
  providedIn: 'root'
})
export class ProductInfoService {
 private baseUrl="http://localhost:8080/product-info";

  constructor(private httpClient: HttpClient) { }
  getInfoById(id:number):Observable<ProductInfo>{
    return this.httpClient.get<ProductInfo>(`${this.baseUrl}/info?id=`+id)
  }
  getProductInfo(): Observable<ProductInfo[]> {
    return this.httpClient.get<ProductInfo[]>(`${environment.baseUrl}/product-info`);
  }

  saveProductInfo(form: FormGroup): Observable<ProductInfo> {
    let productInfo: ProductInfo = new ProductInfo;
    productInfo.productName = form.controls['name'].value;
    productInfo.description = form.controls['descrption'].value;
    productInfo.buyingPrice = form.controls['buyingPrice'].value;
    productInfo.sellingPrice = form.controls['sellingPrice'].value;
    return this.httpClient.post<ProductInfo>(`${environment.baseUrl}/product-info`, productInfo);
  }

  assignCategoryToProductInfo(id: number, categoryId: number): Observable<ProductInfo> {
    return this.httpClient.put<ProductInfo>(`${environment.baseUrl}/product-info/${id}/category/${categoryId}`, {});
  }
}
