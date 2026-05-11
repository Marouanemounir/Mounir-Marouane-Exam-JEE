export type TypePaiement = 'MENSUALITE' | 'PAIEMENT_ANNUEL' | 'PAIEMENT_EXCEPTIONNEL';

export interface Paiement {
  id?: number;
  date: string;
  montant: number;
  type: TypePaiement;
  contratId: number;
}
