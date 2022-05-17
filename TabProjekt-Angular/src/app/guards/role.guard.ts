import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../services/auth.service';

@Injectable({
  providedIn: 'root'
})
export class RoleGuard implements CanActivate {

  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
    return this.isAuthorized(route);
  }

  private isAuthorized(route: ActivatedRouteSnapshot): boolean {
    const expectedRoles = route.data['roles'] as string[];
    const userRole = this.authService.userToken.role;
    const isRoleCorrect = expectedRoles.includes(userRole);
    if(!isRoleCorrect) {
      if(userRole === 'admin')
        this.router.navigate(['/admin']);
      else
        this.router.navigate(['']);
    }
    return isRoleCorrect;
  }
  
}
