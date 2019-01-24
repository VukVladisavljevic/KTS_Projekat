import {Component, OnInit, ViewChild} from '@angular/core';
import {AgmMap, GoogleMapsAPIWrapper, MapsAPILoader} from "@agm/core";
import {LinesMapService} from "../lines-map/lines-map.service";
import {MatDialog, MatDialogConfig} from "@angular/material";
import {AddStationComponent} from "./add-station/add-station.component";

declare var google: any;

@Component({
  selector: 'app-stations',
  templateUrl: './stations.component.html',
  styleUrls: ['./stations.component.css']
})
export class StationsComponent implements OnInit {
  geocoder:any;
  @ViewChild(AgmMap) map: AgmMap;
  private latitude;
  private longitude;

  constructor(public mapsApiLoader: MapsAPILoader,
              private wrapper: GoogleMapsAPIWrapper,
              private linesMapService: LinesMapService,
              private dialog: MatDialog) {
    this.mapsApiLoader = mapsApiLoader;
    this.wrapper = wrapper;
    this.linesMapService = linesMapService;
    this.mapsApiLoader.load().then(() => {
      this.geocoder = new google.maps.Geocoder();
    });
  }

  ngOnInit() {
  }

  placeMarker($event) {
    this.latitude = $event.coords.lat;
    this.longitude = $event.coords.lng;
    const dialogConfig = new MatDialogConfig();
    dialogConfig.autoFocus = true;
    dialogConfig.data = {latitude: this.latitude, longitude: this.longitude};
    this.dialog.open(AddStationComponent, dialogConfig);
    console.log($event);
    console.log($event.coords.lng);
  }
}
