import { NumberInput } from '@angular/cdk/coercion';
import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Product } from './product';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  constructor(private httpClient: HttpClient) { }

  saveProduct(size: string, amountAvailable: number): Observable<Product> {
    const product = new Product(null, null, size, amountAvailable);
    return this.httpClient.post<Product>(`${environment.baseUrl}/products`, product);
  }

  batchSaveProduct(products: Product[]) : Observable<Product[]> {
    return this.httpClient.post<Product[]>(`${environment.baseUrl}/products/batch`, products);
  }

  assignProductInfoToProduct(id: number, productInfoId: number): Observable<Product> {
    return this.httpClient.put<Product>(`${environment.baseUrl}/products/${id}/product-info/${productInfoId}`, {});
  }

  batchAssignProductInfoToProduct(productList: Product[], productInfoId: number): Observable<Product[]> {
    return this.httpClient.put<Product[]>(`${environment.baseUrl}/products/batch-assign/product-info/${productInfoId}`, productList);
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
