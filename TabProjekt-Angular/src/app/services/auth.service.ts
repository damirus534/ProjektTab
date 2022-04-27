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
  isLoggedIn = this._isLoggedIn.asObservable();

  constructor(private httpClient: HttpClient, private hashService: HashService) {
    const token = localStorage.getItem('JWT_TOKEN');

    // TODO: check if token is not expired
    this._isLoggedIn.next(!!token);
  }

  login(email: string, password: string): Observable<any> {
    const encryptedPassword = this.hashService.set(environment.hashSecret, password);
    return this.httpClient.post<any>(`${environment.baseUrl}/users/login`, { login: email, password: encryptedPassword }).pipe(
      tap((response: any) => {
        localStorage.setItem('JWT_TOKEN', response.JWT_TOKEN);
        this._isLoggedIn.next(true);
      })
    );
  }

  logout() {
    localStorage.removeItem('JWT_TOKEN');
    this._isLoggedIn.next(false);
  }

}
