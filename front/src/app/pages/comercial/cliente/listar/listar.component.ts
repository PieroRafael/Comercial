import { ToastService } from './../../../../@core/sistema/utils/toast.service';
import { Cliente, ClienteData } from './../../../../@core/comercial/interfaces/cliente';
import { Component, OnInit } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { Router } from '@angular/router';

@Component({
  selector: 'ngx-listar-clientes',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource();
  autorizado = false;
  requeridoEditar = false;
  requeridoDocumento = false;
  requeridoRepresentante = false;
  requeridoEliminar = false;
  idcliente = 0;
  ideliminado: boolean;
  btnRegistrar = 'none';
  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.idcliente}`;
      if (row.data.eliminado) {
        clases += ' ng2-smart-row-eliminado ';
      }

      return clases;
    },
    mode: 'external',
    hideSubHeader: true,
    actions: false,
    columns: {
      ruc: {
        title: 'RUC',
        type: 'number',
      },
      razonsocial: {
        title: 'Razon Social',
        type: 'string',
      },
      telefono: {
        title: 'Teléfono',
        type: 'string',
      },
      direccion: {
        title: 'Dirección',
        type: 'string',
      },
    },
  };

  constructor(private clienteService: ClienteData,
    private router: Router,
    private toastService: ToastService,
  ) {
    this.loadData();
  }

  onCustom(event) {
    if (event.action === 'read-representative') {
      this.router.navigate(['pages/cliente/representante/listar']);
    }
    if (event.action === 'read-documents') {
      this.router.navigate(['pages/cliente/documentocliente/listar']);
    }

  }
  accion(evento) {
    if (!this.idcliente) {
      this.toastService.showSeleccion();
    } else {
      if (this.idcliente) {
        if (evento === 'eliminar') {
          this.eliminar();
          return false;
        }
        if (this.ideliminado === true) {
          this.toastService.showAdvertencia('No se pueden hacer operaciones mientras el cliente este desahbilitado');
        } else {
          switch (evento) {
            case 'editar':
              this.router.navigate(['/pages/comercial/cliente/editar/' + this.idcliente]);
              break;
            case 'documento':
              this.router.navigate(['/pages/comercial/cliente/documentocliente/listar/' + this.idcliente]);
              break;
            case 'representante':
              this.router.navigate(['/pages/comercial/cliente/representante/listar/' + this.idcliente]);
              break;
            case 'unidad':
              this.router.navigate(['/pages/comercial/cliente/unidad/listar/' + this.idcliente]);
              break;
            case 'descarga':
              break;
          }
        }
      }

    }
  }

  eliminar() {

    this.clienteService.delete(this.idcliente).subscribe(res => {
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

  back() {
    document
      .getElementsByClassName('row-' + this.idcliente)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/cliente/listar']);
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  loadData() {
    this.clienteService.list().subscribe(
      (res: Cliente[]) => {
        this.source.load(res);
      },
    );
  }

  onRowSelect(event): void {
    this.idcliente = event.data.idcliente;
    this.ideliminado = event.data.eliminado;
  }

  onSearch(query: string = '') {
    if (query !== '') {
      this.source.setFilter([
        {
          field: 'razonsocial',
          search: query,
        },
        {
          field: 'ruc',
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
