import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {ProductMainSide} from "../product/productMainSide";
import {ProductOffer} from "../product/productOffer";
import {Photo} from "./photo";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class PhotoService {
  private baseUrl="http://localhost:8080/photos"
  constructor(private httpClient: HttpClient) { }
  getPhotosUrls(id:number):Observable<Array<String>>{
        return this.httpClient.get<Array<String>>(`${this.baseUrl}/urls?id=`+id)
  }
  savePhoto(url: string): Observable<Photo> {
    return this.httpClient.post<Photo>(`${environment.baseUrl}/photos`, { photoUrl: url });
  }

  assignProductInfoToPhoto(id: number, productInfoId: number): Observable<Photo> {
    return this.httpClient.put<Photo>(`${environment.baseUrl}/photos/${id}/product-info/${productInfoId}`, {});
  }
}
