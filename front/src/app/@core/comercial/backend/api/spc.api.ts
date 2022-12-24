import { Presupuesto } from './../../interfaces/presupuesto';
import { VendedorSelect } from './../../interfaces/vendedorSelect';
import { Spc } from './../../interfaces/spc';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { SpcSelect } from '../../interfaces/spcselect';

@Injectable()
export class SpcApi {
  private readonly apiController: string = 'spc';
  private readonly apiControllerPres: string = 'documentopres';
  private readonly apiControllerVendedor: string = 'usuario';
  constructor(private api: HttpService) { }

  list(): Observable<Spc[]> {
    return this.api.get(this.apiController);
  }

  listByModal(idspc: number):
    Observable<Spc[]> {
    return this.api.get(`${this.apiController}/${idspc}`);
  }

  listByModalPres(fdDocumento: FormData): Observable<Presupuesto[]> {
    return this.api.post(this.apiControllerPres, fdDocumento);
  }

  listSelect(): Observable<SpcSelect[]> {
    return this.api.get(`${this.apiController}/select`);
  }

  listVendedor(): Observable<VendedorSelect[]> {
    return this.api.get(`${this.apiControllerVendedor}/vendedor`);
  }

  get(idspc: number): Observable<Spc> {
    return this.api.get(`${this.apiController}/${idspc}`);
  }

  add(spc: Spc): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, spc);
  }

  update(spc: Spc): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, spc);
  }

  delete(idspc: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idspc}`, null);
  }
}
