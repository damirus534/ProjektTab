import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductInfo} from "./product-info";

@Injectable({
  providedIn: 'root'
})
export class ProductInfoService {
 private baseUrl="http://localhost:8080/product-info";

  constructor(private httpClient: HttpClient) { }
  getInfoById(id:number):Observable<ProductInfo>{
    return this.httpClient.get<ProductInfo>(`${this.baseUrl}/info?id=`+id)
  }
}
