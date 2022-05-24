import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {AdminRaportReqbody} from "./admin-raport-reqbody";
import {Observable} from "rxjs";
import {AdminRaportService} from "./admin-raport-service";
import {environment} from "../../../environments/environment";

@Injectable({
  providedIn: 'root'
})
export class AdminRaportServiceService {

  constructor(private httpClient:HttpClient) { }

  getAdminRaport(filter: AdminRaportReqbody):Observable<Array<AdminRaportService>>{
    return this.httpClient.post<Array<AdminRaportService>>(`${environment.baseUrl}/order-history/adminRaport`, filter);
  }

}
