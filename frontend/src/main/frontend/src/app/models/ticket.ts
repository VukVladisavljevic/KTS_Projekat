export class Ticket{

  token: string;
  ticketType: string

  constructor (token: string, ticketType: string) {
    this.token = token;
    this.ticketType = ticketType;
  }
}
