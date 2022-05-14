import {Injectable, Query} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {ProductMainSide} from "./productMainSide";
import {Observable} from "rxjs";
import {Params} from "@angular/router";
import {ProductOffer} from "./productOffer";
import {environment} from "../../../environments/environment";
import {Product} from "./product";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = "http://localhost:8080/products/main"

  constructor(private httpClient: HttpClient) { }

  getProducts():Observable<Array<ProductMainSide>>{
    return this.httpClient.get<Array<ProductMainSide>>(this.baseUrl)
  }
  getProductsByFilter(id:number):Observable<Array<ProductMainSide>>{

      return this.httpClient.get<Array<ProductMainSide>>(`${this.baseUrl}/category?id=`+id)
  }
  getProductsByCategoryId(id:number):Observable<Array<ProductOffer>>{
    return this.httpClient.get<Array<ProductOffer>>(`${this.baseUrl}/category/id?id=`+id)
  }
  saveProduct(size: string, amountAvailable: number): Observable<Product> {
    let product = new Product;
    product.size = size;
    product.amountAvailable = amountAvailable;
    return this.httpClient.post<Product>(`${environment.baseUrl}/products`, product);
  }

  assignProductInfoToProduct(id: number, productInfoId: number): Observable<Product> {
    return this.httpClient.put<Product>(`${environment.baseUrl}/products/${id}/product-info/${productInfoId}`, {});
  }


}
