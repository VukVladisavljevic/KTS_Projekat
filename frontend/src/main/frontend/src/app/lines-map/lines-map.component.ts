import { Component, OnInit } from '@angular/core';
import { MapsAPILoader, AgmMap } from '@agm/core';
import { GoogleMapsAPIWrapper } from '@agm/core/services';
import { ViewChild } from '@angular/core'
import { AgmDirectionModule } from 'agm-direction';
import { MatFormFieldModule, MatOptionModule, MatSelectModule } from
   '@angular/material';
import {LinesMapService} from './lines-map.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';

declare var google: any;
@Component({
  selector: 'app-lines-map',
  templateUrl: './lines-map.component.html',
  styleUrls: ['./lines-map.component.css']
})

export class LinesMapComponent implements OnInit {
  geocoder:any;
  @ViewChild(AgmMap) map: AgmMap;
  source: object;
  destination: object
  lines: object;
  visible: boolean = false;
  waypoints: object;

  public renderOptions = {
      suppressMarkers: true,
  }

  public markerOptions = {
      origin: {
          icon: 'https://www.shareicon.net/data/48x48/2015/09/15/641263_stop_512x512.png',
      },
      waypoints: {
          icon: 'https://www.shareicon.net/data/48x48/2015/09/15/641263_stop_512x512.png',
      },
      destination: {
          icon: 'https://www.shareicon.net/data/48x48/2015/09/15/641263_stop_512x512.png',
      },
  }
  constructor(public mapsApiLoader: MapsAPILoader,
              private wrapper: GoogleMapsAPIWrapper,
              private linesMapService: LinesMapService) {
    this.mapsApiLoader = mapsApiLoader;
    this.wrapper = wrapper;
    this.linesMapService = linesMapService;
    this.mapsApiLoader.load().then(() => {
      this.geocoder = new google.maps.Geocoder();
    });
  }

  ngOnInit() {
    this.linesMapService.getLineStations()
      .then(response => {
        this.formatLines(response);
      });
  }

  formatLines(data) {
    this.lines = data;
  }
  onChange(event) {
    this.visible = false;
    let waypointsMapping = [];
    event.value.waypoints.forEach((item) => {
      waypointsMapping.push({location: {lat: item.lat, lng: item.lng}, stopover:false});
    })
    this.waypoints = waypointsMapping;
    this.source = event.value.source;
    this.destination = event.value.destination;
  }
}
