import { Cliente } from './../../interfaces/cliente';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { ClienteSelect } from '../../interfaces/clienteSelect';

@Injectable()
export class ClienteApi {
  private readonly apiController: string = 'cliente';
  constructor(private api: HttpService) { }

  list(): Observable<Cliente[]> {
    return this.api.get(this.apiController);
  }

  listSelect(): Observable<ClienteSelect[]> {
    return this.api.get(`${this.apiController}/select`);
  }

  get(idcliente: number): Observable<Cliente> {
    return this.api.get(`${this.apiController}/${idcliente}`);
  }

  add(cliente: Cliente): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, cliente);
  }

  update(cliente: Cliente): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, cliente);
  }

  delete(idcliente: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idcliente}`, null);
  }
}
