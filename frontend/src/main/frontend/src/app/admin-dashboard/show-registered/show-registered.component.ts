import { Component, OnInit } from '@angular/core';
import { AccountService } from '../../services/auth/account.service';
import {remove} from 'lodash';
import { BadRequestError } from '../../shared/errors/bad-request-error';
import { ForbiddenError } from '../../shared/errors/forbidden-error';
import { NotFoundError } from '../../shared/errors/not-found-error';
import { AppError } from '../../shared/errors/app-error';
import { ToasterService, ToasterConfig } from 'angular2-toaster';
import { BsModalService } from 'ngx-bootstrap';
import { JwtService } from '../../services/auth/jwt.service';

@Component({
  selector: 'app-show-registered',
  templateUrl: './show-registered.component.html',
  styleUrls: ['./show-registered.component.css'],
  providers: [AccountService, BsModalService, ToasterService]
})
export class ShowRegisteredComponent implements OnInit {
  
  private users;
  private toasterConfig: ToasterConfig;

  constructor(private accountService: AccountService, private toasterService: ToasterService, private jwtService: JwtService) { }

  ngOnInit() {
    this.accountService.getAllUsers()
      .then(response => {
        this.users = response;
      });
    console.log(this.users);
  }

  removeUser(user) {
    remove(this.users, user);
    this.accountService.removeUser(user.username).subscribe(data => {
    }, (error: AppError) => {
        if (error instanceof BadRequestError)
          this.toasterService.pop('error', 'Error','Invalid format of given data!');
        else if (error instanceof ForbiddenError)
          this.toasterService.pop('error', 'Error', 'Account not confirmed!');
        else if (error instanceof NotFoundError)
          this.toasterService.pop('error', 'Error', 'User doesn\'t exist!');
        else {
          this.toasterService.pop('error', 'Error', 'Something unexpected happened! \nSee information about error in console.');
          throw error;
        }
    });
  }

}
