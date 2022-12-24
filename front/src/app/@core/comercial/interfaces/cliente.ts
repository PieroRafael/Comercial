import { ClienteSelect } from './clienteSelect';
import { Observable } from 'rxjs';
import { ResponseMensaje } from './responseMensaje';

export interface Cliente {
  idcliente: number;
  ruc: number;
  razonsocial: string;
  telefono: string;
  direccion: string;
  paginaweb: string;
  eliminado: boolean;
}

export abstract class ClienteData {
  abstract list(): Observable<Cliente[]>;
  abstract get(idcliente: number): Observable<Cliente>;
  abstract listSelect(): Observable<ClienteSelect[]>;
  abstract update(cliente: Cliente): Observable<ResponseMensaje>;
  abstract create(cliente: Cliente): Observable<ResponseMensaje>;
  abstract delete(cliente: number): Observable<ResponseMensaje>;
}
