import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment'; // Vérifie l'URL de l'API
import { Incident } from '../models/incident.model'; 
@Injectable({
  providedIn: 'root'
})
export class IncidentService {
  private apiUrl = `http://localhost:8081/api/incidents`; // Met l'URL de ton API ici

  constructor(private http: HttpClient) { }

  // Récupérer tous les incidents
  getAllIncidents(): Observable<Incident[]> {
    return this.http.get<Incident[]>(this.apiUrl);
  }

  // Récupérer un incident par son ID
  getIncidentById(id: number): Observable<Incident> {
    return this.http.get<Incident>(`${this.apiUrl}/${id}`);
  }

  // Créer un nouvel incident avec un fichier
  createIncident(incident: Incident, file: File): Observable<Incident> {
    const formData = new FormData();
    formData.append('incident', new Blob([JSON.stringify(incident)], { type: 'application/json' }));
    formData.append('file', file);

    return this.http.post<Incident>(this.apiUrl, formData);
  }

  // Mettre à jour un incident
  updateIncident(id: number, incident: Incident): Observable<Incident> {
    return this.http.put<Incident>(`${this.apiUrl}/${id}`, incident);
  }

  // Supprimer un incident
  deleteIncident(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
 
  

  getIncidents(): Observable<Incident[]> {
    return this.http.get<Incident[]>(this.apiUrl);
  }
  updateIncidentWithFile(id: number, incident: any, file?: File): Observable<any> {
    const formData = new FormData();
    formData.append('incident', new Blob([JSON.stringify(incident)], { type: 'application/json' }));
    if (file) {
      formData.append('file', file);
    }
  
    return this.http.put(`http://localhost:8081/api/incidents/${id}`, formData);
  }
  
  
}
