import { Cliente } from './../../../../../@core/comercial/interfaces/cliente';
import { ClienteService } from './../../../../../@core/comercial/backend/services/cliente.service';
import { Unidad } from './../../../../../@core/comercial/interfaces/unidad';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { LocalDataSource } from 'ng2-smart-table';
import { Component, Input, OnInit } from '@angular/core';
import { UnidadData } from '../../../../../@core/comercial/interfaces/unidad';

@Component({
  selector: 'ngx-listar',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {
  @Input() cliente: string;
  source: LocalDataSource = new LocalDataSource();
  idunidad = 0;
  idcliente = 0;
  ideliminado: boolean;
  btnRegistrar = 'none';
  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.idunidad}`;
      if (row.data.eliminado) {
        clases += ' ng2-smart-row-eliminado ';
      }

      return clases;
    },
    mode: 'external',
    hideSubHeader: true,
    actions: false,
    columns: {
      nombre: {
        title: 'Nombre',
        type: 'string',
      },
      descripcion: {
        title: 'Descripcion',
        type: 'string',
      },
    },
  };

  constructor(private unidadService: UnidadData,
    private clienteService: ClienteService,
    private activatedRouter: ActivatedRoute,
    private router: Router,
    private toastService: ToastService,
  ) {
    this.idcliente = this.activatedRouter.snapshot.params.idcliente;
    this.loadData();
  }

  accion(evento) {
    if (evento === 'crear') {
      this.router.navigate(['/pages/comercial/cliente/unidad/crear/' + this.idcliente]);
    } else {
      if (!this.idunidad) {
        this.toastService.showSeleccion();
      } else {
        if (evento === 'eliminar') {
          this.eliminar();
          return false;
        }
        if (this.ideliminado === true) {
          this.toastService.showAdvertencia('No se pueden hacer operaciones mientras la unidad este deshabilitado');
        } else {
          switch (evento) {
            case 'editar':
              this.router.navigate(['/pages/comercial/cliente/unidad/editar/' + this.idunidad]);
              break;
            case 'descarga':

              break;
          }
        }
      }
    }
  }

  eliminar() {

    this.unidadService.delete(this.idunidad).subscribe(res => {
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
      .getElementsByClassName('row-' + this.idunidad)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/cliente/unidad/listar/' + this.idcliente]);
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  loadData() {
    this.unidadService.listByIdCliente(this.idcliente).subscribe(
      (res: Unidad[]) => {
        this.source.load(res);
      },
    );
  }

  onRowSelect(event): void {
    this.idunidad = event.data.idunidad;
    this.ideliminado = event.data.eliminado;
  }

  onSearch(query: string = '') {
    if (query !== '') {
      this.source.setFilter([
        {
          field: 'nombre',
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
    this.clienteService.get(this.idcliente).subscribe((res: Cliente) => {
      this.cliente = res.razonsocial;
    });
  }
}
