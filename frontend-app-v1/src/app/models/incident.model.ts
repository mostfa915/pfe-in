export interface Utilisateur {
    id: number;
    nom: string;
  }
  
 
  export interface Incident {
  id: number;
  description: string;
  priorite: 'BASSE' | 'MOYENNE' | 'HAUTE';
  statut: 'NOUVEAU' | 'EN_COURS' | 'RESOLU';
  survChat: string;
  declarant: {
    id: number;
    nom: string;
    email: string;
  };
}