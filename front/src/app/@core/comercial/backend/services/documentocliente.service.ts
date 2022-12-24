import { DocumentoClienteApi } from './../api/documentocliente.api';
import { DocumentoCliente } from './../../interfaces/documentocliente';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseMensaje } from '../../interfaces/responseMensaje';

@Injectable()
export class DocumentoClienteService {
  constructor(private api: DocumentoClienteApi) {
  }

  list(idcliente: number): Observable<DocumentoCliente[]> {
    return this.api.list(idcliente);
  }

  get(iddocumentocliente: number): Observable<DocumentoCliente> {
    return this.api.get(iddocumentocliente);
  }

  create(documento) {
    const formData = new FormData();
    formData.append('descripcion', documento.descripcion);
    formData.append('idcliente', documento.idcliente);
    for (let i = 0; i < documento.listFile.length; i++) {
      formData.append('listFile', documento.listFile[i]);
    }
    return this.api.add(formData);
  }

  update(documentocliente: DocumentoCliente): Observable<ResponseMensaje> {
    return this.api.update(documentocliente);
  }

  delete(iddocumentocliente: number): Observable<ResponseMensaje> {
    return this.api.delete(iddocumentocliente);
  }
}
