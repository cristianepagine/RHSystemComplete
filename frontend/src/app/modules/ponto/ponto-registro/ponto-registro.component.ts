import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatTableModule } from '@angular/material/table';
import { Router } from '@angular/router';
import { PontoService } from '../../../core/services/ponto.service';
import { AuthService } from '../../../core/services/auth.service';
import { RegistroPonto, TipoPonto } from '../../../models/registro-ponto.model';

@Component({
  selector: 'app-ponto-registro',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatTableModule
  ],
  template: `
    <mat-toolbar color="primary">
      <button mat-icon-button (click)="goBack()">
        <mat-icon>arrow_back</mat-icon>
      </button>
      <span>Registro de Ponto</span>
    </mat-toolbar>

    <div class="container">
      <mat-card class="registro-card">
        <mat-card-header>
          <mat-card-title>Registrar Ponto</mat-card-title>
        </mat-card-header>
        <mat-card-content>
          <div class="clock">{{ currentTime | date:'HH:mm:ss' }}</div>
          <div class="actions">
            <button mat-raised-button color="primary" (click)="registrar('ENTRADA')">
              <mat-icon>login</mat-icon>
              Entrada
            </button>
            <button mat-raised-button color="accent" (click)="registrar('SAIDA')">
              <mat-icon>logout</mat-icon>
              Saída
            </button>
          </div>
        </mat-card-content>
      </mat-card>

      <h2>Registros Recentes</h2>
      <table mat-table [dataSource]="registros" class="mat-elevation-z8">
        <ng-container matColumnDef="dataHora">
          <th mat-header-cell *matHeaderCellDef>Data/Hora</th>
          <td mat-cell *matCellDef="let reg">{{ reg.dataHora | date:'dd/MM/yyyy HH:mm' }}</td>
        </ng-container>

        <ng-container matColumnDef="tipo">
          <th mat-header-cell *matHeaderCellDef>Tipo</th>
          <td mat-cell *matCellDef="let reg">{{ reg.tipo }}</td>
        </ng-container>

        <tr mat-header-row *matHeaderRowDef="displayedColumns"></tr>
        <tr mat-row *matRowDef="let row; columns: displayedColumns;"></tr>
      </table>
    </div>
  `,
  styles: [`
    .container {
      padding: 20px;
    }
    .registro-card {
      max-width: 600px;
      margin: 0 auto 40px;
      text-align: center;
    }
    .clock {
      font-size: 48px;
      font-weight: bold;
      margin: 30px 0;
      color: #3f51b5;
    }
    .actions {
      display: flex;
      gap: 20px;
      justify-content: center;
    }
    h2 {
      margin: 30px 0 20px;
    }
    table {
      width: 100%;
    }
  `]
})
export class PontoRegistroComponent implements OnInit {
  private pontoService = inject(PontoService);
  private authService = inject(AuthService);
  private router = inject(Router);

  currentTime = new Date();
  registros: RegistroPonto[] = [];
  displayedColumns = ['dataHora', 'tipo'];

  ngOnInit(): void {
    setInterval(() => {
      this.currentTime = new Date();
    }, 1000);

    this.loadRegistros();
  }

  loadRegistros(): void {
    const userId = this.authService.currentUser()?.id;
    if (userId) {
      this.pontoService.getByFuncionario(userId).subscribe({
        next: (data) => {
          this.registros = data.slice(0, 10);
        }
      });
    }
  }

  registrar(tipo: 'ENTRADA' | 'SAIDA'): void {
    const userId = this.authService.currentUser()?.id;
    if (userId) {
      const registro: RegistroPonto = {
        funcionarioId: userId,
        dataHora: new Date().toISOString(),
        tipo: TipoPonto[tipo]
      };

      this.pontoService.registrar(registro).subscribe({
        next: () => {
          this.loadRegistros();
        }
      });
    }
  }

  goBack(): void {
    this.router.navigate(['/dashboard']);
  }
}
