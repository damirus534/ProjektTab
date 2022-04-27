import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  constructor(private authService: AuthService) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    const helper = new JwtHelperService();
    const token = localStorage.getItem('JWT_TOKEN');
    if(token !== null) {
      // TODO: zrobić walidację roli ORAZ PIERWEJ zrobić weryfikację zalogowania
      const role = helper.decodeToken(token).role;
      return true;
    } else {
      return false;
    }
  }
  
}
