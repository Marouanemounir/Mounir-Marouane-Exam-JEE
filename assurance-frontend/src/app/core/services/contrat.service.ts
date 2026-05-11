import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { ContratAutomobile, ContratHabitation, ContratSante } from '../models/contrat.model';

@Injectable({
  providedIn: 'root'
})
export class ContratService {
  private baseUrl = '/api/contrats';

  constructor(private http: HttpClient) {}

  getAllAuto(): Observable<ContratAutomobile[]> {
    return this.http.get<ContratAutomobile[]>(`${this.baseUrl}/auto`);
  }

  createAuto(c: ContratAutomobile): Observable<ContratAutomobile> {
    return this.http.post<ContratAutomobile>(`${this.baseUrl}/auto`, c);
  }

  getAllHabitation(): Observable<ContratHabitation[]> {
    return this.http.get<ContratHabitation[]>(`${this.baseUrl}/habitation`);
  }

  createHabitation(c: ContratHabitation): Observable<ContratHabitation> {
    return this.http.post<ContratHabitation>(`${this.baseUrl}/habitation`, c);
  }

  getAllSante(): Observable<ContratSante[]> {
    return this.http.get<ContratSante[]>(`${this.baseUrl}/sante`);
  }

  createSante(c: ContratSante): Observable<ContratSante> {
    return this.http.post<ContratSante>(`${this.baseUrl}/sante`, c);
  }

  valider(id: number): Observable<any> {
    return this.http.put(`${this.baseUrl}/${id}/valider`, {});
  }

  resilier(id: number): Observable<void> {
    return this.http.put<void>(`${this.baseUrl}/${id}/resilier`, {});
  }
}
