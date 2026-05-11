import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Paiement } from '../models/paiement.model';

@Injectable({
  providedIn: 'root'
})
export class PaiementService {
  private baseUrl = '/api/paiements';

  constructor(private http: HttpClient) {}

  getAll(): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(this.baseUrl);
  }

  create(p: Paiement): Observable<Paiement> {
    return this.http.post<Paiement>(this.baseUrl, p);
  }

  getByContrat(contratId: number): Observable<Paiement[]> {
    return this.http.get<Paiement[]>(`${this.baseUrl}/contrat/${contratId}`);
  }
}
