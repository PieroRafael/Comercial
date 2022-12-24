import { UnidadSelect } from './../../interfaces/unidadSelect';
import { UnidadApi } from './../api/unidad.api';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { Unidad, UnidadData } from '../../interfaces/unidad';

@Injectable()
export class UnidadService extends UnidadData {
  constructor(private api: UnidadApi) {
    super();
  }

  list(): Observable<Unidad[]> {
    return this.api.list();
  }

  listSelect(idcliente: number): Observable<UnidadSelect[]> {
    return this.api.listSelect(idcliente);
  }

  listByIdCliente(idcliente: number): Observable<Unidad[]> {
    return this.api.listByIdCliente(idcliente);
  }

  get(idunidad: number): Observable<Unidad> {
    return this.api.get(idunidad);
  }

  create(unidad: Unidad): Observable<ResponseMensaje> {
    return this.api.add(unidad);
  }

  update(unidad: Unidad): Observable<ResponseMensaje> {
    return this.api.update(unidad);
  }

  delete(idunidad: number): Observable<ResponseMensaje> {
    return this.api.delete(idunidad);
  }
}
