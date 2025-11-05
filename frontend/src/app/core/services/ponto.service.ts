import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { RegistroPonto } from '../../models/registro-ponto.model';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class PontoService {
  private http = inject(HttpClient);
  private apiUrl = `${environment.apiUrl}/registros-ponto`;

  getAll(): Observable<RegistroPonto[]> {
    return this.http.get<RegistroPonto[]>(this.apiUrl);
  }

  getById(id: number): Observable<RegistroPonto> {
    return this.http.get<RegistroPonto>(`${this.apiUrl}/${id}`);
  }

  getByFuncionario(funcionarioId: number): Observable<RegistroPonto[]> {
    return this.http.get<RegistroPonto[]>(`${this.apiUrl}/funcionario/${funcionarioId}`);
  }

  getByPeriodo(funcionarioId: number, inicio: string, fim: string): Observable<RegistroPonto[]> {
    return this.http.get<RegistroPonto[]>(
      `${this.apiUrl}/funcionario/${funcionarioId}/periodo?inicio=${inicio}&fim=${fim}`
    );
  }

  registrar(registro: RegistroPonto): Observable<RegistroPonto> {
    return this.http.post<RegistroPonto>(this.apiUrl, registro);
  }

  delete(id: number): Observable<void> {
    return this.http.delete<void>(`${this.apiUrl}/${id}`);
  }
}
