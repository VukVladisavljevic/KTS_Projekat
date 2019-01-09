import { AbstractControl } from "@angular/forms";
import { Injectable } from "@angular/core";

@Injectable()
export class PasswordValidator {
  matchPasswords(control: AbstractControl) : any {
    let password = control.get('password').value;
    let confirm = control.get('confirmPassword').value;

    if(password.trim() != '' && confirm.trim() != '' && password != confirm)
      return { matchPasswords: true };

    return null;
  }
}
