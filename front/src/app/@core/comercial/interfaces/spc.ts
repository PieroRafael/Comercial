import { Presupuesto } from './presupuesto';
import { VendedorSelect } from './vendedorSelect';
import { Contacto } from './contacto';
import { SpcSelect } from './spcselect';
import { Observable } from 'rxjs';
import { ResponseMensaje } from './responseMensaje';

export interface Spc {
  idspc: number;
  codigo: string;
  codigospc: string;
  idcliente: number;
  idot: number;
  razonsocial: string;
  proyecto: string;
  vendedor: string;
  fecha: Date;
  fechaenviodeconsulta: Date;
  fechaabsolucion: Date;
  fechaentrega: Date;
  fechareunion: Date;
  fechavisitatecnica: Date;
  fcreate: Date;
  ubicacion: string;
  tipo: string;
  ruc: number;  
  telefono: string;
  direccion: string;
  eliminado: boolean;
  consulta: Contacto[];
  tecnica: Contacto[];
  visita: Contacto[];
}

export abstract class SpcData {
  abstract list(): Observable<Spc[]>;
  abstract listByModal(idspc: number): Observable<Spc[]>;
  abstract listByModalPres(idspc: number, path: string): Observable<Presupuesto[]>;
  abstract listSelect(): Observable<SpcSelect[]>;
  abstract listVendedor(): Observable<VendedorSelect[]>;
  abstract get(idspc: number): Observable<Spc>;
  abstract update(spc: Spc): Observable<ResponseMensaje>;
  abstract create(spc: Spc): Observable<ResponseMensaje>;
  abstract delete(idspc: number): Observable<ResponseMensaje>;
}
