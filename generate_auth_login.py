import os

base_dir = "/home/marouane-mounir/projects/assurance-app/assurance-frontend/src/app"

files = {
    "features/auth/login/login.component.ts": """import { Component, inject } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../../../core/services/auth.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent {
  loginForm: FormGroup;
  error: string | null = null;
  loading = false;

  private authService = inject(AuthService);
  private router = inject(Router);
  private fb = inject(FormBuilder);

  constructor() {
    this.loginForm = this.fb.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  onSubmit() {
    if (this.loginForm.invalid) return;

    this.loading = true;
    this.error = null;

    this.authService.login(this.loginForm.value).subscribe({
      next: () => {
        this.router.navigate(['/dashboard']);
      },
      error: () => {
        this.error = 'Identifiants incorrects';
        this.loading = false;
      }
    });
  }
}
""",
    "features/auth/login/login.component.html": """<div class="auth-container">
  <div class="auth-card">
    <h2>Connexion</h2>
    
    <div *ngIf="error" class="alert alert-danger">
      {{ error }}
    </div>

    <form [formGroup]="loginForm" (ngSubmit)="onSubmit()">
      <div class="form-group">
        <label for="username">Nom d'utilisateur</label>
        <input type="text" id="username" formControlName="username" class="form-control" />
        <div *ngIf="loginForm.get('username')?.invalid && loginForm.get('username')?.touched" class="text-danger">
          Ce champ est requis.
        </div>
      </div>

      <div class="form-group">
        <label for="password">Mot de passe</label>
        <input type="password" id="password" formControlName="password" class="form-control" />
        <div *ngIf="loginForm.get('password')?.invalid && loginForm.get('password')?.touched" class="text-danger">
          Ce champ est requis.
        </div>
      </div>

      <button type="submit" class="btn btn-primary w-100" [disabled]="loginForm.invalid || loading">
        <span *ngIf="loading" class="spinner"></span>
        <span *ngIf="!loading">Se connecter</span>
      </button>

      <div class="mt-3 text-center">
        <a routerLink="/register">Pas encore de compte ? S'inscrire</a>
      </div>
    </form>
  </div>
</div>
""",
    "features/auth/login/login.component.css": """.auth-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: 100vh;
  background-color: #f5f6fa;
}

.auth-card {
  background: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  width: 100%;
  max-width: 400px;
}

h2 {
  color: #1a237e;
  text-align: center;
  margin-bottom: 1.5rem;
}

.form-group {
  margin-bottom: 1rem;
}

label {
  display: block;
  margin-bottom: 0.5rem;
  color: #333;
}

.form-control {
  width: 100%;
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.text-danger {
  color: #dc3545;
  font-size: 0.875rem;
  margin-top: 0.25rem;
}

.alert-danger {
  background-color: #f8d7da;
  color: #721c24;
  padding: 0.75rem;
  border-radius: 4px;
  margin-bottom: 1rem;
}

.btn-primary {
  background-color: #1a237e;
  color: white;
  border: none;
  padding: 0.75rem;
  border-radius: 4px;
  cursor: pointer;
  font-weight: bold;
}

.btn-primary:disabled {
  opacity: 0.7;
  cursor: not-allowed;
}

.w-100 {
  width: 100%;
}

.mt-3 {
  margin-top: 1rem;
}

.text-center {
  text-align: center;
}

.spinner {
  display: inline-block;
  width: 1rem;
  height: 1rem;
  border: 2px solid white;
  border-top-color: transparent;
  border-radius: 50%;
  animation: spin 1s linear infinite;
}

@keyframes spin {
  to {
    transform: rotate(360deg);
  }
}
"""
}

for path, content in files.items():
    full_path = os.path.join(base_dir, path)
    os.makedirs(os.path.dirname(full_path), exist_ok=True)
    with open(full_path, "w") as f:
        f.write(content)

print("Auth Login files generated.")
