import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Photo } from './photo';

@Injectable({
  providedIn: 'root'
})
export class PhotoService {

  constructor(private httpCilent: HttpClient) { }

  savePhoto(url: string): Observable<Photo> {
    return this.httpCilent.post<Photo>(`${environment.baseUrl}/photos`, { photoUrl: url });
  }

  assignProductInfoToPhoto(id: number, productInfoId: number): Observable<Photo> {
    return this.httpCilent.put<Photo>(`${environment.baseUrl}/photos/${id}/product-info/${productInfoId}`, {});
  }

}
