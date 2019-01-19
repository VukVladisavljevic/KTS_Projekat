import { Injectable } from "@angular/core";
import { FormControl } from "@angular/forms";
// service
// error
import { AppError } from "../errors/app-error";
import { BadRequestError } from "../errors/bad-request-error";
import {AuthService} from "../../services/auth/auth.service";

@Injectable()
export class UsernameUniqueValidator {
  constructor(private authService: AuthService){ }

  isUsernameTaken(control: FormControl): any {
    return new Promise(resolve => {
      setTimeout(() => {
        this.authService.checkUsername(control.value).subscribe(data => {
          if(data)
            resolve({ 'usernameIsTaken' : true });
          else
            resolve(null);
        }, (error: AppError) => {
          if(error instanceof BadRequestError)
            resolve(null);
        });
      }, 2000);
    });
  }
}
