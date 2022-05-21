import {Component, OnInit} from '@angular/core';
import {MainSideService} from "../core/website-service/main-side/main-side.service";
import {AuthService} from "../services/auth.service";

@Component({
  selector: 'app-navibar',
  templateUrl: './navibar.component.html',
  styleUrls: ['./navibar.component.css']
})
export class NavibarComponent implements OnInit {

  constructor(public mainSide: MainSideService, public authService: AuthService) {

  }

  ngOnInit(): void {

  }

  checkURL() {
    this.mainSide.setMain(0)
    this.mainSide.setEvent(true);
  }
}
