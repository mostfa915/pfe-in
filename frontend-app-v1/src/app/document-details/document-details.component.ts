import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { DocumentService } from '../services/document.service'; // Importer le service qui gère les documents


@Component({
  selector: 'app-document-details',
  templateUrl: './document-details.component.html',
  styleUrls: ['./document-details.component.css']
})
export class DocumentDetailsComponent implements OnInit {
  document: any;

  constructor(
    private route: ActivatedRoute,
    private documentService: DocumentService
  ) {}

  ngOnInit(): void {
    const documentId = this.route.snapshot.paramMap.get('id');
    if (documentId) {
      // Passer documentId comme string
      this.documentService.getDocumentById(documentId).subscribe((data: any) => {
        data.cheminFichier= data.fileUrl = `http://localhost:8081/uploads/${data.nomFichier}`;
        data.fileUrl = `http://localhost:8081/uploads/${data.nomFichier}`;
        this.document = data;
        console.log(data);
      });
    }
}
}