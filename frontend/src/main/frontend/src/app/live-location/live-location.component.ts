import { Component, OnInit } from '@angular/core';
import { MapsAPILoader, AgmMap } from '@agm/core';
import { GoogleMapsAPIWrapper } from '@agm/core/services';
import { ViewChild } from '@angular/core'
import { AgmDirectionModule } from 'agm-direction';
import { MatFormFieldModule, MatOptionModule, MatSelectModule } from
    '@angular/material';
import {LinesMapService} from '../lines-map/lines-map.service';
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import * as _ from 'lodash';

declare var google: any;
@Component({
  selector: 'app-live-location',
  templateUrl: './live-location.component.html',
  styleUrls: ['./live-location.component.css']
})

export class LiveLocationComponent implements OnInit {
  geocoder:any;
  @ViewChild(AgmMap) map: AgmMap;
  source: object;
  destination: object
  lines: object;
  visible: boolean = false;
  waypoints: object;
  private serverUrl = 'http://localhost:8080/socket'
  private title = 'WebSockets chat';
  private stompClient;
  private locations;
  private longitude;
  private latitude;
  private transport;
  private busLocation = {text: 'Current bus location', color: 'black', fontWeight:"bold", fontSize:"16px"};

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
    this.initializeWebSocketConnection();
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

  initializeWebSocketConnection(){
    let ws = new SockJS(this.serverUrl);
    this.stompClient = Stomp.over(ws);
    let that = this;
    this.stompClient.connect({}, function(frame) {
      that.stompClient.subscribe("/live-location", (message) => {
        console.log(message);
        that.locations = JSON.parse(message.body);
        if(!that.transport || !that.transport.id){
          console.log(that.transport);
          that.transport = _.find(that.locations, { idLine: that.transport });
          that.longitude = that.transport ? that.transport.station.lng : null;
          that.latitude = that.transport ? that.transport.station.lat : null;
          return;
        }
        that.transport = _.find(that.locations, { id: that.transport.id });
        console.log(that.transport);
        that.longitude = that.transport ? that.transport.station.lng : null;
        that.latitude = that.transport ? that.transport.station.lat : null;
        console.log(that.longitude);
      });
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
    let idline = event.value.line.idLine;
    this.transport = _.find(this.locations, { idLine: idline });
    if(!this.transport) {
      this.transport = idline;
      this.longitude = null;
      this.latitude = null;
      return;
    }
    this.longitude = this.transport.station.lng;
    this.latitude = this.transport.station.lat;
  }

  ngOnDestroy() {
    this.stompClient.disconnect(function() {
      console.log("DISCONNECTED");
    });
  }
}
