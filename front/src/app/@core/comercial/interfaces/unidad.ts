import { ResponseMensaje } from './../../sistema/interfaces/responseMensaje';
import { Observable } from 'rxjs';

export interface Unidad {
  idunidad: number;
  idcliente: number;
  nombre: string;
  descripcion: number;
  fcreate: Date;
  eliminado: boolean;
}

export abstract class UnidadData {
  abstract list(): Observable<Unidad[]>;
  abstract listByIdCliente(idcliente: number): Observable<Unidad[]>;
  abstract get(idunidad: number): Observable<Unidad>;
  abstract update(unidad: Unidad): Observable<ResponseMensaje>;
  abstract create(unidad: Unidad): Observable<ResponseMensaje>;
  abstract delete(idunidad: number): Observable<ResponseMensaje>;
}
