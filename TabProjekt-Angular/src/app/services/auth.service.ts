import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { JwtHelperService } from '@auth0/angular-jwt';
import { BehaviorSubject, Observable, tap } from 'rxjs';
import { environment } from 'src/environments/environment';
import { UserToken } from '../core/userToken.model';
import { HashService } from './hash.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private _isLoggedIn = new BehaviorSubject<boolean>(false);
  private readonly TOKEN_NAME = 'JWT_TOKEN';
  isLoggedIn = this._isLoggedIn.asObservable();
  userToken!: UserToken;
  private jwtHelper = new JwtHelperService();

  get token() {
    return localStorage.getItem(this.TOKEN_NAME);
  }

  constructor(private httpClient: HttpClient, private hashService: HashService) {
    // Check if token exists and is not expired
    if(!!this.token && !this.jwtHelper.isTokenExpired(this.token)) {
      this.userToken = this.getUserToken(this.token);
      this._isLoggedIn.next(true);
    } else {
      this._isLoggedIn.next(false);
    }


  }

  login(email: string, password: string): Observable<any> {
    const encryptedPassword = this.hashService.set(environment.hashSecret, password);
    return this.httpClient.post<any>(`${environment.baseUrl}/users/login`, { login: email, password: encryptedPassword }).pipe(
      tap((response: any) => {
        if(response != null) {
          localStorage.setItem(this.TOKEN_NAME, response.JWT_TOKEN);
          this._isLoggedIn.next(true);
          this.userToken = this.getUserToken(this.token!);
        }
      })
    );
  }

  logout() {
    localStorage.removeItem(this.TOKEN_NAME);
    this._isLoggedIn.next(false);
  }

  private getUserToken(token: string): UserToken {
    return JSON.parse(atob(token.split('.')[1])) as UserToken;
  }

}
