import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { DocumentService } from '../../services/document.service';
import { Document } from '../../models/document.model';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-document-edit',
  templateUrl: './document-edit.component.html',
  styleUrls: ['./document-edit.component.css']
})
export class DocumentEditComponent implements OnInit {

  documentForm!: FormGroup;
  documentId!: number;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private documentService: DocumentService
  ) {}

  ngOnInit(): void {
    this.documentId = +this.route.snapshot.paramMap.get('id')!;
    this.initForm();
    this.loadDocument();
  }

  initForm() {
    this.documentForm = this.fb.group({
      titre: ['', Validators.required],
      type: ['', Validators.required],
      nomFichier: [''],
      cheminFichier: ['']
    });
  }

  loadDocument() {
   
    this.documentService.getDocumentById(this.documentId.toString()).subscribe(doc => {
      this.documentForm.patchValue(doc);
    });
  }

  onSubmit() {
    if (this.documentForm.valid) {
      this.documentService.updateDocument(this.documentId, this.documentForm.value).subscribe(() => {
        alert('Document modifié avec succès');
        this.router.navigate(['/documents']);
      });
    }
  }
}
