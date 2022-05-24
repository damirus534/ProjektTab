import { Injectable } from '@angular/core';
import {BehaviorSubject} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class MainSideService {
  get category(): number {
    return this._category;
  }

  set category(value: number) {
    this._category = value;
  }

  main: number;
  private _category :number;
  private event$: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);

  constructor() {
    this.main = 0;
    this._category=0
  }

  setMain(main: number) {
    this.main = main;
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
