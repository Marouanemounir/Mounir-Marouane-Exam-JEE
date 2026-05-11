import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { AuthService } from '../../core/services/auth.service';
import { ClientService } from '../../core/services/client.service';
import { ContratService } from '../../core/services/contrat.service';
import { PaiementService } from '../../core/services/paiement.service';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
  authService = inject(AuthService);
  clientService = inject(ClientService);
  contratService = inject(ContratService);
  paiementService = inject(PaiementService);

  totalClients = 0;
  totalContrats = 0;
  totalPaiements = 0;
  loading = true;

  get username(): string {
    return this.authService.getUsername() || '';
  }

  ngOnInit() {
    this.loadStats();
  }

  loadStats() {
    forkJoin({
      clients: this.clientService.getAll(),
      auto: this.contratService.getAllAuto(),
      habitation: this.contratService.getAllHabitation(),
      sante: this.contratService.getAllSante(),
      paiements: this.paiementService.getAll()
    }).subscribe({
      next: (results) => {
        this.totalClients = results.clients.length;
        this.totalContrats = results.auto.length + results.habitation.length + results.sante.length;
        this.totalPaiements = results.paiements.length;
        this.loading = false;
      },
      error: (err) => {
        console.error('Erreur lors du chargement des statistiques', err);
        this.loading = false;
      }
    });
  }
}
