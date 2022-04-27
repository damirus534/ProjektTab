import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {MainSideService} from "../core/website-service/main-side/main-side.service";

@Component({
  selector: 'app-navibar',
  templateUrl: './navibar.component.html',
  styleUrls: ['./navibar.component.css']
})
export class NavibarComponent implements OnInit {
  mainSide!:MainSideService
  constructor(mainSide:MainSideService) {
    this.mainSide=mainSide
  }

  ngOnInit(): void {

  }

  checkURL() {

      this.mainSide.setMain(0)
  }
}
