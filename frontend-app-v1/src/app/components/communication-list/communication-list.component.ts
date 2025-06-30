import { Component, OnInit } from '@angular/core';
import { CommunicationService } from '../../services/communication.service';
import { Communication } from '../../models/communication.model';

@Component({
  selector: 'app-communication-list',
  templateUrl: './communication-list.component.html'
})
export class CommunicationListComponent implements OnInit {
  communications: Communication[] = [];

  constructor(private communicationService: CommunicationService) {}

  ngOnInit(): void {
    this.communicationService.getAll().subscribe(data => {
      this.communications = data;
    });
  }
}
