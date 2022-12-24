import { Adicionalot } from '../../interfaces/adicionalot';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';

@Injectable()
export class AdicionalotApi {
  private readonly apiController: string = 'adicionalot';
  constructor(private api: HttpService) { }

  list(): Observable<Adicionalot[]> {
    return this.api.get(this.apiController);
  }

  get(idot: number): Observable<Adicionalot> {
    return this.api.get(`${this.apiController}/${idot}`);
  }

  add(adicionalot: Adicionalot): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, adicionalot);
  }

  update(adicionalot: Adicionalot): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, adicionalot);
  }

  delete(idadicionalot: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idadicionalot}`, null);
  }
}
