import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";

@Component({
  selector: 'app-raport-user-dialog',
  templateUrl: './raport-user-dialog.component.html',
  styleUrls: ['./raport-user-dialog.component.css']
})
export class RaportUserDialogComponent implements OnInit {

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.userToken.id;
  }



}
