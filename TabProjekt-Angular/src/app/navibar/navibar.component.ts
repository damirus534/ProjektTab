import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";

@Component({
  selector: 'app-navibar',
  templateUrl: './navibar.component.html',
  styleUrls: ['./navibar.component.css']
})
export class NavibarComponent implements OnInit {

  constructor(private router: Router) { }

  ngOnInit(): void {

  }

  checkURL() {
    console.log(this.router.url)
    if(this.router.url==="/"){

      window.location.reload();
    }

  }
}
