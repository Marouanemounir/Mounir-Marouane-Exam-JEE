import { Routes } from '@angular/router';
import { LoginComponent } from './features/auth/login/login.component';
import { RegisterComponent } from './features/auth/register/register.component';
import { DashboardComponent } from './features/dashboard/dashboard.component';
import { ClientListComponent } from './features/clients/client-list/client-list.component';
import { ClientFormComponent } from './features/clients/client-form/client-form.component';
import { ContratListComponent } from './features/contrats/contrat-list/contrat-list.component';
import { ContratFormComponent } from './features/contrats/contrat-form/contrat-form.component';
import { PaiementListComponent } from './features/paiements/paiement-list/paiement-list.component';
import { PaiementFormComponent } from './features/paiements/paiement-form/paiement-form.component';
import { authGuard } from './core/guards/auth.guard';
import { roleGuard } from './core/guards/role.guard';

export const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authGuard]
  },
  {
    path: 'clients',
    canActivate: [authGuard],
    children: [
      { path: '', component: ClientListComponent, canActivate: [roleGuard], data: { roles: ['ROLE_ADMIN', 'ROLE_EMPLOYE'] } },
      { path: 'new', component: ClientFormComponent, canActivate: [roleGuard], data: { roles: ['ROLE_ADMIN', 'ROLE_EMPLOYE'] } },
      { path: 'edit/:id', component: ClientFormComponent, canActivate: [roleGuard], data: { roles: ['ROLE_ADMIN', 'ROLE_EMPLOYE'] } }
    ]
  },
  {
    path: 'contrats',
    canActivate: [authGuard],
    children: [
      { path: '', component: ContratListComponent },
      { path: 'new', component: ContratFormComponent }
    ]
  },
  {
    path: 'paiements',
    canActivate: [authGuard],
    children: [
      { path: '', component: PaiementListComponent },
      { path: 'new', component: PaiementFormComponent }
    ]
  },
  { path: '**', redirectTo: 'login' }
];
