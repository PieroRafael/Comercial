import { Contacto } from './../../../../../@core/comercial/interfaces/contacto';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { ContactoService } from '../../../../../@core/comercial/backend/services/contacto.service';

@Component({
  selector: 'ngx-todorepresentative',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource();
  idcontacto = 0;
  ideliminado: boolean;
  btnRegistrar = 'none';
  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.idcontacto}`;
      if (row.data.eliminado) {
        clases += ' ng2-smart-row-eliminado ';
      }

      return clases;
    },
    mode: 'external',
    hideSubHeader: true,
    actions: false,
    columns: {
      razonsocial: {
        title: 'Cliente',
        type: 'string',
      },
      nombre: {
        title: 'Nombre',
        type: 'string',
      },
      telefono: {
        title: 'Teléfono',
        type: 'string',
      },
      correo: {
        title: 'Correo',
        type: 'string',
      },
      unidad: {
        title: 'Unidad',
        type: 'string',
      },
    },
  };

  constructor(private contactoService: ContactoService,
    private router: Router,
    private toastService: ToastService,
  ) {
    this.loadData();
  }

  accion(evento) {
    if (evento === 'crear') {
      this.router.navigate(['/pages/comercial/cliente/representante/crear/']);
    } else {
      if (!this.idcontacto) {
        this.toastService.showSeleccion();
      } else {
        if (evento === 'eliminar') {
          this.eliminar();
          return false;
        }
        if (this.ideliminado === true) {
          this.toastService.showAdvertencia('No se pueden hacer operaciones mientras el contacto este deshabilitado');
        } else {
          switch (evento) {
            case 'editar':
              this.router.navigate(['/pages/comercial/cliente/representante/editar/' + this.idcontacto]);
              break;
            case 'descarga':

              break;
          }
        }
      }
    }
  }

  onRowSelect(event): void {
    this.idcontacto = event.data.idcontacto;
    this.ideliminado = event.data.eliminado;
  }

  eliminar() {
    this.contactoService.delete(this.idcontacto).subscribe(res => {
      this.handleSuccessResponse(res);
      this.loadData();
    },
      err => {
        this.handleWrongResponse();
      });
  }

  handleSuccessResponse(res) {
    if (res.codigo === 200) {

      this.toastService.showCorrecto(res.mensaje);
    } else {

      this.toastService.showAdvertencia(res.mensaje);
    }
    this.back();
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  loadData() {
    this.contactoService.list().subscribe(
      (res: Contacto[]) => {
        this.source.load(res);
      },
    );
  }

  back() {
    document
      .getElementsByClassName('row-' + this.idcontacto)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/cliente/representantetodo/listar/']);
  }

  onSearch(query: string = '') {
    if (query !== '') {
      this.source.setFilter([
        {
          field: 'nombre',
          search: query,
        },
        {
          field: 'razonsocial',
          search: query,
        },
      ],
        false,
      );
    } else {
      this.source.reset();
      this.loadData();
    }
  }

  ngOnInit(): void {
  }
}
