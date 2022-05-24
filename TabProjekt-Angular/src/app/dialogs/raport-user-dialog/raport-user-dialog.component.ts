import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import {AuthService} from "../../services/auth.service";

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

  constructor(private authService: AuthService) { }

  ngOnInit(): void {
    this.authService.userToken.id;
  }

  generateRaport() {
    const beginningDate = this.dateForm.controls['beginningDateControl'].value;
    const endingDate = this.dateForm.controls['endingDateControl'].value;

    
  }

}
