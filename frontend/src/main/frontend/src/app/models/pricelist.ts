export class Pricelist{

  startDate: string;
  endDate: string;
  ticketType: string;
  price : number;

  constructor (startDate: string, endDate: string, ticketType: string, price: number) {
    this.startDate = startDate;
    this.endDate = endDate;
    this.ticketType = ticketType;
    this.price = price;
  }
}
