// src/app/services/tour-package.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface TourPackage {
  id?: number;
  title: string;
  state: string;
  description: string;
  price: number;
  duration: string;
  image: string;
}

@Injectable({
  providedIn: 'root'
})
export class TourPackageService {
  private baseUrl = 'http://localhost:8080/api/tour-packages';

  constructor(private http: HttpClient) {}

  getPackages(): Observable<TourPackage[]> {
    return this.http.get<TourPackage[]>(this.baseUrl);
  }

  getPackageById(id: number): Observable<TourPackage> {
    return this.http.get<TourPackage>(`${this.baseUrl}/${id}`);
  }

  // Add create, update, delete if your backend supports them
}
