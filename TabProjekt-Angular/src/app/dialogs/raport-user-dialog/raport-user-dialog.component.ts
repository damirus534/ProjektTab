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
  range= new FormGroup({
    start: new FormControl(null, [Validators.required]),
    end: new FormControl(null, [Validators.required]),
  });

  constructor(private authService: AuthService,public dialogRef: MatDialogRef<OrderHistoryComponent>) { }

  ngOnInit(): void {
    this.authService.userToken.id;
  }

  generateRaport() {
    /*
    const beginningDate =formatDate(this.dateForm.controls['beginningDateControl'].value,'yyyy-MM-dd','en-US')
    const endingDate = formatDate(this.dateForm.controls['endingDateControl'].value,'yyyy-MM-dd','en-US')*/

    const beginningDate = formatDate(this.range.controls['start'].value, 'yyyy-MM-ddT00:00:00', 'en-US');
    const endingDate = formatDate(this.range.controls['end'].value, 'yyyy-MM-ddT23:59:59', 'en-US');
    localStorage.setItem("beginningDate",beginningDate)
    localStorage.setItem("endingDate",endingDate)
    this.dialogRef.close()
  }
}
