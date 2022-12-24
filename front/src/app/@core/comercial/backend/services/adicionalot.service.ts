import { AdicionalotApi } from './../api/adicionalot.api';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Adicionalot } from '../../interfaces/adicionalot';
import { ResponseMensaje } from '../../interfaces/responseMensaje';

@Injectable()
export class AdicionalotService {
  constructor(private api: AdicionalotApi) {
  }

  list(): Observable<Adicionalot[]> {
    return this.api.list();
  }

  get(idadicionalot: number): Observable<Adicionalot> {
    return this.api.get(idadicionalot);
  }

  create(adicionalot: Adicionalot): Observable<ResponseMensaje> {
    return this.api.add(adicionalot);
  }

  update(adicionalot: Adicionalot): Observable<ResponseMensaje> {
    return this.api.update(adicionalot);
  }

  delete(idadicionalot: number): Observable<ResponseMensaje> {
    return this.api.delete(idadicionalot);
  }
}
