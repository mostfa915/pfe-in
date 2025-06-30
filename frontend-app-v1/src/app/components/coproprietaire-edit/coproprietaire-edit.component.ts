import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CoproprietaireService } from '../../services/coproprietaire.service';
import { Location } from '@angular/common';

@Component({
  selector: 'app-coproprietaire-edit',
  templateUrl: './coproprietaire-edit.component.html',
})
export class CoproprietaireEditComponent implements OnInit {
  coproForm: FormGroup;
  
  coproId: number = 0;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private coproService: CoproprietaireService,
    private router: Router,
    private location: Location
  ) {
    this.coproForm = this.fb.group({
      num: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      mdpHash: ['', Validators.required],
      role: ['COPROPRIETAIRE'],
      soldeCharges: [0, Validators.required],
    });
  }

  ngOnInit(): void {
   
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.coproId = +id; // Conversion en nombre si 'id' est défini
      this.coproService.getById(this.coproId).subscribe((data) => {
        this.coproForm.patchValue(data);
      });
    } else {
      console.error('ID est manquant');
      // Gérer le cas où l'ID est absent (par exemple, rediriger ou afficher un message d'erreur)
    }
  }

 






  onSubmit() {
    if (this.coproForm.valid) {
      this.coproService.update(this.coproId, this.coproForm.value).subscribe(() => {
        this.router.navigate(['/coproprietaires']);
      });
    }
  }

  goBack(): void {
    this.location.back();
  }
}
