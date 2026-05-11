import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, RouterLink } from '@angular/router';
import { ContratService } from '../../../core/services/contrat.service';
import { ContratAutomobile, ContratHabitation, ContratSante } from '../../../core/models/contrat.model';

@Component({
  selector: 'app-contrat-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './contrat-list.component.html',
  styleUrls: ['./contrat-list.component.css']
})
export class ContratListComponent implements OnInit {
  activeTab: 'AUTO' | 'HABITATION' | 'SANTE' = 'AUTO';
  
  contratsAuto: ContratAutomobile[] = [];
  contratsHabitation: ContratHabitation[] = [];
  contratsSante: ContratSante[] = [];
  loading = false;
  
  clientIdFilter?: number;

  private contratService = inject(ContratService);
  private route = inject(ActivatedRoute);

  ngOnInit() {
    this.route.queryParams.subscribe(params => {
      if (params['clientId']) {
        this.clientIdFilter = +params['clientId'];
      }
      this.loadAllContrats();
    });
  }

  setTab(tab: 'AUTO' | 'HABITATION' | 'SANTE') {
    this.activeTab = tab;
  }

  loadAllContrats() {
    this.loading = true;
    
    // Pour simplifier, on charge tout et on filtre côté client s'il y a un clientIdFilter
    // Dans une vraie application, on ferait l'appel avec le filtre
    
    this.contratService.getAllAuto().subscribe(data => {
      this.contratsAuto = this.clientIdFilter ? data.filter(c => c.clientId === this.clientIdFilter) : data;
    });

    this.contratService.getAllHabitation().subscribe(data => {
      this.contratsHabitation = this.clientIdFilter ? data.filter(c => c.clientId === this.clientIdFilter) : data;
    });

    this.contratService.getAllSante().subscribe(data => {
      this.contratsSante = this.clientIdFilter ? data.filter(c => c.clientId === this.clientIdFilter) : data;
      this.loading = false;
    });
  }

  validerContrat(id: number | undefined) {
    if (!id) return;
    if (confirm('Voulez-vous valider ce contrat ?')) {
      this.contratService.valider(id).subscribe(() => this.loadAllContrats());
    }
  }

  resilierContrat(id: number | undefined) {
    if (!id) return;
    if (confirm('Voulez-vous résilier ce contrat ?')) {
      this.contratService.resilier(id).subscribe(() => this.loadAllContrats());
    }
  }

  getBadgeClass(statut: string | undefined): string {
    switch(statut) {
      case 'EN_COURS': return 'badge-info';
      case 'VALIDE': return 'badge-success';
      case 'RESILIE': return 'badge-danger';
      default: return 'badge-secondary';
    }
  }
}
