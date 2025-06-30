export interface Prestataire {
  id?: number;
  num: string;
  email: string;
  mdpHash: string;
  role: 'PRESTATAIRE';
  specialite: string;
}
