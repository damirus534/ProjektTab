import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private baseUrl = "http://localhost:8080/users"

  constructor(private httpClient: HttpClient) { }

  // getUserList() : Observable<User[]> {
  //   return this.httpClient.get<User[]>(`${this.baseUrl}`);
  // }

  verify(user: User, loginForm: FormGroup) : Observable<Boolean> {
    user.login = loginForm.controls['loginEmail'].value;
    user.password = loginForm.controls['loginPassword'].value;
    return this.httpClient.post<Boolean>(`${this.baseUrl}/verify`, user);
  }

  saveUser(user: User) : Observable<User> {
    return this.httpClient.post<User>(`${this.baseUrl}`, user);
  }

  isUserExists(user: User, registerForm: FormGroup) : Observable<Boolean> {
    user.login = registerForm.controls['registerEmail'].value;
    user.password = registerForm.controls['registerPassword'].value;
    user.address = registerForm.controls['registerAddress'].value;
    user.status = 'user';
    return this.httpClient.post<Boolean>(`${this.baseUrl}/exists`, user);
  }

}
