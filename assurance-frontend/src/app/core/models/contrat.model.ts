export type StatutContrat = 'EN_COURS' | 'VALIDE' | 'RESILIE';
export type TypeLogement = 'APPARTEMENT' | 'MAISON' | 'LOCAL_COMMERCIAL';
export type NiveauCouverture = 'BASIQUE' | 'INTERMEDIAIRE' | 'PREMIUM';

export interface ContratBase {
  id?: number;
  dateSouscription: string;
  statut?: StatutContrat;
  dateValidation?: string;
  montantCotisation: number;
  dureeContrat: number;
  tauxCouverture: number;
  clientId: number;
}

export interface ContratAutomobile extends ContratBase {
  numeroImmatriculation: string;
  marque: string;
  modele: string;
}

export interface ContratHabitation extends ContratBase {
  typeLogement: TypeLogement;
  adresse: string;
  superficie: number;
}

export interface ContratSante extends ContratBase {
  niveauCouverture: NiveauCouverture;
  nombrePersonnesCouvertes: number;
}
