import { AreasSelect } from './../../interfaces/areasSelect';
import { DocumentootApi } from './../api/documentoot.api';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Documentoot } from '../../interfaces/documentoot';
import { ResponseMensaje } from '../../interfaces/responseMensaje';

@Injectable()
export class DocumentootService {
  constructor(private api: DocumentootApi) {
  }

  list(idot: number): Observable<Documentoot[]> {
    return this.api.list(idot);
  }

  listByIdDocumento(idot: number): Observable<Documentoot[]> {
    return this.api.listByIdDocumento(idot);
  }

  listArea(): Observable<AreasSelect[]> {
    return this.api.listArea();
  }

  get(iddocumentoot: number): Observable<Documentoot> {
    return this.api.get(iddocumentoot);
  }

  create(documento) {
    const formData = new FormData();
    formData.append('file', documento.file);
    formData.append('descripcion', documento.descripcion);
    formData.append('listArea', documento.listArea);
    formData.append('idot', documento.idot);
    return this.api.add(formData);
  }

  update(documentoot: Documentoot): Observable<ResponseMensaje> {
    return this.api.update(documentoot);
  }

  delete(iddocumentoot: number): Observable<ResponseMensaje> {
    return this.api.delete(iddocumentoot);
  }
}
