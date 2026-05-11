import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { PaiementService } from '../../../core/services/paiement.service';
import { Paiement } from '../../../core/models/paiement.model';

@Component({
  selector: 'app-paiement-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './paiement-list.component.html',
  styleUrls: ['./paiement-list.component.css']
})
export class PaiementListComponent implements OnInit {
  paiements: Paiement[] = [];
  filteredPaiements: Paiement[] = [];
  loading = false;
  
  typeFilter: string = 'ALL';

  private paiementService = inject(PaiementService);

  ngOnInit() {
    this.loadPaiements();
  }

  loadPaiements() {
    this.loading = true;
    this.paiementService.getAll().subscribe({
      next: (data) => {
        this.paiements = data;
        this.applyFilter();
        this.loading = false;
      },
      error: () => this.loading = false
    });
  }

  onFilterChange(event: any) {
    this.typeFilter = event.target.value;
    this.applyFilter();
  }

  applyFilter() {
    if (this.typeFilter === 'ALL') {
      this.filteredPaiements = this.paiements;
    } else {
      this.filteredPaiements = this.paiements.filter(p => p.type === this.typeFilter);
    }
  }

  getBadgeClass(type: string): string {
    switch(type) {
      case 'MENSUALITE': return 'badge-info';
      case 'PAIEMENT_ANNUEL': return 'badge-success';
      case 'PAIEMENT_EXCEPTIONNEL': return 'badge-warning';
      default: return 'badge-secondary';
    }
  }
}
