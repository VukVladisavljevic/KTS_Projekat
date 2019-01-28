import { Component, OnInit } from '@angular/core';
import { AccountService } from '../services/auth/account.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { JwtService } from '../services/auth/jwt.service';
import { Profile } from '../models/profile';
import { ActivatedRoute } from '@angular/router';
import { PasswordValidator } from '../shared/validators/password.validator';
import { ProfileChange } from '../models/profileChange';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css'],
  providers: [AccountService, JwtService, PasswordValidator]
})
export class ProfileComponent implements OnInit {
  private profile: Profile;
  private myForm: FormGroup;
  private returnUrl;

  constructor(private accountService: AccountService, private formBuilder: FormBuilder, private jwtService: JwtService, private route: ActivatedRoute, private passwordValidator : PasswordValidator) { }

  ngOnInit() {
    this.accountService.getCurrentUser(this.jwtService.getUsernameFromToken()).subscribe(data => {
      let user = (data as Profile);
      console.log(data);
      this.profile = user;
      this.myForm = this.formBuilder.group({
        username: [this.profile.username, ],
        firstName: [this.profile.firstName, [Validators.required, Validators.minLength(2)]],
        lastName: [this.profile.lastName, [Validators.required, Validators.minLength(2)]],
        email: [this.profile.email, [Validators.required, Validators.email]],
        ticketsBought: [this.profile.ticketsBought],
        password: ['', [Validators.required, Validators.minLength(5)]],
        newPassword: ['', [Validators.required, Validators.minLength(5)]],
      });
    });
    this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
  }

  get firstName() {
    return this.myForm.get('firstName');
  }

  get lastName() {
    return this.myForm.get('lastName');
  }

  get username() {
    return this.myForm.get('username');
  }

  get email() {
    return this.myForm.get('email');
  }

  get password() {
    return this.myForm.get('password');
  }

  get newPassword() {
    return this.myForm.get('newPassword');
  }

  changeProfile() {
    this.password.setValue("unchanged");
    this.password.setValue("unchanged");
    let account = new ProfileChange(this.firstName.value, this.lastName.value, this.password.value, this.newPassword.value, this.email.value);
    this.accountService.changeProfile(account).subscribe();
  }

}
