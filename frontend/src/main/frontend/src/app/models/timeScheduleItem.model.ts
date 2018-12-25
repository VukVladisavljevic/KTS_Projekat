import {Deserializable} from './deserializable';

export class TimeScheduleItemModel implements Deserializable{

  name: string;
  active: boolean;
  idLine: string;


  deserialize(input: any): this {
    Object.assign(this, input);
    return this;
  }

}
