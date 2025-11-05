import { Routes } from '@angular/router';
import { authGuard } from './core/guards/auth.guard';

export const routes: Routes = [
  {
    path: 'login',
    loadComponent: () => import('./modules/auth/login/login.component').then(m => m.LoginComponent)
  },
  {
    path: '',
    canActivate: [authGuard],
    children: [
      {
        path: 'dashboard',
        loadComponent: () => import('./modules/dashboard/dashboard.component').then(m => m.DashboardComponent)
      },
      {
        path: 'funcionarios',
        loadChildren: () => import('./modules/funcionarios/funcionarios.routes').then(m => m.FUNCIONARIOS_ROUTES)
      },
      {
        path: 'ferias',
        loadChildren: () => import('./modules/ferias/ferias.routes').then(m => m.FERIAS_ROUTES)
      },
      {
        path: 'ponto',
        loadChildren: () => import('./modules/ponto/ponto.routes').then(m => m.PONTO_ROUTES)
      },
      {
        path: '',
        redirectTo: 'dashboard',
        pathMatch: 'full'
      }
    ]
  },
  {
    path: '**',
    redirectTo: 'login'
  }
];
