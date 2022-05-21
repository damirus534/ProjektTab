import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ProductMainSide} from "./productMainSide";
import {Observable} from "rxjs";
import {ProductOffer} from "./productOffer";
import {environment} from "../../../environments/environment";
import {Product} from "./product";
import {ProductAdmin} from "./productAdmin";

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  getProducts(): Observable<Array<ProductMainSide>> {
    return this.httpClient.get<Array<ProductMainSide>>(`${environment.baseUrl}/products/main`);
  }

  getProductsByFilter(id: number): Observable<Array<ProductMainSide>> {
      return this.httpClient.get<Array<ProductMainSide>>(`${environment.baseUrl}/products/main/category?id=` + id);
  }

  getProductsByCategoryId(id: number): Observable<Array<ProductOffer>> {
    return this.httpClient.get<Array<ProductOffer>>(`${environment.baseUrl}/products/main/category/id?id=` + id);
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

  getProductByProductInfoId(id: number): Observable<ProductAdmin[]> {
    return this.httpClient.get<ProductAdmin[]>(`${environment.baseUrl}/products/${id}`);
  }

  editProduct(product: ProductAdmin): Observable<ProductAdmin> {
    return this.httpClient.put<ProductAdmin>(`${environment.baseUrl}/products/edit/${product.id}`, { amountAvailable: product.amountAvailable });
  }

  batchEditProduct(productList: ProductAdmin[]): Observable<ProductAdmin[]> {
    return this.httpClient.put<ProductAdmin[]>(`${environment.baseUrl}/products/edit/batch`, productList);
  }

}
