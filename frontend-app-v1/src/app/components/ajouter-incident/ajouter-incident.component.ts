import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { IncidentService } from '../../services/incident.service';
import { Incident } from '../../models/incident.model';

@Component({
  selector: 'app-ajouter-incident',
  templateUrl: './ajouter-incident.component.html',
  styleUrls: ['./ajouter-incident.component.css']
})
export class AjouterIncidentComponent {
  incident: Incident = {
    id : 0,
    description: '',
    priorite: 'BASSE',
    statut: 'NOUVEAU',
    survChat: '',
    declarant: { id: 1, nom: '',  email:'' } // Remplacer par l'utilisateur réel
  };

  file: File | null = null;
  showToast: boolean = false;

  constructor(private incidentService: IncidentService, private router: Router) { }

  // Gestion du fichier sélectionné
  onFileSelected(event: any): void {
    this.file = event.target.files[0];
  }

  // Soumettre l'incident avec le fichier
  onSubmit(): void {
    if (this.file) {
      this.incidentService.createIncident(this.incident, this.file).subscribe(() => {
        this.router.navigate(['/']); // Redirection vers la liste des incidents
        this.showToast = true;

      // Optionnel : cacher le toast après 3 secondes
      setTimeout(() => {
        this.showToast = false;
      }, 3000);
    
      });
    }
  }
}
