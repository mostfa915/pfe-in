import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { IncidentService } from '../../services/incident.service';
import { FormBuilder, FormGroup } from '@angular/forms';

@Component({
  selector: 'app-edit-incident',
  templateUrl: './edit-incident.component.html'
})
export class EditIncidentComponent implements OnInit {
  incidentForm!: FormGroup;
  id!: number;

  constructor(
    private fb: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private incidentService: IncidentService
  ) {}

  ngOnInit(): void {
    this.id = Number(this.route.snapshot.paramMap.get('id'));
    this.incidentForm = this.fb.group({
      description: [''],
      priorite: [''],
      statut: ['']
    });

    this.incidentService.getIncidentById(this.id).subscribe(data => {
      this.incidentForm.patchValue(data);
    });
  }

 


  selectedFile!: File;

onFileSelected(event: any) {
  this.selectedFile = event.target.files[0];
}

onSubmit() {
  const incidentData = this.incidentForm.value;
  this.incidentService.updateIncidentWithFile(this.id, incidentData, this.selectedFile)
    .subscribe(() => {
      this.router.navigate(['/incidents']);
    });
}

}
