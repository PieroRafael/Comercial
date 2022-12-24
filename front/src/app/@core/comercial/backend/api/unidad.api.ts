import { UnidadSelect } from './../../interfaces/unidadSelect';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { Unidad } from '../../interfaces/unidad';

@Injectable()
export class UnidadApi {
  private readonly apiController: string = 'unidad';
  constructor(private api: HttpService) { }

  list(): Observable<Unidad[]> {
    return this.api.get(this.apiController);
  }

  listSelect(idcliente: number): Observable<[UnidadSelect]> {
    return this.api.get(`${this.apiController}/select/${idcliente}`);
  }

  listByIdCliente(idcliente: number): Observable<Unidad[]> {
    return this.api.get(`${this.apiController}/cliente/${idcliente}`);
  }

  get(idunidad: number): Observable<Unidad> {
    return this.api.get(`${this.apiController}/${idunidad}`);
  }

  add(unidad: Unidad): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, unidad);
  }

  update(unidad: Unidad): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, unidad);
  }

  delete(idunidad: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idunidad}`, null);
  }
}
