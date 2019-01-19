export class Ticket{

  id: string;
  token: string;
  ticketType: string;
  startTime: Date;
  endTime: Date;

  constructor (token: string, ticketType: string, startTime: Date, endTime: Date, id: string) {
    this.token = token;
    this.ticketType = ticketType;
    this.startTime = startTime;
    this.endTime = endTime;
    this.id = id;
  }
}
