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

  add(name: string): Observable<Category> {
    return this.httpClient.post<Category>(`${environment.baseUrl}/categories`, { categoryName: name });
  }

  delete(id: number): Observable<Category> {
    return this.httpClient.delete<Category>(`${environment.baseUrl}/categories/delete/${id}`);
  }

  edit(category: Category): Observable<Category> {
    return this.httpClient.put<Category>(`${environment.baseUrl}/categories/edit/${category.id}`, category);
  }

}
