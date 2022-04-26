import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Product} from "../product/product";
import {ProductOffer} from "../product/productOffer";

@Injectable({
  providedIn: 'root'
})
export class PhotoService {
  private baseUrl="http://localhost:8080/photos"
  constructor(private httpClient: HttpClient) { }
  getPhotosUrls(id:number):Observable<Array<String>>{
        return this.httpClient.get<Array<String>>(`${this.baseUrl}/urls?id=`+id)
  }

}
