import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class BuyService {

  constructor(private httpClient:HttpClient) {

  }
  
  buyCart(userId: number): Observable<any>{
    return this.httpClient.post(`${environment.baseUrl}/orders/buy`, userId);
  }
}
