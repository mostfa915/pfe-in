import { Component, OnInit } from '@angular/core';
import {  PrestataireService } from '../../services/prestataire.service';
import { Prestataire } from 'src/app/models/prestataire.model';
import {  Router } from '@angular/router';

@Component({
  selector: 'app-prestataire',
  templateUrl: './prestataire.component.html'
})
export class PrestataireComponent implements OnInit {
  prestataires: Prestataire[] = [];
  newPrestataire: Prestataire = {
    num: '',
    email: '',
    mdpHash: '',
    role: 'PRESTATAIRE',
    specialite: ''
  };

  constructor(private prestataireService: PrestataireService,   private router: Router) {}

  ngOnInit(): void {
   
  }

  loadPrestataires(): void {
    this.prestataireService.getPrestataires().subscribe(data => {
      this.prestataires = data;
    });
  }

  addPrestataire(): void {
    this.prestataireService.addPrestataire(this.newPrestataire).subscribe(() => {
      this.newPrestataire = { num: '', email: '', mdpHash: '', role: 'PRESTATAIRE', specialite: '' };
        this.router.navigate(['/prestatairesl']);
    });
  }

  deletePrestataire(id: number): void {
    this.prestataireService.deletePrestataire(id).subscribe(() => {
      this.loadPrestataires();
    });
  }
}
