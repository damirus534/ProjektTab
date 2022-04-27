import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';
import { JwtHelperService } from '@auth0/angular-jwt';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {

  private isLoggedIn: boolean = false;

  constructor(private authService: AuthService, private router: Router) {

  }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    
    // TODO: figure out the way if token is valid when user wants to go back to page
    this.authService.isLoggedIn.subscribe((result) => {
      this.isLoggedIn = result;
    });
    if(this.isLoggedIn) {
      return true;
    }
    window.alert("Wymagane logowanie");
    this.router.navigate(['/login']);
    return false;
    
  }

}
