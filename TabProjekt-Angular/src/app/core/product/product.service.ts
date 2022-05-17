import {Injectable, Query} from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {ProductMainSide} from "./productMainSide";
import {Observable} from "rxjs";
import {Params} from "@angular/router";
import {ProductOffer} from "./productOffer";
import {environment} from "../../../environments/environment";
import {Product} from "./product";
import {ProductAdmin} from "./productAdmin";

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


  assignProductInfoToProduct(id: number, productInfoId: number): Observable<Product> {
    return this.httpClient.put<Product>(`${environment.baseUrl}/products/${id}/product-info/${productInfoId}`, {});
  }

  saveProduct(size: string, amountAvailable: number): Observable<ProductAdmin> {
    const product = new ProductAdmin(null, null, size, amountAvailable);
    return this.httpClient.post<ProductAdmin>(`${environment.baseUrl}/products`, product);
  }

  batchSaveProduct(products: ProductAdmin[]) : Observable<ProductAdmin[]> {
    return this.httpClient.post<ProductAdmin[]>(`${environment.baseUrl}/products/batch`, products);
  }


  batchAssignProductInfoToProduct(productList: ProductAdmin[], productInfoId: number): Observable<ProductAdmin[]> {
    return this.httpClient.put<ProductAdmin[]>(`${environment.baseUrl}/products/batch-assign/product-info/${productInfoId}`, productList);
  }

  getProductByProductInfoId(id: number): Observable<Product[]> {
    return this.httpClient.get<Product[]>(`${environment.baseUrl}/products/${id}`);
  }

  editProduct(product: Product): Observable<Product> {
    return this.httpClient.put<Product>(`${environment.baseUrl}/products/edit/${product.id}`, { amountAvailable: product.amountAvailable });
  }

  batchEditProduct(productList: Product[]): Observable<Product[]> {
    return this.httpClient.put<Product[]>(`${environment.baseUrl}/products/edit/batch`, productList);
  }

}
