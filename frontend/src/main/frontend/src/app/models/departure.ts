export class Departure{

  time: string;
  dayOfWeek: string;
  lineName: string;

  constructor (time: string, dayOfWeek: string, lineName: string) {
    this.time = time;
    this.dayOfWeek = dayOfWeek;
    this.lineName = lineName;
  }
}
