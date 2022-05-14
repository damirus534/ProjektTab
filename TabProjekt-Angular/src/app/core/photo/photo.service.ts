import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Photo } from './photo';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private httpClient: HttpClient) { }

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
