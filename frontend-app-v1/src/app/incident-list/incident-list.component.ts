import { Component, OnInit } from '@angular/core';
import { IncidentService } from '../services/incident.service';
import { Incident } from '../models/incident.model';

@Component({
  selector: 'app-incident-list',
  templateUrl: './incident-list.component.html',
  styleUrls: ['./incident-list.component.css']
})
export class IncidentListComponent implements OnInit {

  incidents: Incident[] = [];
  document: any;
  constructor(private incidentService: IncidentService) { }

  ngOnInit(): void {
    // Récupérer la liste des incidents au chargement du composant
    this.incidentService.getIncidents().subscribe(
      (data:any) => {
        this.incidents = data;
        
       console.log(data
        );
        
      },
      (error) => {
        console.error('Erreur lors de la récupération des incidents:', error);
      }
    );
  }

  onDeleteIncident(id: number): void {
  if (confirm('Voulez-vous vraiment supprimer cet incident ?')) {
    this.incidentService.deleteIncident(id).subscribe({
      next: () => {
        // Actualiser la liste après suppression
      
        alert('Incident supprimé avec succès');
      },
      error: (err) => {
        console.error('Erreur lors de la suppression :', err);
        alert('Erreur lors de la suppression de l’incident.');
      }
    });
  }
}

}
