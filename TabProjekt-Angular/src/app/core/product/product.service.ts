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
    let product = new Product;
    product.size = size;
    product.amountAvailable = amountAvailable;
    return this.httpClient.post<Product>(`${environment.baseUrl}/products`, product);
  }

  assignProductInfoToProduct(id: number, productInfoId: number): Observable<Product> {
    return this.httpClient.put<Product>(`${environment.baseUrl}/products/${id}/product-info/${productInfoId}`, {});
  }

}
