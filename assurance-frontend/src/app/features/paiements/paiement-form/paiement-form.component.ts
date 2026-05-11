import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { PaiementService } from '../../../core/services/paiement.service';

@Component({
  selector: 'app-paiement-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './paiement-form.component.html',
  styleUrls: ['./paiement-form.component.css']
})
export class PaiementFormComponent {
  paiementForm: FormGroup;
  loading = false;
  error: string | null = null;

  private fb = inject(FormBuilder);
  private paiementService = inject(PaiementService);
  private router = inject(Router);

  constructor() {
    this.paiementForm = this.fb.group({
      contratId: ['', Validators.required],
      date: ['', Validators.required],
      montant: ['', [Validators.required, Validators.min(0)]],
      type: ['MENSUALITE', Validators.required]
    });
  }

  onSubmit() {
    if (this.paiementForm.invalid) return;

    this.loading = true;
    this.error = null;

    this.paiementService.create(this.paiementForm.value).subscribe({
      next: () => this.router.navigate(['/paiements']),
      error: () => {
        this.error = "Erreur lors de l'enregistrement du paiement";
        this.loading = false;
      }
    });
  }
}
