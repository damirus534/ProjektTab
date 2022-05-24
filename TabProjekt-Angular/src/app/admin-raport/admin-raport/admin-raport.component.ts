import {Component, Input, OnInit} from '@angular/core';
import {AdminRaportService} from "../../core/admin-raport-service/admin-raport-service";

@Component({
  selector: 'app-admin-raport',
  templateUrl: './admin-raport.component.html',
  styleUrls: ['./admin-raport.component.css']
})
export class AdminRaportComponent implements OnInit {
  @Input()
  body?:AdminRaportService[]
  @Input()
  id?:number
  constructor() { }

  ngOnInit(): void {
  }

}
