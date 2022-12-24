import { DocumentoCliente } from './../../interfaces/documentocliente';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { ResponseMensaje } from '../../interfaces/responseMensaje';

@Injectable()
export class DocumentoClienteApi {
  private readonly apiController: string = 'documentocliente';

  constructor(private api: HttpService) { }

  list(idcliente: number): Observable<DocumentoCliente[]> {
    return this.api.get(`${this.apiController}/${idcliente}`);
  }

  get(iddocumentocliente: number): Observable<DocumentoCliente> {
    return this.api.get(`${this.apiController}/${iddocumentocliente}`);
  }

  add(formData: FormData): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, formData);
  }

  update(documentocliente: DocumentoCliente): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, documentocliente);
  }

  delete(iddocumentocliente: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${iddocumentocliente}`, null);
  }
}
