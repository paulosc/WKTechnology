import { Component, OnInit } from '@angular/core';
import { PersonService } from '../person.service';

@Component({
  selector: 'app-display-info',
  templateUrl: './display-info.component.html',
  styleUrls: ['./display-info.component.css']
})
export class DisplayInfoComponent implements OnInit {
  statesData: [string, number][] = [];
  imcData: any[] = [];
  obesePercentageData: any[] = [];
  mediaAgeData: any[] = [];
  possibleDonorsData: any;

  constructor(private personService: PersonService) {}

  ngOnInit(): void {
    this.personService.getCountByState().subscribe((data) => {
      this.statesData = data;
    });

    this.personService.getIMCByAge().subscribe((data) => {
      this.imcData = data;
    });

    this.personService.getPercentageOfObese().subscribe((data) => {
      this.obesePercentageData = data;
    });

    this.personService.getMediaAge().subscribe((data) => {
      this.mediaAgeData = data;
    });

    this.personService.getPossibleDonors().subscribe((data) => {
      this.possibleDonorsData = data;
    });
  }
}
