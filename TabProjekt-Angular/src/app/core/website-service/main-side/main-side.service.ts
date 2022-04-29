import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class MainSideService {
  main:number
  constructor() {
    this.main=0
  }
  setMain(main:number){
    this.main=main
  }
  getMain():number{
    console.log(this.main)
    return this.main
  }
}
