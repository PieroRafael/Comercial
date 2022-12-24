import { Cliente } from './../../../../../@core/comercial/interfaces/cliente';
import { DocumentoCliente, DocumentoClienteData } from './../../../../../@core/comercial/interfaces/documentocliente';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { LocalDataSource } from 'ng2-smart-table';
import { ClienteData } from '../../../../../@core/comercial/interfaces/cliente';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'ngx-listar-documentos',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {
  @Input() cliente: string;
  source: LocalDataSource = new LocalDataSource();
  iddocumentocliente = 0;
  idcliente = 0;
  btnRegistrar = 'none';
  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.iddocumentocliente}`;
      if (row.data.eliminado) {
        clases += ' ng2-smart-row-eliminado ';
      }

      return clases;
    },
    mode: 'external',
    hideSubHeader: true,
    actions: false,
    columns: {
      fcreate: {
        title: 'Fecha',
        type: 'html',
        valuePrepareFunction: date => {
          const raw = new Date(date);
          const formatted = this.datePipe.transform(raw, 'dd/MM/yyyy', 'UTC');
          return '<div class="centrado"> ' + formatted + ' </div>';
        },
      },
      nombre: {
        title: 'Nombre',
        type: 'string',
      },
      ucreate: {
        title: 'Usuario',
        type: 'string',
      },
    },
  };

  constructor(private documentoclienteService: DocumentoClienteData,
    private datePipe: DatePipe,
    private clienteService: ClienteData,
    private router: Router,
    private activatedRouter: ActivatedRoute,
    private toastService: ToastService,
  ) {
    this.idcliente = this.activatedRouter.snapshot.params.idcliente;
    this.loadData();
  }

  accion(evento) {
    if (evento === 'crear') {
      this.router.navigate(['/pages/comercial/cliente/documentocliente/crear/' + this.idcliente]);
    } else {
      if (!this.iddocumentocliente) {
        this.toastService.showSeleccion();
      } else {
        switch (evento) {
          case 'eliminar':
            this.eliminar();
            break;
          case 'descarga':
            break;
        }
      }
    }
  }

  eliminar() {
    this.documentoclienteService.delete(this.iddocumentocliente).subscribe(res => {
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
      .getElementsByClassName('row-' + this.iddocumentocliente)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/cliente/documentocliente/listar/' + this.idcliente]);
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  loadData() {
    this.documentoclienteService.list(this.idcliente).subscribe(
      (res: DocumentoCliente[]) => {
        this.source.load(res);
      },
    );
  }

  onRowSelect(event): void {
    this.iddocumentocliente = event.data.iddocumentocliente;
  }

  onSearch(query: string = '') {
    if (query !== '') {
      this.source.setFilter([
        {
          field: 'nombre',
          search: query,
        },
        {
          field: 'ucreate',
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
