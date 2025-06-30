import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Prestataire } from 'src/app/models/prestataire.model';

@Injectable({
  providedIn: 'root'
})
export class PrestataireService {
  private apiUrl = 'http://localhost:8081/api/prestataires';

  constructor(private http: HttpClient) {}

  getPrestataires(): Observable<Prestataire[]> {
    return this.http.get<Prestataire[]>(this.apiUrl);
  }

  getPrestataireById(id: number): Observable<Prestataire> {
    return this.http.get<Prestataire>(`${this.apiUrl}/${id}`);
  }

  addPrestataire(prestataire: Prestataire): Observable<Prestataire> {
    return this.http.post<Prestataire>(this.apiUrl, prestataire);
  }

  updatePrestataire(id: number, prestataire: Prestataire): Observable<Prestataire> {
    return this.http.put<Prestataire>(`${this.apiUrl}/${id}`, prestataire);
  }

  deletePrestataire(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
  getPrestataire(id: number): Observable<Prestataire> {
    return this.http.get<Prestataire>(`${this.apiUrl}/${id}`);
  }
  
}
