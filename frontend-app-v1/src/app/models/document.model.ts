export interface Document {
    id: number;
    titre: string;
    type: 'COMPTE_RENDU' | 'REGLEMENT' | 'BUDGET' | 'AUTRE';
    content: string;  // Cela peut être un base64 ou une chaîne vide
    nomFichier: string;
    cheminFichier: string;
   // gestionnaire: { id: number; nom: string };  // Gestionnaire est un objet avec id et nom
  }
  