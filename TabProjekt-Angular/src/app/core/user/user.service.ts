import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from './user';
import { HashService } from '../../services/hash.service';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private httpClient: HttpClient, private hashService: HashService) { }

  verify(user: User, loginForm: FormGroup) : Observable<Boolean> {
    user.login = loginForm.controls['loginEmail'].value;
    const password = loginForm.controls['loginPassword'].value;
    const encryptedPassword = this.hashService.set(environment.hashSecret, password);
    user.password = encryptedPassword;
    return this.httpClient.post<Boolean>(`${environment.baseUrl}/users/verify`, user);
  }

  saveUser(user: User) : Observable<User> {
    return this.httpClient.post<User>(`${environment.baseUrl}/users`, user);
  }

  isUserExists(user: User, email: string, password: string, address: string) : Observable<boolean> {
    user.login = email;
    const encryptedPassword = this.hashService.set(environment.hashSecret, password);
    user.password = encryptedPassword;
    user.address = address;
    user.status = 'user';
    return this.httpClient.post<boolean>(`${environment.baseUrl}/users/exists`, user);
  }

}
