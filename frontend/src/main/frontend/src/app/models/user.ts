import { Login } from "./login"
import { AccountAuthority } from './accountAuthority';

export class User{
  constructor(
              public loginAccount : Login,
              public firstName : string,
              public lastName : string,
              public email : string,
              ){}
}
