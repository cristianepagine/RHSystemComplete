import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatTableModule } from '@angular/material/table';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { FuncionarioService } from '../../../core/services/funcionario.service';
import { Funcionario } from '../../../models/funcionario.model';

@Component({
  selector: 'app-funcionarios-list',
  standalone: true,
  imports: [
    CommonModule,
    MatTableModule,
    MatButtonModule,
    MatIconModule,
    MatToolbarModule
  ],
  template: `
    <mat-toolbar color="primary">
      <button mat-icon-button (click)="goBack()">
        <mat-icon>arrow_back</mat-icon>
      </button>
      <span>Funcionários</span>
      <span class="spacer"></span>
      <button mat-raised-button (click)="add()">
        <mat-icon>add</mat-icon>
        Novo Funcionário
      </button>
    </mat-toolbar>

    <div class="container">
      <table mat-table [dataSource]="funcionarios" class="mat-elevation-z8">
        <ng-container matColumnDef="nome">
          <th mat-header-cell *matHeaderCellDef>Nome</th>
          <td mat-cell *matCellDef="let func">{{ func.nome }}</td>
        </ng-container>

        <ng-container matColumnDef="cpf">
          <th mat-header-cell *matHeaderCellDef>CPF</th>
          <td mat-cell *matCellDef="let func">{{ func.cpf }}</td>
        </ng-container>

        <ng-container matColumnDef="cargo">
          <th mat-header-cell *matHeaderCellDef>Cargo</th>
          <td mat-cell *matCellDef="let func">{{ func.cargoNome }}</td>
        </ng-container>

        <ng-container matColumnDef="departamento">
          <th mat-header-cell *matHeaderCellDef>Departamento</th>
          <td mat-cell *matCellDef="let func">{{ func.departamentoNome }}</td>
        </ng-container>

        <ng-container matColumnDef="status">
          <th mat-header-cell *matHeaderCellDef>Status</th>
          <td mat-cell *matCellDef="let func">
            {{ func.status ? 'Ativo' : 'Inativo' }}
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
export class FuncionariosListComponent implements OnInit {
  private funcionarioService = inject(FuncionarioService);
  private router = inject(Router);

  funcionarios: Funcionario[] = [];
  displayedColumns = ['nome', 'cpf', 'cargo', 'departamento', 'status'];

  ngOnInit(): void {
    this.loadFuncionarios();
  }

  loadFuncionarios(): void {
    this.funcionarioService.getAll().subscribe({
      next: (data) => {
        this.funcionarios = data;
      }
    });
  }

  add(): void {
    // Navegar para formulário
  }

  goBack(): void {
    this.router.navigate(['/dashboard']);
  }
}
