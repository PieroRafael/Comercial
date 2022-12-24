import { Injectable } from '@angular/core';
import { HttpParams } from '@angular/common/http';
import { Observable } from 'rxjs';
import { HttpService } from './http.service';
import { DataSource } from 'ng2-smart-table/lib/lib/data-source/data-source';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { Usuario } from '../../interfaces/usuario';
import { Restore } from '../../interfaces/restore';
import { Rol } from '../../interfaces/rol';

@Injectable()
export class UsuarioApi {
  private readonly apiController: string = 'usuario';

  constructor(private api: HttpService) { }

  get usuarioDataSource(): DataSource {
    return this.api.getServerDataSource(`${this.api.apiUrl}/${this.apiController}`);
  }

  list(pageNumber: number = 1, pageSize: number = 10): Observable<Usuario[]> {
    const params = new HttpParams()
      .set('pageNumber', `${pageNumber}`)
      .set('pageSize', `${pageSize}`);

    return this.api.get(this.apiController, { params });
  }

  getCurrent(): Observable<Usuario> {
    return this.api.get(`${this.apiController}/current`);
  }

  get(idusuario: number): Observable<Usuario> {
    return this.api.get(`${this.apiController}/${idusuario}`);
  }

  add(usuario: Usuario): Observable<ResponseMensaje> {
    return this.api.post(this.apiController, usuario);
  }

  update(usuario: Usuario): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}`, usuario);
  }

  delete(idusuario: number): Observable<ResponseMensaje> {
    return this.api.put(`${this.apiController}/${idusuario}`, null);
  }

  restore(restore: Restore): Observable<ResponseMensaje> {
    return this.api.post('auth/restore-pass', restore);
  }

  getAllRoles(): Observable<Rol[]> {
    return this.api.get(`rol`);
  }
}
