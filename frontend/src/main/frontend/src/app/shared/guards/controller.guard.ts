import { ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot } from "@angular/router";
import { Injectable } from "@angular/core";
import { JwtService } from '../../services/auth/jwt.service';

@Injectable()
export class ControllerGuard implements CanActivate {

  constructor(private router: Router, private jwtService: JwtService) { }

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
    if(this.jwtService.hasRole('CONTROLLER')) return true;

    // logged user hasn't role CONTROLLER
    this.router.navigate(['home']);
    return false;
  }
}
