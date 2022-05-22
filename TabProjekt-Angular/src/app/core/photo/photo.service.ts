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

  constructor(private httpClient: HttpClient) { }

  getPhotosUrls(id:number): Observable<Array<String>> {
        return this.httpClient.get<Array<String>>(`${environment.baseUrl}/photos/urls?id=`+id)
  }

  savePhoto(url: string): Observable<Photo> {
    return this.httpClient.post<Photo>(`${environment.baseUrl}/photos`, { photoUrl: url });
  }

  batchSavePhoto(photoList: Photo[]): Observable<Photo[]> {
    return this.httpClient.post<Photo[]>(`${environment.baseUrl}/photos/batch`, photoList);
  }

  assignProductInfoToPhoto(id: number, productInfoId: number): Observable<Photo> {
    return this.httpClient.put<Photo>(`${environment.baseUrl}/photos/${id}/product-info/${productInfoId}`, {});
  }

  batchAssignProductInfoToPhoto(savedPhotos: Photo[], id: number): Observable<Photo[]> {
    return this.httpClient.put<Photo[]>(`${environment.baseUrl}/photos/batch/product-info/${id}`, savedPhotos);
  }

  getPhotosByProductInfoId(id: number): Observable<Photo[]> {
    return this.httpClient.get<Photo[]>(`${environment.baseUrl}/photos/${id}`);
  }

  deletePhotoById(id: number): Observable<Photo> {
    return this.httpClient.delete<Photo>(`${environment.baseUrl}/photos/delete/${id}`);
  }

}
