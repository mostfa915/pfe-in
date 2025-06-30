import { Component, OnInit } from '@angular/core';
import { DocumentService } from '../../services/document.service';
import { Document } from '../../models/document.model';

@Component({
  selector: 'app-document-list',
  templateUrl: './document-list.component.html',
  styleUrls: ['./document-list.component.css']
})
export class DocumentListComponent implements OnInit {

  documents: Document[] = [];

  constructor(private documentService: DocumentService) { }

  ngOnInit(): void {
    this.getDocuments();
  }

  getDocuments(): void {
    this.documentService.getAllDocuments().subscribe((documents: Document[]) => {
      this.documents = documents;
      console.log(this.documents);
    });
  }

  deleteDocument(id: number): void {
    if (confirm('Êtes-vous sûr de vouloir supprimer ce document ?')) {
      this.documentService.deleteDocument(id).subscribe(() => {
        this.documents = this.documents.filter(doc => doc.id.toString !== id.toString);
        alert('Document supprimé avec succès');
      }, error => {
        console.error('Erreur lors de la suppression :', error);
        alert('Erreur lors de la suppression du document.');
      });
    }
  }
  
}
