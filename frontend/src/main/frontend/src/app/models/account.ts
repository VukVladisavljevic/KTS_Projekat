import { Login } from "./login"

export class Account{
  constructor(public loginAccount : Login,
              public firstName : string,
              public lastName : string,
              public email : string){}
}
