import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { PrestataireService } from 'src/app/services/prestataire.service';
import { Prestataire } from '../../models/prestataire.model';

@Component({
  selector: 'app-prestataire-list',
  templateUrl: './prestataire-list.component.html'
})
export class PrestataireListComponent implements OnInit {
  prestataires: Prestataire[] = [];

  constructor(private prestataireService: PrestataireService, private router: Router) {}

  ngOnInit(): void {
    this.loadPrestataires();
  }

  loadPrestataires(): void {
    this.prestataireService.getPrestataires().subscribe(data => {
      this.prestataires = data;
    });
  }

  deletePrestataire(id: number): void {
    this.prestataireService.deletePrestataire(id).subscribe(() => this.loadPrestataires());
  }

  goToEdit(id: number): void {
    this.router.navigate(['/prestataires/edit', id]);
  }
}
