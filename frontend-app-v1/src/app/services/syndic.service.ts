import { Injectable } from '@angular/core';
import { AuthService } from './auth.service';

import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

import { Observable } from 'rxjs';
import { Syndic } from '../models/syndic.model';
@Injectable({
  providedIn: 'root'
})
export class SyndicService {
  private apiUrl = 'http://localhost:8081/api/Syndic';

  constructor(
    private http: HttpClient,
    private authService: AuthService
  ) {}

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

  getAllSyndics(): Observable<Syndic[]> {
    return this.http.get<Syndic[]>(this.apiUrl, this.getHttpOptions());
  }

  getSyndicById(id: number): Observable<Syndic> {
    return this.http.get<Syndic>(`${this.apiUrl}/${id}`, this.getHttpOptions());
  }

  createSyndic(syndic: Syndic): Observable<Syndic> {
    return this.http.post<Syndic>(
      'http://localhost:8081/api/auth/register',
      syndic, {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      }
    );
  }

  updateSyndic(id: number, syndic: Syndic): Observable<Syndic> {
    return this.http.put<Syndic>(`${this.apiUrl}/${id}`, syndic, this.getHttpOptions());
  }

  deleteSyndic(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`, this.getHttpOptions());
  }
}

