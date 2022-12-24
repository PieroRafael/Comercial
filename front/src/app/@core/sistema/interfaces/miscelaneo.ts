import { Observable } from 'rxjs';
import { Area } from './area';

export interface Miscelaneo {
  id: number;
  nombre: string;
}

export abstract class MiscelaneoData {
  abstract listSelectArea(): Observable<Area[]>;
}
