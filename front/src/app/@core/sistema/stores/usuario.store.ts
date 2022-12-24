import { Injectable } from '@angular/core';
import { Usuario } from '../interfaces/usuario';
import { BehaviorSubject } from 'rxjs';
import { share } from 'rxjs/operators';

@Injectable({
  providedIn: 'root',
})
export class UsuarioStore {

  private usuario: Usuario;

  protected usuarioState$ = new BehaviorSubject<Usuario | {}>({});

  getUsuario(): Usuario {
    return this.usuario;
  }

  setUsuario(paramUsuario: Usuario) {
    this.usuario = paramUsuario;

    this.changeUsuarioState(paramUsuario);
  }

  onUsuarioStateChange() {
    return this.usuarioState$.pipe(share());
  }

  changeUsuarioState(paramUsuario: Usuario) {
    this.usuarioState$.next(paramUsuario);
  }
}
