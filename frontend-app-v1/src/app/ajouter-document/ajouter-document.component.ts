import { Component } from '@angular/core';
import { DocumentService } from '../services/document.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-ajouter-document',
  templateUrl: './ajouter-document.component.html',
  styleUrls: ['./ajouter-document.component.css']
})
export class AjouterDocumentComponent {
  document = {
    titre: '',
    type: 'AUTRE',
    nomFichier: ''
  };

  selectedFile: File | null = null;

  constructor(private documentService: DocumentService, private router: Router) {}

  onFileSelected(event: Event): void {
    const input = event.target as HTMLInputElement;
    if (input?.files?.length) {
      const file = input.files[0];
      console.log('Fichier sélectionné:', file);
      // Ici, vous pouvez traiter le fichier, comme le convertir en base64 ou l'envoyer au backend
   this.selectedFile=file;
    }
  }
  

  onSubmit(): void {
    if (!this.selectedFile) {
      alert('Veuillez sélectionner un fichier.');
      return;
    }

    const formData = new FormData();
    formData.append('file', this.selectedFile);
    formData.append('titre', this.document.titre);
    formData.append('type', this.document.type);

    this.documentService.uploadDocument(formData).subscribe(() => {
      this.router.navigate(['/documents']);
    });
  }
}
