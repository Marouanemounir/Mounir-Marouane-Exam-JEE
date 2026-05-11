import { Component, OnInit, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { ClientService } from '../../../core/services/client.service';
import { Client } from '../../../core/models/client.model';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-client-list',
  standalone: true,
  imports: [CommonModule, RouterLink],
  templateUrl: './client-list.component.html',
  styleUrls: ['./client-list.component.css']
})
export class ClientListComponent implements OnInit {
  private clientService = inject(ClientService);
  clients$!: Observable<Client[]>;

  ngOnInit() {
    this.loadClients();
  }

  loadClients() {
    this.clients$ = this.clientService.getAll();
  }

  deleteClient(id: number | undefined) {
    if (!id) return;
    
    if (confirm('Êtes-vous sûr de vouloir supprimer ce client ?')) {
      this.clientService.delete(id).subscribe({
        next: () => this.loadClients(),
        error: (err) => console.error('Erreur lors de la suppression', err)
      });
    }
  }
}
