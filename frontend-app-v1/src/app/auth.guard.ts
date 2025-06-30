import { Injectable } from '@angular/core';
import { CanActivate, Router, ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';
import { AuthService } from '../../src/app/services/auth.service'; // Service où vous gérez l'authentification

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  constructor(private authService: AuthService, private router: Router) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ): boolean {
    if (this.authService.isLoggedIn()) {
      console.log("xx")
      return true; // Autorise l'accès si l'utilisateur est connecté
    } else {
      this.router.navigate(['/']); // Redirige vers la page de login
      return false;
    }
  }

  
}