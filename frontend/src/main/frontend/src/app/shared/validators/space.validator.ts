import { AbstractControl, ValidationErrors } from "@angular/forms";
import {AuthService} from "../../services/auth/auth.service";

export class SpaceValidator {

  constructor(private authService: AuthService) { }

  static cannotContainSpace(control: AbstractControl) : ValidationErrors | null {
    if((control.value as string).indexOf(' ') != -1)
      return { cannotContainSpace : true };

    return null;
  }
}
