import {Injectable, Query} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {Product} from "./product";
import {Observable} from "rxjs";
import {Params} from "@angular/router";
import {ProductOffer} from "./productOffer";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  private baseUrl = "http://localhost:8080/products/main"

  constructor(private httpClient: HttpClient) { }

  getProducts():Observable<Array<Product>>{
    return this.httpClient.get<Array<Product>>(this.baseUrl)
  }
  getProductsByFilter(id:number):Observable<Array<Product>>{

      return this.httpClient.get<Array<Product>>(`${this.baseUrl}/category?id=`+id)
  }
  getProductsByCategoryId(id:number):Observable<Array<ProductOffer>>{
    return this.httpClient.get<Array<ProductOffer>>(`${this.baseUrl}/category/id?id=`+id)
  }


}
