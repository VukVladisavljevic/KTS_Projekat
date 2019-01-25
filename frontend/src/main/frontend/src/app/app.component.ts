import { Component } from '@angular/core';
import { JwtService } from './services/auth/jwt.service';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'frontend';

  constructor(private jwtService: JwtService, private router: Router, private titleService: Title) {
    this.titleService.setTitle('KTS, NWT project');
  }
}
