import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode'; // ✅ Utiliser l'export nommé
import { environment } from 'src/environments/environment';



export interface User {
  id: number;
  username: string;
  email: string;
  // Add other user properties as needed

}
export interface JwtPayload {
  sub: string;
  id?: number; // ou userId selon ta structure
  email?: string;
  roles?: string[];
  exp?: number;
  [key: string]: any; // pour accepter d'autres champs
}
@Injectable({
  providedIn: 'root'
})
export class AuthService {
    private authUrl = environment.apiBaseUrl;

  private tokenSubject = new BehaviorSubject<string | null>(null);

  constructor(private http: HttpClient, private router: Router) {
    // Initialisation avec le token du localStorage s'il existe
    const savedToken = localStorage.getItem('jwt_token');
    if (savedToken) {
      this.tokenSubject.next(savedToken);
    }
      const user = localStorage.getItem('currentUser');
    if (user) {
      this.currentUserSubject.next(JSON.parse(user));
    }
  }
isLoggedIn(): boolean {
  const token = localStorage.getItem('jwt_token'); // Ou sessionStorage
  return !!token; // Retourne true si le token existe
}
  login(email: string, password: string): Observable<any> {
   console.log( email +password)
    return this.http.post(`${this.authUrl}/auth/login`, { email, password }).pipe(
      tap((response: any) => {
        this.storeToken(response.token);
      })
    );
  }

  register(email: string, password: string, role: string): Observable<any> {
    return this.http.post(`${this.authUrl}/auth/register`, { email, password, role });
  }

  storeToken(token: string): void {
    localStorage.setItem('jwt_token', token);
    this.tokenSubject.next(token);
  }

 
  isAuthenticated(): boolean {
    return !!this.getToken();
  }

  logout(): void {
    localStorage.removeItem('jwt_token');
    this.tokenSubject.next(null);
    this.router.navigate(['/login']);
  }

   private currentUserSubject = new BehaviorSubject<User | null>(null);
  public currentUser$ = this.currentUserSubject.asObservable();

  

  // Method to get current user synchronously
  

  // Method to get current user as observable
  getCurrentUserObservable(): Observable<User | null> {
    return this.currentUser$;
  }
 getToken(): string | null {
    return localStorage.getItem('jwt_token');
  }

  getDecodedToken(): JwtPayload | null {
    const token = this.getToken();
    if (!token) return null;

    try {
return jwtDecode<JwtPayload>(token); // ✅ utiliser jwtDecode et non jwt_decode
    } catch (e) {
      console.error('Erreur de décodage du token', e);
      return null;
    }
  }

  getCurrentUser(): JwtPayload | null {
    return this.getDecodedToken();
  }
  
}