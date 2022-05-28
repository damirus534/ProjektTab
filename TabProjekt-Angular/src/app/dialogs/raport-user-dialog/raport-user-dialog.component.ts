import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../services/auth.service";
import {MatDatepicker} from "@angular/material/datepicker";
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";
import {OrderHistoryComponent} from "../../order-history/order-history.component";
import {formatDate} from "@angular/common";

@Component({
  selector: 'app-raport-user-dialog',
  templateUrl: './raport-user-dialog.component.html',
  styleUrls: ['./raport-user-dialog.component.css']
})
export class RaportUserDialogComponent implements OnInit {

  dateForm = new FormGroup({
    beginningDateControl: new FormControl('', [Validators.required]),
    endingDateControl: new FormControl('', Validators.required)
  });

  constructor(private authService: AuthService,public dialogRef: MatDialogRef<OrderHistoryComponent>) { }

  ngOnInit(): void {
    this.authService.userToken.id;
  }

  generateRaport() {
    const beginningDate =formatDate(this.dateForm.controls['beginningDateControl'].value,'yyyy-MM-dd','en-US')

    const endingDate = formatDate(this.dateForm.controls['endingDateControl'].value,'yyyy-MM-dd','en-US')

    localStorage.setItem("beginningDate",beginningDate)
    localStorage.setItem("endingDate",endingDate)
    this.dialogRef.close()
  }
}
