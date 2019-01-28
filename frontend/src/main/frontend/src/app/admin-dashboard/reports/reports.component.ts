import { Component, OnInit } from '@angular/core';
import { NgbDate } from '@ng-bootstrap/ng-bootstrap';
import { RequestDate } from '../../models/requestDate';
import { NgbDateISOParserFormatter } from '@ng-bootstrap/ng-bootstrap/datepicker/ngb-date-parser-formatter';
import { ReportsService } from '../../services/reports.service';
import { Report } from '../../models/report';

@Component({
  selector: 'app-reports',
  templateUrl: './reports.component.html',
  styleUrls: ['./reports.component.css']
})
export class ReportsComponent implements OnInit {
  private startDate;
  private endDate;
  private maxDate;
  private today;
  private report = new Report(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
  constructor(private reportsService: ReportsService) {
    let td = new Date();
    this.startDate = new NgbDate(td.getFullYear(), td.getMonth()+1, td.getDate());
    this.endDate = new NgbDate(td.getFullYear(), td.getMonth()+1, td.getDate());
    this.today = new NgbDate(td.getFullYear(), td.getMonth()+1, td.getDate());
   }

  ngOnInit() {
  }

  refresh() {
    let sd = this.startDate.day;
    let sm = this.startDate.month;
    let sy = this.startDate.year;
    let ed = this.endDate.day;
    let em = this.endDate.month;
    let ey = this.endDate.year;
    let sDate = new Date(sy, sm, sd);
    let eDate = new Date(ey, em, ed);
    let requestDate = new RequestDate(sDate, eDate);
    if(sDate > eDate) {
      console.log("error");
      return;
    }
    this.reportsService.getReports(requestDate).subscribe(data => {
      this.report = data;
    });
  }
}
