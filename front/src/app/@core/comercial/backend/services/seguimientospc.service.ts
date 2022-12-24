import { Seguimientospc } from './../../interfaces/seguimientospc';
import { SeguimientospcApi } from './../api/seguimientospc.api';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { SeguimientospcData } from '../../interfaces/seguimientospc';

@Injectable()
export class SeguimientospcService extends SeguimientospcData {
  constructor(private api: SeguimientospcApi) {
    super();
  }

  list(): Observable<Seguimientospc[]> {
    return this.api.list();
  }

  listByIdDocumento(idspc: number): Observable<Seguimientospc[]> {
    return this.api.listByIdDocumento(idspc);
  }

  listByModal(idseguimientospc: number, rutabase: string):
    Observable<Seguimientospc[]> {
    const fdDocumento = new FormData();
    fdDocumento.append('idseguimientospc', idseguimientospc.toString());
    fdDocumento.append('rutabase', rutabase);
    return this.api.listByDocumentoModal(fdDocumento);
  }

  get(idseguimientospc: number): Observable<Seguimientospc> {
    return this.api.get(idseguimientospc);
  }

  create(seguimientospc): Observable<ResponseMensaje> {
    const fdSeguimiento = new FormData();
    fdSeguimiento.append('idspc', seguimientospc.idspc);
    fdSeguimiento.append('formarecepcion', seguimientospc.formarecepcion);
    fdSeguimiento.append('descripcion', seguimientospc.descripcion);
    fdSeguimiento.append('observacion', seguimientospc.observacion);
    for (let i = 0; i < seguimientospc.listFile.length; i++) {
      fdSeguimiento.append('listFile', seguimientospc.listFile[i]);
    }
    fdSeguimiento.append('listFolder', seguimientospc.listFolder);
    return this.api.add(fdSeguimiento);
  }

  createPrueba(seguimientospc): Observable<ResponseMensaje> {
    const fdSeguimiento = new FormData();
    fdSeguimiento.append('idspc', seguimientospc.idspc);
    fdSeguimiento.append('formarecepcion', seguimientospc.formarecepcion);
    fdSeguimiento.append('descripcion', seguimientospc.descripcion);
    fdSeguimiento.append('observacion', seguimientospc.observacion);
    for (let i = 0; i < seguimientospc.listFile.length; i++) {
      fdSeguimiento.append('listFile', seguimientospc.listFile[i]);
    }
    fdSeguimiento.append('listFolder', seguimientospc.listFolder);
    return this.api.pruebaFile(fdSeguimiento);
  }

  createFile(seguimientospc) {
    const formData = new FormData();
    formData.append('file', seguimientospc.file);
    formData.append('idseguimientospc', seguimientospc.idseguimientospc);
    return this.api.addFile(formData);
  }

  update(seguimientospc: Seguimientospc): Observable<ResponseMensaje> {
    return this.api.update(seguimientospc);
  }

  delete(idseguimientospc: number): Observable<ResponseMensaje> {
    return this.api.delete(idseguimientospc);
  }
}
