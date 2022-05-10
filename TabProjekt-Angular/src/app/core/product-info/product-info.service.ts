import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { PhotoService } from '../photo/photo.service';
import { ProductService } from '../product/product.service';
import { ProductInfo } from './product-info';

@Injectable({
  providedIn: 'root'
})
export class ProductInfoService {

  constructor(
    private httpClient: HttpClient,
    private productService: ProductService,
    private photoService: PhotoService
  ) { }

  getProductInfo(): Observable<ProductInfo[]> {
    return this.httpClient.get<ProductInfo[]>(`${environment.baseUrl}/product-info`);
  }

  saveProductInfo(form: FormGroup): Observable<ProductInfo> {
    let productInfo: ProductInfo = new ProductInfo;
    productInfo.productName = form.controls['name'].value;
    productInfo.category = form.controls['category'].value;
    productInfo.description = form.controls['descrption'].value;
    productInfo.buyingPrice = form.controls['buyingPrice'].value;
    productInfo.sellingPrice = form.controls['sellingPrice'].value;
    productInfo.isActive = true;
    return this.httpClient.post<ProductInfo>(`${environment.baseUrl}/product-info`, productInfo);
  }

  assignCategoryToProductInfo(id: number, categoryId: number): Observable<ProductInfo> {
    return this.httpClient.put<ProductInfo>(`${environment.baseUrl}/product-info/${id}/category/${categoryId}`, {});
  }
  
  editProductInfo(form: FormGroup, id: number): Observable<ProductInfo> {
    let productInfo: ProductInfo = new ProductInfo;
    productInfo.productName = form.controls['name'].value;
    productInfo.category = form.controls['category'].value;
    productInfo.description = form.controls['descrption'].value;
    productInfo.buyingPrice = form.controls['buyingPrice'].value;
    productInfo.sellingPrice = form.controls['sellingPrice'].value;
    return this.httpClient.put<ProductInfo>(`${environment.baseUrl}/product-info/edit/${id}`, productInfo);
  }

  changeIsActive(productInfo: ProductInfo): Observable<ProductInfo> {
    return this.httpClient.put<ProductInfo>(`${environment.baseUrl}/product-info/edit/${productInfo.id}`, { isActive: productInfo.isActive })
  }

}
