import {Deserializable} from './deserializable';
import * as _ from 'lodash';
import {StationModel} from './station.model';
export class LineModel implements Deserializable{

  name: string;
  stations: Array<object>;

  constructor(name, stations) {
    this.name = name;
    //this.stations = _.map(stations, (station) => new StationModel(station));
    this.stations = stations;
  }

  deserialize(input: any): this {
    Object.assign(this, input);
    this.stations = _.map(input.stations, (station) => new StationModel(station));
    return this;
  }

}
