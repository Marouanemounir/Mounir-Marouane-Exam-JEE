import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { ContratService } from '../../../core/services/contrat.service';

@Component({
  selector: 'app-contrat-form',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './contrat-form.component.html',
  styleUrls: ['./contrat-form.component.css']
})
export class ContratFormComponent {
  contratForm: FormGroup;
  typeContrat: 'AUTO' | 'HABITATION' | 'SANTE' = 'AUTO';
  loading = false;
  error: string | null = null;

  private fb = inject(FormBuilder);
  private contratService = inject(ContratService);
  private router = inject(Router);

  constructor() {
    this.contratForm = this.createBaseForm();
    this.updateFormFields();
  }

  createBaseForm(): FormGroup {
    return this.fb.group({
      clientId: ['', Validators.required],
      dateSouscription: ['', Validators.required],
      montantCotisation: ['', [Validators.required, Validators.min(0)]],
      dureeContrat: ['', [Validators.required, Validators.min(1)]],
      tauxCouverture: ['', [Validators.required, Validators.min(0), Validators.max(100)]]
    });
  }

  onTypeChange(event: any) {
    this.typeContrat = event.target.value;
    this.updateFormFields();
  }

  updateFormFields() {
    // Supprimer les contrôles spécifiques précédents
    ['numeroImmatriculation', 'marque', 'modele', 'typeLogement', 'adresse', 'superficie', 'niveauCouverture', 'nombrePersonnesCouvertes'].forEach(control => {
      this.contratForm.removeControl(control);
    });

    // Ajouter les nouveaux contrôles selon le type
    if (this.typeContrat === 'AUTO') {
      this.contratForm.addControl('numeroImmatriculation', this.fb.control('', Validators.required));
      this.contratForm.addControl('marque', this.fb.control('', Validators.required));
      this.contratForm.addControl('modele', this.fb.control('', Validators.required));
    } else if (this.typeContrat === 'HABITATION') {
      this.contratForm.addControl('typeLogement', this.fb.control('APPARTEMENT', Validators.required));
      this.contratForm.addControl('adresse', this.fb.control('', Validators.required));
      this.contratForm.addControl('superficie', this.fb.control('', [Validators.required, Validators.min(1)]));
    } else if (this.typeContrat === 'SANTE') {
      this.contratForm.addControl('niveauCouverture', this.fb.control('BASIQUE', Validators.required));
      this.contratForm.addControl('nombrePersonnesCouvertes', this.fb.control(1, [Validators.required, Validators.min(1)]));
    }
  }

  onSubmit() {
    if (this.contratForm.invalid) return;

    this.loading = true;
    this.error = null;
    const formValue = this.contratForm.value;

    let request$: import('rxjs').Observable<any>;
    if (this.typeContrat === 'AUTO') {
      request$ = this.contratService.createAuto(formValue);
    } else if (this.typeContrat === 'HABITATION') {
      request$ = this.contratService.createHabitation(formValue);
    } else {
      request$ = this.contratService.createSante(formValue);
    }

    request$.subscribe({
      next: () => this.router.navigate(['/contrats']),
      error: () => {
        this.error = 'Erreur lors de la création du contrat';
        this.loading = false;
      }
    });
  }
}
