import { Observable } from 'rxjs';
import { OtSelect } from './otSelect';
import { ResponseMensaje } from './responseMensaje';

export interface Ot {
  idot: number;
  idspc: number;
  codigo: string;
  proyecto: string;
  fechaaprobacion: Date;
  otprincipal: number;
  tipoproyecto: boolean;
  estado: string;
  eliminado: boolean;
  nombreproyecto: string;
  razonsocial: string;
  ubicacion: string;
  codigospc: string;
  cliente: string;
  fcreate: Date;
  listFile: FileList;
}

export abstract class OtData {
  abstract list(): Observable<Ot[]>;
  abstract listByModal(idot: number): Observable<Ot[]>;
  abstract listByOt(idot: number): Observable<Ot[]>;
  abstract listSelect(): Observable<OtSelect[]>;
  abstract get(idot: number): Observable<Ot>;
  abstract update(ot: Ot): Observable<ResponseMensaje>;
  abstract create(ot: Ot, files: File): Observable<ResponseMensaje>;
  abstract delete(ot: number): Observable<ResponseMensaje>;
}
