import { Routes } from '@angular/router';

export const PONTO_ROUTES: Routes = [
  {
    path: '',
    loadComponent: () => import('./ponto-registro/ponto-registro.component')
      .then(m => m.PontoRegistroComponent)
  }
];
