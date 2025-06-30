import { Component, OnInit } from '@angular/core';
import { CoproprietaireService, Coproprietaire } from '../../services/coproprietaire.service';

@Component({
  selector: 'app-coproprietaire-list',
  templateUrl: './coproprietaire-list.component.html',
})
export class CoproprietaireListComponent implements OnInit {
  coproprietaires: Coproprietaire[] = [];

  constructor(private coproService: CoproprietaireService) {}

  ngOnInit(): void {
    this.coproService.getAll().subscribe((data) => {
      this.coproprietaires = data;
      console.log(this.coproprietaires);
    });
  }
  deleteCoproprietaire(id: number) {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce copropriétaire ?')) {
      this.coproService.delete(id).subscribe(() => {
        this.coproprietaires = this.coproprietaires.filter(copro => copro.id !== id);
      });
    }
  }
  
}

