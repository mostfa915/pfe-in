import { Injectable } from '@angular/core';
import { HttpClient ,HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';


export interface Coproprietaire {
  id: number;
  num: string;
  email: string;
  mdpHash: string;
  role: string;
  soldeCharges: number;
}

@Injectable({
  providedIn: 'root',
})
export class CoproprietaireService {
  private apiUrl = 'http://localhost:8081/api/Coproprietaires';

  constructor(private http: HttpClient , 
      private authService: AuthService) {}

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

  getAll(): Observable<Coproprietaire[]> {
    return this.http.get<Coproprietaire[]>(this.apiUrl);
  }

  getById(id: number): Observable<Coproprietaire> {
    return this.http.get<Coproprietaire>(`${this.apiUrl}/${id}`);
  }

  create(copro: Coproprietaire): Observable<Coproprietaire> {
    return this.http.post<Coproprietaire>('http://localhost:8081/api/auth/register', copro , {
        headers: new HttpHeaders({ 'Content-Type': 'application/json' })
      });
  }

  update(id: number, copro: Coproprietaire): Observable<Coproprietaire> {
    return this.http.put<Coproprietaire>(`${this.apiUrl}/${id}`, copro);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }


  
}
