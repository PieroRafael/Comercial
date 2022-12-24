import { Observable } from 'rxjs';
import { DataSource } from 'ng2-smart-table/lib/lib/data-source/data-source';
import { Rol } from './rol';
import { ResponseMensaje } from './responseMensaje';
import { Restore } from './restore';
import { Area } from './area';

export interface Usuario {
  idusuario: number;
  nombres: string;
  apellidos: string;
  correo: string;
  nick: string;
  celular: number;
  eliminado: boolean;
  roles: Array<Rol>;
  area: Area;
  vendedor: boolean;
}

export abstract class UsuarioData {
  abstract get gridDataSource(): DataSource;
  abstract getCurrentUsuario(): Observable<Usuario>;
  abstract list(pageNumber: number, pageSize: number): Observable<Usuario[]>;
  abstract get(idusuario: number): Observable<Usuario>;
  abstract update(usuario: Usuario): Observable<ResponseMensaje>;
  abstract create(usuario: Usuario): Observable<ResponseMensaje>;
  abstract delete(idusuario: number): Observable<ResponseMensaje>;
  abstract restore(restore: Restore): Observable<ResponseMensaje>;
  abstract getAllRoles(): Observable<Rol[]>;
}
