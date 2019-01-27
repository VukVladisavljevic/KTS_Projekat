import {Deserializable} from './deserializable';

export class StationModel implements Deserializable{

  name: string;
  stations: Array<object>;

  constructor(input) {
    Object.assign(this, input);
  }

  deserialize(input: any): this {
    Object.assign(this, input);
    return this;
  }

}
