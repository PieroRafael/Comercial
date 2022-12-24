import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { UsuarioApi } from '../api/usuario.api';
import { UsuarioData, Usuario } from '../../../sistema/interfaces/usuario';
import { DataSource } from 'ng2-smart-table/lib/lib/data-source/data-source';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { Restore } from '../../interfaces/restore';
import { Rol } from '../../interfaces/rol';

@Injectable()
export class UsuarioService extends UsuarioData {

  constructor(private api: UsuarioApi) {
    super();
  }

  get gridDataSource(): DataSource {
    return this.api.usuarioDataSource;
  }

  list(pageNumber: number = 1, pageSize: number = 10): Observable<Usuario[]> {
    return this.api.list(pageNumber, pageSize);
  }

  getCurrentUsuario(): Observable<Usuario> {
    return this.api.getCurrent();
  }

  get(idusuario: number): Observable<Usuario> {
    return this.api.get(idusuario);
  }

  create(usuario: Usuario): Observable<ResponseMensaje> {
    return this.api.add(usuario);
  }

  update(usuario: Usuario): Observable<ResponseMensaje> {
    return this.api.update(usuario);
  }

  delete(idusuario: number): Observable<ResponseMensaje> {
    return this.api.delete(idusuario);
  }

  restore(restore: Restore): Observable<ResponseMensaje> {
    return this.api.restore(restore);
  }

  getAllRoles(): Observable<Rol[]> {
    return this.api.getAllRoles();
  }
}
