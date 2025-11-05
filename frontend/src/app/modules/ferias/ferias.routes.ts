import { Routes } from '@angular/router';

export const FERIAS_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./ferias-list/ferias-list.component')
      .then(m => m.FeriasListComponent)
  }
];
