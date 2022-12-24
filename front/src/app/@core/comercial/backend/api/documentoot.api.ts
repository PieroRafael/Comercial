import { AreasSelect } from './../../interfaces/areasSelect';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { Documentoot } from '../../interfaces/documentoot';

@Injectable()
export class DocumentootApi {
  private readonly apiController: string = 'documentoot';
  private readonly apiControllerArea: string = 'area';

  constructor(private api: HttpService) { }

  list(idot: number): Observable<Documentoot[]> {
    return this.api.get(`${this.apiController}/${idot}`);
  }

  listByIdDocumento(idot: number): Observable<Documentoot[]> {
    return this.api.get(`${this.apiController}/ot/${idot}`);
  }

  listArea(): Observable<AreasSelect[]> {
    return this.api.get(`${this.apiControllerArea}/select`);
  }

  get(iddocumentoot: number): Observable<Documentoot> {
    return this.api.get(`${this.apiController}/${iddocumentoot}`);
  }

  add(formData: FormData): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, formData);
  }

  update(documentoot: Documentoot): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, documentoot);
  }

  delete(iddocumentoot: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${iddocumentoot}`, null);
  }
}
