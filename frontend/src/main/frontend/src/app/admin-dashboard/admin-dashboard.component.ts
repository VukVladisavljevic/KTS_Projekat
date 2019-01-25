import { Component, OnInit } from '@angular/core';
import * as $ from 'jquery';


@Component({
  selector: 'app-admin-dashboard',
  templateUrl: './admin-dashboard.component.html',
  styleUrls: ['./admin-dashboard.component.css'],
})
export class AdminDashboardComponent implements OnInit {
  constructor() { }

  ngOnInit() {
  }

  onClick() {
    $("a").click(function() {
      $("a").removeClass("current");
      $(this).addClass("current");
    });
  }

  showRegisterPrompt() {
    
  }
}