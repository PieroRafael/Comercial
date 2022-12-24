import { Component, Input, OnInit } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { Rol } from '../../../../@core/sistema/interfaces/rol';
import { UsuarioData } from '../../../../@core/sistema/interfaces/usuario';

@Component({
  selector: 'ngx-permiso',
  templateUrl: './permiso.component.html',
  styleUrls: ['./permiso.component.scss'],
})
export class PermisoComponent implements OnInit {

  @Input() usuarioPermisos: Array<Rol>;
  permisos = [];

  constructor(
    private usuariosService: UsuarioData,
    protected ref: NbDialogRef<PermisoComponent>) {
  }

  ngOnInit(): void {
    this.usuariosService.getAllRoles().subscribe((res: Rol[]) => {
      this.permisos = res;
      this.usuarioPermisos.forEach(per => {
        const i = this.permisos.findIndex(function (permiso) {
          return permiso.etiqueta === per.etiqueta;
        });
        this.permisos[i].selected = true;
      });
    });
  }

  save() {
    let i = 0;
    do {
      this.permisos.forEach(per => {
        if (!per.selected) {
          this.cleanPermisos(per.etiqueta);
        }
      });
      i = this.permisos.findIndex(function (permiso) {
        return permiso.selected === false;
      });
    } while (i !== -1);

    this.ref.close(this.permisos);
  }

  cleanPermisos(etiqueta) {
    const i = this.permisos.findIndex(function (permiso) {
      return permiso.etiqueta === etiqueta;
    });
    if (i !== -1) {
      this.permisos.splice(i, 1);
    }
  }

  dismiss() {
    this.permisos = [];
    this.ref.close();
  }
}
