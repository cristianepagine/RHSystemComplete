import { Routes } from '@angular/router';

export const FUNCIONARIOS_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./funcionarios-list/funcionarios-list.component')
      .then(m => m.FuncionariosListComponent)
  }
];
