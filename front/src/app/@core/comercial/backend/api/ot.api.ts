import { Ot } from './../../interfaces/ot';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { DataSource } from 'ng2-smart-table/lib/lib/data-source/data-source';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { OtSelect } from '../../interfaces/otSelect';

@Injectable()
export class OtApi {
  private readonly apiController: string = 'ot';
  private readonly apiControllerFile: string = 'documentoot';
  constructor(private api: HttpService) { }

  get otDataSource(): DataSource {
    return this.api.getServerDataSource(`${this.api.apiUrl}/${this.apiController}`);
  }

  list(): Observable<Ot[]> {
    return this.api.get(this.apiController);
  }

  listByModal(idot: number):
    Observable<Ot[]> {
    return this.api.get(`${this.apiController}/${idot}`);
  }

  listByOt(idot: number): Observable<Ot[]> {
    return this.api.get(`${this.apiController}/adicional/${idot}`);
  }

  listSelect(): Observable<OtSelect[]> {
    return this.api.get(`${this.apiController}/select`);
  }

  get(idot: number): Observable<Ot> {
    return this.api.get(`${this.apiController}/${idot}`);
  }

  add(fdOt: FormData): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, fdOt);
  }

  addFile(formData: FormData): Observable<ResponseMensaje> {
    return this.api.post(this.apiControllerFile, formData);
  }

  update(ot: Ot): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, ot);
  }

  delete(idot: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idot}`, null);
  }
}
