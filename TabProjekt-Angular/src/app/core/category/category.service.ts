import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Category } from './category';

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) { }

  getCategories(): Observable<Category[]> {
    return this.httpClient.get<Category[]>(`${environment.baseUrl}/categories`);
  }

  add(name: string): Observable<any> {
    return this.httpClient.post<any>(`${environment.baseUrl}/categories`, { categoryName: name });
  }

  delete(id: number): Observable<any> {
    return this.httpClient.delete<any>(`${environment.baseUrl}/categories/delete/${id}`);
  }

  edit(category: Category): Observable<any> {
    return this.httpClient.put<any>(`${environment.baseUrl}/categories/edit/${category.id}`, category);
  }
  unLogCategories():Observable<Category[]>{
    return this.httpClient.get<Category[]>(`${environment.baseUrl}/categories/all`)
  }

}
