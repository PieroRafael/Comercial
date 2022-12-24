import { Contacto } from '../../interfaces/contacto';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';

@Injectable()
export class ContactoApi {
  private readonly apiController: string = 'contacto';
  constructor(private api: HttpService) { }

  list(): Observable<Contacto[]> {
    return this.api.get(this.apiController);
  }

  listByIdCliente(idcliente: number): Observable<Contacto[]> {
    return this.api.get(`${this.apiController}/cliente/${idcliente}`);
  }

  get(idcontacto: number): Observable<Contacto> {
    return this.api.get(`${this.apiController}/${idcontacto}`);
  }

  add(contacto: Contacto): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, contacto);
  }

  update(contacto: Contacto): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, contacto);
  }

  delete(idcontacto: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idcontacto}`, null);
  }
}
