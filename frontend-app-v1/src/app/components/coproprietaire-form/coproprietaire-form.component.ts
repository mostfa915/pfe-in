import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CoproprietaireService } from '../../services/coproprietaire.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-coproprietaire-form',
  templateUrl: './coproprietaire-form.component.html',
})
export class CoproprietaireFormComponent {
  coproForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private coproService: CoproprietaireService,
    private router: Router
  ) {
    this.coproForm = this.fb.group({
      num: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required],
      role: ['COPROPRIETAIRE'],
      soldeCharges: [0, Validators.required],
    });
  }

  onSubmit() {
    if (this.coproForm.valid) {
      this.coproService.create(this.coproForm.value).subscribe(() => {
        this.router.navigate(['/coproprietaires']);
      });
    }
  }
}
