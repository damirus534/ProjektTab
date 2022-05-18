import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MainSideService {

  main:number;
  private event$:BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor() {
    this.main = 0;
  }

  setMain(main: number) {
    this.main=main
  }

  getMain(): number {
    return this.main;
  }

  setEvent(bol: boolean) {
    this.event$.next(bol);
  }

  getEvent() {
    return this.event$.asObservable();
  }

}
