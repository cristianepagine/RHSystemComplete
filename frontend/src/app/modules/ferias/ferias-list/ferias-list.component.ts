import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatChipsModule } from '@angular/material/chips';
import { Router } from '@angular/router';
import { FeriasService } from '../../../core/services/ferias.service';
import { Ferias } from '../../../models/ferias.model';

@Component({
  selector: 'app-ferias-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule,
    MatChipsModule
  ],
  template: `
    <mat-toolbar color="primary">
      <button mat-icon-button (click)="goBack()">
        <mat-icon>arrow_back</mat-icon>
      </button>
      <span>Férias</span>
      <span class="spacer"></span>
      <button mat-raised-button (click)="add()">
        <mat-icon>add</mat-icon>
        Solicitar Férias
      </button>
    </mat-toolbar>

    <div class="container">
      <table mat-table [dataSource]="ferias" class="mat-elevation-z8">
        <ng-container matColumnDef="funcionario">
          <th mat-header-cell *matHeaderCellDef>Funcionário</th>
          <td mat-cell *matCellDef="let item">{{ item.funcionarioNome }}</td>
        </ng-container>

        <ng-container matColumnDef="dataInicio">
          <th mat-header-cell *matHeaderCellDef>Início</th>
          <td mat-cell *matCellDef="let item">{{ item.dataInicio | date:'dd/MM/yyyy' }}</td>
        </ng-container>

        <ng-container matColumnDef="dataFim">
          <th mat-header-cell *matHeaderCellDef>Fim</th>
          <td mat-cell *matCellDef="let item">{{ item.dataFim | date:'dd/MM/yyyy' }}</td>
        </ng-container>

        <ng-container matColumnDef="dias">
          <th mat-header-cell *matHeaderCellDef>Dias</th>
          <td mat-cell *matCellDef="let item">{{ item.diasSolicitados }}</td>
        </ng-container>

        <ng-container matColumnDef="status">
          <th mat-header-cell *matHeaderCellDef>Status</th>
          <td mat-cell *matCellDef="let item">
            <mat-chip [color]="getStatusColor(item.status)">
              {{ item.status }}
            </mat-chip>
          </td>
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
    table {
      width: 100%;
    }
  `]
})
export class FeriasListComponent implements OnInit {
  private feriasService = inject(FeriasService);
  private router = inject(Router);

  ferias: Ferias[] = [];
  displayedColumns = ['funcionario', 'dataInicio', 'dataFim', 'dias', 'status'];

  ngOnInit(): void {
    this.loadFerias();
  }

  loadFerias(): void {
    this.feriasService.getAll().subscribe({
      next: (data) => {
        this.ferias = data;
      }
    });
  }

  getStatusColor(status: string): string {
    switch (status) {
      case 'APROVADA': return 'primary';
      case 'REJEITADA': return 'warn';
      default: return 'accent';
    }
  }

  add(): void {
    // Navegar para formulário
  }

  goBack(): void {
    this.router.navigate(['/dashboard']);
  }
}
