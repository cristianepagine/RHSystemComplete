import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Ferias, StatusFerias } from '../../models/ferias.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FeriasService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/ferias`;

  getAll(): Observable<Ferias[]> {
    return this.http.get<Ferias[]>(this.apiUrl);
  }

  getById(id: number): Observable<Ferias> {
    return this.http.get<Ferias>(`${this.apiUrl}/${id}`);
  }

  getByFuncionario(funcionarioId: number): Observable<Ferias[]> {
    return this.http.get<Ferias[]>(`${this.apiUrl}/funcionario/${funcionarioId}`);
  }

  getByStatus(status: StatusFerias): Observable<Ferias[]> {
    return this.http.get<Ferias[]>(`${this.apiUrl}/status/${status}`);
  }

  create(ferias: Ferias): Observable<Ferias> {
    return this.http.post<Ferias>(this.apiUrl, ferias);
  }

  update(id: number, ferias: Ferias): Observable<Ferias> {
    return this.http.put<Ferias>(`${this.apiUrl}/${id}`, ferias);
  }

  aprovar(id: number): Observable<Ferias> {
    return this.http.put<Ferias>(`${this.apiUrl}/${id}/aprovar`, {});
  }

  rejeitar(id: number): Observable<Ferias> {
    return this.http.put<Ferias>(`${this.apiUrl}/${id}/rejeitar`, {});
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
