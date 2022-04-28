import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HashService } from './hash.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _isLoggedIn = new BehaviorSubject<boolean>(false);
  private readonly TOKEN_NAME = 'JWT_TOKEN';
  isLoggedIn = this._isLoggedIn.asObservable();

  get token() {
    return localStorage.getItem(this.TOKEN_NAME);
  }

  constructor(private httpClient: HttpClient, private hashService: HashService) {
    // TODO: check if token is not expired
    this._isLoggedIn.next(!!this.token);
  }

  login(email: string, password: string): Observable<any> {
    const encryptedPassword = this.hashService.set(environment.hashSecret, password);
    return this.httpClient.post<any>(`${environment.baseUrl}/users/login`, { login: email, password: encryptedPassword }).pipe(
      tap((response: any) => {
        if(response != null) {
          localStorage.setItem(this.TOKEN_NAME, response.JWT_TOKEN);
          this._isLoggedIn.next(true);
        }
      })
    );
  }

  logout() {
    localStorage.removeItem(this.TOKEN_NAME);
    this._isLoggedIn.next(false);
  }

}
