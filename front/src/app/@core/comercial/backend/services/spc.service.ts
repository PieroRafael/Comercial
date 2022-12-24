import { Presupuesto } from './../../interfaces/presupuesto';
import { VendedorSelect } from './../../interfaces/vendedorSelect';
import { Spc } from './../../interfaces/spc';
import { SpcApi } from './../api/spc.api';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { SpcData } from '../../interfaces/spc';
import { SpcSelect } from '../../interfaces/spcselect';

@Injectable()
export class SpcService extends SpcData {
  constructor(private api: SpcApi) {
    super();
  }

  list(): Observable<Spc[]> {
    return this.api.list();
  }

  listByModal(idspc: number):
    Observable<Spc[]> {
    return this.api.listByModal(idspc);
  }

  listByModalPres(idspc: number, rutabase: string):
    Observable<Presupuesto[]> {
    const fdDocumento = new FormData();
    fdDocumento.append('idspc', idspc.toString());
    fdDocumento.append('rutabase', rutabase);
    return this.api.listByModalPres(fdDocumento);
  }

  listSelect(): Observable<SpcSelect[]> {
    return this.api.listSelect();
  }

  listVendedor(): Observable<VendedorSelect[]> {
    return this.api.listVendedor();
  }

  get(idspc: number): Observable<Spc> {
    return this.api.get(idspc);
  }

  create(spc: Spc): Observable<ResponseMensaje> {
    return this.api.add(spc);
  }

  update(spc: Spc): Observable<ResponseMensaje> {
    return this.api.update(spc);
  }

  delete(idspc: number): Observable<ResponseMensaje> {
    return this.api.delete(idspc);
  }
}
