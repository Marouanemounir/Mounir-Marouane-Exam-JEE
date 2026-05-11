import { inject } from '@angular/core';
import { Router, CanActivateFn } from '@angular/router';
import { AuthService } from '../services/auth.service';

export const roleGuard: CanActivateFn = (route) => {
  const authService = inject(AuthService);
  const router = inject(Router);
  
  const expectedRoles = route.data['roles'] as Array<string>;
  const hasRole = expectedRoles.some(role => authService.hasRole(role));
  
  if (!hasRole) {
    router.navigate(['/dashboard']);
    return false;
  }
  
  return true;
};
