
import {throwError as observableThrowError, Observable} from 'rxjs';
import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Account} from "../../models/account";
import {JwtToken} from "../../models/jwt-token";
// errors
import { AppError } from "../../shared/errors/app-error";
import { BadRequestError } from "../../shared/errors/bad-request-error";
import { NotFoundError } from "../../shared/errors/not-found-error";
import { JwtService } from './jwt.service';

@Injectable()
export class AccountService {
  private readonly urlBase = '/api';

  constructor(private http: HttpClient, private jwtService: JwtService) { }

  save(account : Account)
    {
        return this.http.post('http://localhost:8080/api/register', account)
          /*.map((token: JwtToken) => {
            if(token.value) {
              this.jwtService.setToken(token.value);
              return true;
            }
            else return false;
          }).catch(this.handleErrors);*/
    }

  private handleErrors(response: Response) {
    if(response.status === 400)
      return observableThrowError(new BadRequestError());
    else if(response.status === 404)
      return observableThrowError(new NotFoundError());
    return observableThrowError(new AppError(response));
  }

  public getCurrentUser(){
    return this.http.get('http://localhost:8080/api/current_user');
  }

  public changePassword(passwordChange){
    return this.http.post('http://localhost:8080/api/change_password', passwordChange);
  }

  public changeProfile(account){
    return this.http.post('http://localhost:8080/api/profile', account);
  }

}