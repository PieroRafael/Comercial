import { ClienteSelect } from './../../interfaces/clienteSelect';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ClienteApi } from '../api/cliente.api';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { Cliente, ClienteData } from '../../interfaces/cliente';

@Injectable()
export class ClienteService extends ClienteData {
  constructor(private api: ClienteApi) {
    super();
  }

  list(): Observable<Cliente[]> {
    return this.api.list();
  }

  listSelect(): Observable<ClienteSelect[]> {
    return this.api.listSelect();
  }

  get(idcliente: number): Observable<Cliente> {
    return this.api.get(idcliente);
  }

  create(cliente: Cliente): Observable<ResponseMensaje> {
    return this.api.add(cliente);
  }

  update(cliente: Cliente): Observable<ResponseMensaje> {
    return this.api.update(cliente);
  }

  delete(idcliente: number): Observable<ResponseMensaje> {
    return this.api.delete(idcliente);
  }
}
