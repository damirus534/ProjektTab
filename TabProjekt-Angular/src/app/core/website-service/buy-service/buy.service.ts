import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {environment} from "../../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class BuyService {

  constructor(private httpClient:HttpClient) {

  }
  buyCart(userId:number){

    return this.httpClient.post(`${environment.baseUrl}/orders/buy`,userId)

  }
}
