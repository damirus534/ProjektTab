import { Component, OnInit } from '@angular/core';
import {OrderHistoryService} from "../core/order-history/order-history.service";
import {UserRaportFilterReqBody} from "../core/order-history/UserRaportFilterReqBody";
import {DatePipe} from "@angular/common";
import {AuthService} from "../services/auth.service";
import {Observable} from "rxjs";
import {OrderHistory} from "../core/order-history/order-history";
import {MatTableDataSource} from "@angular/material/table";

@Component({
  selector: 'app-order-history',
  templateUrl: './order-history.component.html',
  styleUrls: ['./order-history.component.css']
})
export class OrderHistoryComponent implements OnInit {

  displayedColumns: string[] = ['Data Zamowienia', 'Nazwa Produktu', 'Ilość', 'Cena Zakupu','Rozmiar Produktu'];

  displayRow!:OrderHistory[]
  constructor(private orderHistoryService:OrderHistoryService,private authService:AuthService) {
    let beging=localStorage.getItem("beginningDate")

    let ending=localStorage.getItem("endingDate")

    localStorage.removeItem("beginningDate")
    localStorage.removeItem("endingDate")
    let body:UserRaportFilterReqBody
    if(beging&&ending){
      const userToken = this.authService.getUserToken(this.authService.token!);

      body= new UserRaportFilterReqBody(beging, ending);

      this.orderHistoryService.getOrderHistory(body,userToken.id).subscribe(date=>{this.displayRow=date
        console.log(date)})


    }
  }


  ngOnInit(): void {


  }

}
