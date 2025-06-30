import { Component, OnInit } from '@angular/core';
import { SyndicService } from '../../services/syndic.service';
import { Syndic } from '../../models/syndic.model';

@Component({
  selector: 'app-syndic-list',
  templateUrl: './syndic-list.component.html'
})
export class SyndicListComponent implements OnInit {
  syndics: Syndic[] = [];

  constructor(private syndicService: SyndicService) {}

  ngOnInit(): void {
    this.loadSyndics();
  }

  loadSyndics(): void {
    this.syndicService.getAllSyndics().subscribe(data => {
      this.syndics = data;
    });
  }

  deleteSyndic(id: number): void {
    this.syndicService.deleteSyndic(id).subscribe(() => {
      this.loadSyndics();
    });
  }
}
