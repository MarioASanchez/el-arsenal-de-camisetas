import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from '../Models/login-request';
import { Observable, tap } from 'rxjs';
import { AuthResponse } from '../Models/auth-response';
import { RegisterRequest } from '../Models/register-request';
import { UserDTO } from '../Models/user-dto';
import { environment } from '../Environments/environment';

@Injectable({
  providedIn: 'root',
})

export class AuthService {
  private readonly apiUrl = `${environment.apiUrl}/auth`;
  private readonly TOKEN_KEY = 'auth_token';
  private readonly USER_KEY = 'auth_user';

  constructor(private http: HttpClient) {}

  // login
  login(credentials: LoginRequest): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.apiUrl}/login`, credentials)
      .pipe(
        tap(response => this.saveSession(response))
      );
  }

  // register
  register(data: RegisterRequest): Observable<AuthResponse>{
    return this.http.post<AuthResponse>(`${this.apiUrl}/register`, data)
      .pipe(
        tap(response => this.saveSession(response))
      );
  }

  private saveSession(response: AuthResponse): void{
    localStorage.setItem(this.TOKEN_KEY, response.token);
    localStorage.setItem(this.USER_KEY, JSON.stringify(response.user));
  }

  logout(): void{
    localStorage.removeItem(this.TOKEN_KEY);
    localStorage.removeItem(this.USER_KEY);
  }

  getToken(): string | null{
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getCurrentUser(): UserDTO | null{
    const userJson = localStorage.getItem(this.USER_KEY);
    return userJson ? JSON.parse(userJson) : null;
  }

  isLoggedIn(): boolean {
    const token = this.getToken();

    if(!token) return false;

    try{
      const payload = JSON.parse(atob(token.split('.')[1]));
      const now = Math.floor(Date.now() / 1000);
      return payload.exp > now;
    } catch {
      return false;
    }
  }

  isAdmin(): boolean {
    const user = this.getCurrentUser();
    return user?.role === 'ADMIN';
  }

  refreshUser(): Observable<UserDTO> {
    return this.http.get<UserDTO>(`${environment.apiUrl}/users/me`)
      .pipe(
        tap(user => {
          localStorage.setItem(this.USER_KEY, JSON.stringify(user));
        })
      );
  }
}
