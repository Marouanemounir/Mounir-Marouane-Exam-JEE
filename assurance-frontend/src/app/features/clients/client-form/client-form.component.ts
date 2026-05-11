import { Component, OnInit, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ClientService } from '../../../core/services/client.service';

@Component({
  selector: 'app-client-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './client-form.component.html',
  styleUrls: ['./client-form.component.css']
})
export class ClientFormComponent implements OnInit {
  clientForm: FormGroup;
  isEditMode = false;
  clientId?: number;
  loading = false;
  error: string | null = null;

  private fb = inject(FormBuilder);
  private clientService = inject(ClientService);
  private router = inject(Router);
  private route = inject(ActivatedRoute);

  constructor() {
    this.clientForm = this.fb.group({
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]]
    });
  }

  ngOnInit() {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.isEditMode = true;
      this.clientId = +id;
      this.loadClient();
    }
  }

  loadClient() {
    if (!this.clientId) return;
    this.loading = true;
    this.clientService.getById(this.clientId).subscribe({
      next: (client) => {
        this.clientForm.patchValue(client);
        this.loading = false;
      },
      error: () => {
        this.error = 'Erreur lors du chargement du client';
        this.loading = false;
      }
    });
  }

  onSubmit() {
    if (this.clientForm.invalid) return;

    this.loading = true;
    const clientData = this.clientForm.value;

    const request$ = this.isEditMode
      ? this.clientService.update(this.clientId!, clientData)
      : this.clientService.create(clientData);

    request$.subscribe({
      next: () => {
        this.router.navigate(['/clients']);
      },
      error: () => {
        this.error = "Une erreur est survenue lors de l'enregistrement.";
        this.loading = false;
      }
    });
  }
}
