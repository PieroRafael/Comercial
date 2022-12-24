import { Observable } from 'rxjs';
import { ResponseMensaje } from './responseMensaje';

export interface Contacto {
  idcontacto: number;
  idcliente: number;
  nombre: string;
  razonsocial: string;
  correo: string;
  telefono: string;
  celular: string;
  cargo: string;
  unidad: string;
  eliminado: boolean;
}

export abstract class ContactoData {
  abstract list(pageNumber: number, pageSize: number): Observable<Contacto[]>;
  abstract listByIdCliente(idcliente: number): Observable<Contacto[]>;
  abstract get(idcliente: number): Observable<Contacto>;
  abstract update(contacto: Contacto): Observable<ResponseMensaje>;
  abstract create(contacto: Contacto): Observable<ResponseMensaje>;
  abstract delete(contacto: number): Observable<ResponseMensaje>;
}
