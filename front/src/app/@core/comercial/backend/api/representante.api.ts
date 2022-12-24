import { Representante } from './../../interfaces/representante';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';

@Injectable()
export class RepresentanteApi {
  private readonly apiController: string = 'representante';
  constructor(private api: HttpService) { }

  list(): Observable<Representante[]> {
    return this.api.get(this.apiController);
  }

  listByIdCliente(idcliente: number): Observable<Representante[]> {
    return this.api.get(`${this.apiController}/cliente/${idcliente}`);
  }

  get(idrepresentante: number): Observable<Representante> {
    return this.api.get(`${this.apiController}/${idrepresentante}`);
  }

  add(representante: Representante): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, representante);
  }

  update(representante: Representante): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, representante);
  }

  delete(idrepresentante: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idrepresentante}`, null);
  }
}
