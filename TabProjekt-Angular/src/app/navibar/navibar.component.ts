import {Component, OnInit, QueryList, ViewChildren} from '@angular/core';
import {Router} from "@angular/router";
import {MainSideService} from "../core/website-service/main-side/main-side.service";
import {AuthService} from "../services/auth.service";
import {SidenavComponent} from "../sidenav/sidenav.component";

@Component({
  selector: 'app-navibar',
  templateUrl: './navibar.component.html',
  styleUrls: ['./navibar.component.css']
})
export class NavibarComponent implements OnInit {
  mainSide!:MainSideService
  constructor(mainSide:MainSideService,public authService: AuthService) {
    this.mainSide=mainSide

  }


  ngOnInit(): void {

  }

  checkURL() {

      this.mainSide.setMain(0)
      this.mainSide.setEvent(true);
  }
}
