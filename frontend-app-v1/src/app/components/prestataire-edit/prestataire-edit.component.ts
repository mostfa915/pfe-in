import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PrestataireService } from 'src/app/services/prestataire.service';
import { Prestataire } from 'src/app/models/prestataire.model';

@Component({
  selector: 'app-prestataire-edit',
  templateUrl: './prestataire-edit.component.html'
})
export class PrestataireEditComponent implements OnInit {
  prestataire: Prestataire = {
    id: 0,
    num: '',
    email: '',
    mdpHash: '',
    role: 'PRESTATAIRE',
    specialite: ''
  };

  constructor(
    private route: ActivatedRoute,
    private prestataireService: PrestataireService,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.prestataireService.getPrestataire(id).subscribe(data => {
      this.prestataire = data;
    });
  }

  updatePrestataire(): void {
    this.prestataireService.updatePrestataire(this.prestataire.id!, this.prestataire).subscribe(() => {
      this.router.navigate(['/prestataires']);
    });
  }
}
