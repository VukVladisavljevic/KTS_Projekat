export class Ticket{

  id: string;
  token: string;
  ticketType: string;
  startTime: Date;
  endTime: Date;
  active: boolean;

  constructor (token: string, ticketType: string, startTime: Date, endTime: Date, id: string, activated: boolean) {
    this.token = token;
    this.ticketType = ticketType;
    this.startTime = startTime;
    this.endTime = endTime;
    this.id = id;
    this.active = activated;
  }
}
