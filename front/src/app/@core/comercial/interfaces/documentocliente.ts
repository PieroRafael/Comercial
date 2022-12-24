import { Observable } from 'rxjs';
import { ResponseMensaje } from './responseMensaje';

export interface DocumentoCliente {
  iddocumentocliente: number;
  fcreate: Date;
  nombre: string;
  descripcion: string;
  ucreate: string;
  idcliente: number;
  acciones: string;
  file: File;
  eliminado: boolean;
  listFile: FileList;
}

export abstract class DocumentoClienteData {
  abstract list(idcliente: number): Observable<DocumentoCliente[]>;
  abstract get(iddocumentocliente: number): Observable<DocumentoCliente>;
  abstract create(documentocliente: DocumentoCliente): Observable<DocumentoCliente>;
  abstract delete(documentocliente: number): Observable<ResponseMensaje>;
}
