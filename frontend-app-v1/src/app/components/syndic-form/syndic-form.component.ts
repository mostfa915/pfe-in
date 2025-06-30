import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { SyndicService } from '../../services/syndic.service';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-syndic-form',
  templateUrl: './syndic-form.component.html'
})
export class SyndicFormComponent implements OnInit {
  syndicForm!: FormGroup;
  syndicId!: number;
  isEdit = false;

  constructor(
    private fb: FormBuilder,
    private syndicService: SyndicService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.syndicForm = this.fb.group({
      num: [''],
      email: [''],
      password: [''],
    role: ['SYNDIC'],

    });

    this.route.paramMap.subscribe(params => {
      const id = params.get('id');
      if (id) {
        this.isEdit = true;
        this.syndicId = +id;
        this.syndicService.getSyndicById(this.syndicId).subscribe(data => {
          this.syndicForm.patchValue(data);
        });
      }
    });
  }

  onSubmit(): void {
    const syndicData = { ...this.syndicForm.value, role: 'SYNDIC' };
    if (this.isEdit) {
      this.syndicService.updateSyndic(this.syndicId, syndicData).subscribe(() => {
        this.router.navigate(['/syndics']);
      });
    } else {
      this.syndicService.createSyndic(syndicData).subscribe(() => {
        this.router.navigate(['/syndics']);
      });
    }
  }
}
