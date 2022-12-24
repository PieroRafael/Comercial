import { Contacto } from './../../interfaces/contacto';
import { ContactoApi } from './../api/contacto.api';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { ContactoData } from '../../interfaces/contacto';

@Injectable()
export class ContactoService extends ContactoData {
  constructor(private api: ContactoApi) {
    super();
  }

  list(): Observable<Contacto[]> {
    return this.api.list();
  }

  listByIdCliente(idcliente: number): Observable<Contacto[]> {
    return this.api.listByIdCliente(idcliente);
  }

  get(idcontacto: number): Observable<Contacto> {
    return this.api.get(idcontacto);
  }

  create(contacto: Contacto): Observable<ResponseMensaje> {
    return this.api.add(contacto);
  }

  update(contacto: Contacto): Observable<ResponseMensaje> {
    return this.api.update(contacto);
  }

  delete(idcontacto: number): Observable<ResponseMensaje> {
    return this.api.delete(idcontacto);
  }
}
