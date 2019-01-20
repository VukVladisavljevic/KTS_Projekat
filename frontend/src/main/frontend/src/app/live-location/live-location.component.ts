import {Component, OnInit, ViewChild} from '@angular/core';
import {AgmMap, MapsAPILoader} from "@agm/core";
import { GoogleMapsAPIWrapper } from '@agm/core/services';
import {LiveLocationService} from "../services/live-location.service";
import * as Stomp from 'stompjs';
import * as SockJS from 'sockjs-client';
import {LinesService} from "../services/lines.service";
import * as _ from 'lodash';

declare var google: any;
@Component({
  selector: 'app-live-location',
  templateUrl: './live-location.component.html',
  styleUrls: ['./live-location.component.css']
})
export class LiveLocationComponent implements OnInit {
  private serverUrl = 'http://localhost:8080/socket'
  private title = 'WebSockets chat';
  private stompClient;
  private lines;
  private longitude;
  private latitude;
  private locations;
  private transport;
  private visible = false;
  geocoder:any;
  @ViewChild(AgmMap) map: AgmMap;
  constructor(public mapsApiLoader: MapsAPILoader,
              private wrapper: GoogleMapsAPIWrapper,
              private liveLocationService: LiveLocationService,
              private linesService: LinesService) {
    this.mapsApiLoader = mapsApiLoader;
    this.initializeWebSocketConnection();
    this.wrapper = wrapper;
    this.linesService = linesService;
    this.liveLocationService = liveLocationService;
    this.mapsApiLoader.load().then(() => {
      this.geocoder = new google.maps.Geocoder();
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
        if(!that.transport){
          return;
        }
        that.transport = _.find(that.locations, { id: that.transport.id });
        that.longitude = that.transport ? that.transport.station.lng : null;
        that.latitude = that.transport ? that.transport.station.lat : null;
      });
    });
  }

  ngOnInit() {
    this.linesService.getLines()
      .then(response => {
        this.lines = response;
    });
  }

  onChange(event) {
    console.log(this.locations);
    console.log(event.value);
    let idline = event.value.idLine;
    this.transport = _.find(this.locations, { idLine: idline });
    if(!this.transport) {
      this.visible = false;
      this.longitude = null;
      this.latitude = null;
      return;
    }
    this.visible = true;
    this.longitude = this.transport.station.lng;
    this.latitude = this.transport.station.lat;
  }
}
