import { Observable } from 'rxjs';
import { ResponseMensaje } from './responseMensaje';

export interface Adicionalot {
  idadicionalot: number;
  areas: string;
  documento: string;
  descripcion: string;
}

export abstract class AdicionalotData {
  abstract list(): Observable<Adicionalot[]>;
  abstract get(idot: number): Observable<Adicionalot>;
  abstract update(adicionalot: Adicionalot): Observable<ResponseMensaje>;
  abstract create(adicionalot: Adicionalot): Observable<ResponseMensaje>;
  abstract delete(adicionalot: Adicionalot): Observable<ResponseMensaje>;
}
