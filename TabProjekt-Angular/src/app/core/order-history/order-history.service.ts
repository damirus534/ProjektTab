import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {UserRaportFilterReqBody} from "./UserRaportFilterReqBody";
import {OrderHistory} from "./order-history";
import {Observable} from "rxjs";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class OrderHistoryService {

  constructor(private httpClient:HttpClient) { }
  getOrderHistory(filter:UserRaportFilterReqBody,id:Number):Observable<Array<OrderHistory>>{
    return this.httpClient.post<Array<OrderHistory>>(`${environment.baseUrl}/order-history/${id}`,filter);
  }
}
