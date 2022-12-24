import { Seguimientospc } from './../../interfaces/seguimientospc';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';

@Injectable()
export class SeguimientospcApi {
  private readonly apiController: string = 'seguimientospc';
  private readonly apiControllerFile: string = 'documentospc';
  private readonly apiControllerFile1: string = 'documentospc/newdoc';
  constructor(private api: HttpService) { }

  list(): Observable<Seguimientospc[]> {
    return this.api.get(this.apiController);
  }

  listByIdDocumento(idspc: number): Observable<Seguimientospc[]> {
    return this.api.get(`${this.apiController}/spc/${idspc}`);
  }

  listByDocumentoModal(fdDocumento: FormData): Observable<Seguimientospc[]> {
    return this.api.post(this.apiControllerFile, fdDocumento);
  }

  get(idseguimientospc: number): Observable<Seguimientospc> {
    return this.api.get(`${this.apiController}/${idseguimientospc}`);
  }

  add(fdSeguimiento: FormData): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, fdSeguimiento);
  }

  addFile(formData: FormData): Observable<ResponseMensaje> {
    return this.api.post(this.apiControllerFile, formData);
  }

  pruebaFile(formData: FormData): Observable<ResponseMensaje> {
    return this.api.post(this.apiControllerFile1, formData);
  }

  update(seguimientospc: Seguimientospc): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, seguimientospc);
  }

  delete(idseguimientospc: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idseguimientospc}`, null);
  }
}
