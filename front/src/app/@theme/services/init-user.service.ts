import { Observable } from 'rxjs';
import { Usuario, UsuarioData } from '../../@core/sistema/interfaces/usuario';
import { tap } from 'rxjs/operators';
import { UsuarioStore } from '../../@core/sistema/stores/usuario.store';
import { Injectable } from '@angular/core';

@Injectable()
export class InitUserService {
  constructor(protected usuarioStore: UsuarioStore,
    protected usuarioService: UsuarioData) { }

  initCurrentUser(): Observable<Usuario> {
    return this.usuarioService.getCurrentUsuario()
      .pipe(tap((usuario: Usuario) => {
        if (usuario) {
          this.usuarioStore.setUsuario(usuario);
        }
      }));
  }
}
