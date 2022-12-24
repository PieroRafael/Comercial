import { Observable } from 'rxjs';
import { ResponseMensaje } from './responseMensaje';

export interface Seguimientospc {
  idseguimientospc: number;
  idspc: number;
  listFile: FileList;
  tipo: boolean;
  nombre: string;
  descripcion: string;
  observacion: string;
  formarecepcion: string;
  fcreate: Date;
  eliminado: boolean;
  listFolder: String[];
}

export abstract class SeguimientospcData {
  abstract list(): Observable<Seguimientospc[]>;
  abstract listByIdDocumento(idseguimientospc: number): Observable<Seguimientospc[]>;
  abstract listByModal(idseguimientospc: number, path: string): Observable<Seguimientospc[]>;
  abstract get(idseguimientospc: number): Observable<Seguimientospc>;
  abstract update(seguimientospc: Seguimientospc): Observable<ResponseMensaje>;
  abstract create(seguimientospc: Seguimientospc): Observable<ResponseMensaje>;
  abstract delete(idseguimientospc: number): Observable<ResponseMensaje>;
}
