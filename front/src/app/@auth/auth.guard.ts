import { Injectable } from '@angular/core';
import { NbAuthService } from '@nebular/auth';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';
import { UsuarioStore } from '../@core/sistema/stores/usuario.store';
import { Rol } from '../@core/sistema/interfaces/rol';

@Injectable()
export class AuthGuard implements CanActivate {

  private rutasGenerales = ['/auth/reset-password', '/auth/restore-pass', '/pages/usuario/current', '/pages/dashboard'];

  constructor(
    private authService: NbAuthService,
    private router: Router,
    protected usuarioStore: UsuarioStore) { }

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot,
  ): Observable<boolean> | Promise<boolean> | boolean {
    return this.authService.isAuthenticated()
      .pipe(
        tap(authenticated => {
          if (!authenticated) {
            this.router.navigate(['auth/login']);
            /* console.log('No autenticado'); */
          } else {

            if (this.usuarioStore.getUsuario() === undefined) {
              this.router.navigate(['auth/logout']);
              /* console.log('Sesión inválida'); */
            }

            const rutaIngresar = state.url;
            /* console.log('Ruta: ' + rutaIngresar); */
            if (this.rutasGenerales.includes(rutaIngresar)) {
              return;
            }

            let autorizado = false;

            const paginasPermitidas: Rol[] = this.usuarioStore.getUsuario().roles;
            paginasPermitidas.forEach(pagina => {
              if (rutaIngresar.includes(pagina.etiqueta)) {
                autorizado = true;
              }
            });

            if (!autorizado) {
              this.router.navigate(['/pages/dashboard']);
              /* console.log('No autorizado'); */
            }
          }
        }),
      );

  }
}
