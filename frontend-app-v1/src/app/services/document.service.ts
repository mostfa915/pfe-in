import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { Document } from '../models/document.model'; // Créé un modèle pour ton document
import { AuthService } from './auth.service';


@Injectable({
  providedIn: 'root'
})
export class DocumentService {

  private apiUrl = 'http://localhost:8081/api/documents'; // URL de ton API Spring Boot

  constructor(private http: HttpClient,    private authService: AuthService
  ) { }
   // Ajout d'une méthode pour inclure l'en-tête Authorization
    private getHttpOptions(): { headers: HttpHeaders } {
      const token = this.authService.getToken();
      let headers = new HttpHeaders({
        'Content-Type': 'application/json'
      });
  
      if (token) {
        headers = headers.set('Authorization', `Bearer ${token}`);
      }
  
      return { headers };
    }

  // Récupérer tous les documents
  getAllDocuments(): Observable<Document[]> {
    return this.http.get<Document[]>(this.apiUrl);
  }

  // Récupérer un document par ID
  getDocumentById(id: string): Observable<Document> {
    return this.http.get<Document>(`${this.apiUrl}/${id}`,this.getHttpOptions());
  }
 // Dans document.service.ts


  // Ajouter un document
  createDocument(document: Document): Observable<Document> {
    return this.http.post<Document>(this.apiUrl, document);
  }

  // Mettre à jour un document
  updateDocument(id: number, document: Document): Observable<Document> {
    return this.http.put<Document>(`${this.apiUrl}/${id}`, document);
  }

  // Supprimer un document
  deleteDocument(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  

  uploadDocument(formData: FormData) {
    return this.http.post(this.apiUrl + '/upload', formData);
  }

}

