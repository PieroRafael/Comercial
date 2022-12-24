import { Ot } from './../../../../../@core/comercial/interfaces/ot';
import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LocalDataSource } from 'ng2-smart-table';
import { ToastService } from '../../../../../@core/sistema/utils';
import { Documentoot, DocumentootData } from '../../../../../@core/comercial/interfaces/documentoot';
import { OtData } from '../../../../../@core/comercial/interfaces/ot';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'ngx-listar-documentos',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {
  @Input() ot: string;
  iddocumentoot = 0;
  ideliminado: boolean;
  idot = 0;
  source: LocalDataSource = new LocalDataSource();
  btnRegistrar = 'none';
  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.iddocumentoot}`;
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
      listArea: {
        title: 'Áreas',
        type: 'string',
      },
    },
  };

  constructor(private documentootService: DocumentootData,
    private datePipe: DatePipe,
    private otService: OtData,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastService: ToastService,
  ) {
    this.iddocumentoot = this.activatedRoute.snapshot.params.iddocumentoot;
    this.idot = this.activatedRoute.snapshot.params.idot;
  }

  accion(evento) {
    if (evento === 'crear') {
      this.router.navigate(['/pages/comercial/ot/documentoot/crear/' + this.idot]);
    } else {
      if (!this.iddocumentoot) {
        this.toastService.showSeleccion();
      } else {
        if (evento === 'eliminar') {
          this.eliminar();
          return false;
        }
        if (this.ideliminado === true) {
          this.toastService.showAdvertencia('No se pueden hacer operaciones mientras el  documento OT este deshabilitado');
        } else {
          switch (evento) {
            case 'editar':
              this.router.navigate(['/pages/comercial/ot/documentoot/editar/' + this.iddocumentoot]);
              break;
            case 'descarga':
              break;
          }
        }
      }
    }
  }

  onRowSelect(event): void {
    this.iddocumentoot = event.data.iddocumentoot;
    this.ideliminado = event.data.eliminado;
  }

  eliminar() {
    this.documentootService.delete(this.iddocumentoot).subscribe(res => {
      this.handleSuccessResponse(res);
      this.loadDataId();
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
      .getElementsByClassName('row-' + this.iddocumentoot)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/ot/documentoot/listar/' + this.idot]);
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  loadDataId() {
    this.documentootService.listByIdDocumento(this.idot).subscribe((res: Documentoot[]) => {
      this.source.load(res);
    });
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
      this.loadDataId();
    }
  }

  ngOnInit(): void {
    this.loadDataId();
    this.otService.get(this.idot).subscribe((res: Ot) => {
      this.ot = res.codigo;
    });
  }
}
