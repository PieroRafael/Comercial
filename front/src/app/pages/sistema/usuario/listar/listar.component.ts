import { Component } from '@angular/core';
import { DataSource } from 'ng2-smart-table/lib/lib/data-source/data-source';
import { Router } from '@angular/router';
import { UsuarioData } from '../../../../@core/sistema/interfaces/usuario';
import { ToastService } from '../../../../@core/sistema/utils';

@Component({
  selector: 'ngx-listar-usuario',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent {

  idusuario = 0;
  source: DataSource;

  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.idusuario}`;
      if (row.data.eliminado) {
        clases += ' ng2-smart-row-eliminado ';
      }

      return clases;
    },
    mode: 'external',
    actions: false,
    hideSubHeader: true,
    columns: {
      nombres: {
        title: 'Nombres',
        type: 'string',
      },
      apellidos: {
        title: 'Apellidos',
        type: 'string',
      },
      correo: {
        title: 'Correo',
        type: 'string',
      },
      nick: {
        title: 'Usuario',
        type: 'string',
      },
    },
  };

  constructor(private usuarioService: UsuarioData,
    private router: Router,
    private toastService: ToastService) {
    this.loadData();
  }

  accion(evento) {
    if (!this.idusuario) {

      this.toastService.showSeleccion();
    } else {
      switch (evento) {
        case 'editar':
          this.router.navigate(['/pages/usuario/editar/' + this.idusuario]);
          break;
        case 'eliminar':
          this.eliminar();
          break;
      }
    }
  }

  eliminar() {

    this.usuarioService.delete(this.idusuario).subscribe(res => {
      this.handleSuccessResponse(res);
    },
      err => {
        this.handleWrongResponse();
      });
  }

  onRowSelect(event): void {
    this.idusuario = event.data.idusuario;
  }

  loadData() {
    this.source = this.usuarioService.gridDataSource;
  }

  handleSuccessResponse(res) {
    if (res.codigo === 200) {

      this.toastService.showCorrecto(res.mensaje);
    } else {

      this.toastService.showAdvertencia(res.mensaje);
    }
    this.back();
  }

  back() {
    document
      .getElementsByClassName('row-' + this.idusuario)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/usuario/listar']);
  }

  handleWrongResponse() {
    this.toastService.showError();
  }
}
