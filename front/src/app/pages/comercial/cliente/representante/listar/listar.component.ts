import { Contacto } from './../../../../../@core/comercial/interfaces/contacto';
import { Cliente } from './../../../../../@core/comercial/interfaces/cliente';
import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LocalDataSource } from 'ng2-smart-table';
import { ToastService } from '../../../../../@core/sistema/utils';
import { ClienteData } from '../../../../../@core/comercial/interfaces/cliente';
import { ContactoData } from '../../../../../@core/comercial/interfaces/contacto';

@Component({
  selector: 'ngx-listar-representantes',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {
  @Input() cliente: string;
  source: LocalDataSource = new LocalDataSource();
  btnRegistrar = 'none';
  idcontacto = 0;
  ideliminado: boolean;
  idcliente = 0;
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

  constructor(private contactoService: ContactoData,
    private clienteService: ClienteData,
    private activatedRouter: ActivatedRoute,
    private router: Router,
    private toastService: ToastService,
  ) {
    this.idcliente = this.activatedRouter.snapshot.params.idcliente;
    this.loadData();
  }

  accion(evento) {
    if (evento === 'crear') {
      this.router.navigate(['/pages/comercial/cliente/representante/crear/' + this.idcliente]);
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
    this.contactoService.listByIdCliente(this.idcliente).subscribe((res: Contacto[]) => {
      this.source.load(res);
    });
  }

  back() {
    document
      .getElementsByClassName('row-' + this.idcontacto)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/cliente/representante/listar/' + this.idcliente]);
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
