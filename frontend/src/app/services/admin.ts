// src/app/services/admin.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface Admin {
  id?: number;
  username: string;
  password: string;
}

@Injectable({
  providedIn: 'root'
})
export class AdminService {
  private baseUrl = 'http://localhost:8080/api/admins';  // Adjust backend URL

  constructor(private http: HttpClient) {}

  login(admin: { username: string; password: string }): Observable<Admin> {
    return this.http.post<Admin>(`${this.baseUrl}/login`, admin);
  }

  getAdmins(): Observable<Admin[]> {
    return this.http.get<Admin[]>(this.baseUrl);
  }

  // Add more admin methods if needed
}

