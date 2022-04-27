import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { HashService } from './hash.service';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private httpClient: HttpClient, private hashService: HashService) {
  }

  login(email: string, password: string): Observable<string> {
    const encryptedPassword = this.hashService.set(environment.hashSecret, password);
    const requestOptions: Object = { responseType: 'text' };
    return this.httpClient.post<string>(`${environment.baseUrl}/users/login`, { login: email, password: encryptedPassword }, requestOptions);
  }
}
