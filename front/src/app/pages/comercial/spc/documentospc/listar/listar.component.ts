import { Seguimientospc } from './../../../../../@core/comercial/interfaces/seguimientospc';
import { Spc } from './../../../../../@core/comercial/interfaces/spc';
import { ModaldocumentospcComponent } from './../modaldocumentospc/modaldocumentospc.component';
import { Component, Input, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LocalDataSource } from 'ng2-smart-table';
import { ToastService } from '../../../../../@core/sistema/utils';
import { NbDialogService } from '@nebular/theme';
import { SpcData } from '../../../../../@core/comercial/interfaces/spc';
import { DatePipe } from '@angular/common';
import { SeguimientospcData } from '../../../../../@core/comercial/interfaces/seguimientospc';

@Component({
  selector: 'ngx-listar-documentos',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {
  @Input() spc: string;
  idseguimientospc = 0;
  idspc = 0;
  proyecto: string;
  source: LocalDataSource = new LocalDataSource();
  sourcemodal: LocalDataSource = new LocalDataSource();
  btnRegistrar = 'none';
  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.iddocumentospc}`;
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
      descripcion: {
        title: 'Descripción',
        type: 'string',
      },
      observacion: {
        title: 'Observaciones',
        type: 'string',
      },
      formarecepcion: {
        title: 'Forma de Recepción',
        type: 'string',
      },
    },
  };

  constructor(private seguimientospcService: SeguimientospcData,
    private datePipe: DatePipe,
    private spcService: SpcData,
    private dialogService: NbDialogService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastService: ToastService,
  ) {
    this.idspc = this.activatedRoute.snapshot.params.idspc;
    this.loadDataId();
  }

  accionModal() {
    this.dialogService.open(ModaldocumentospcComponent, {
      context: {
        idseguimientospc: this.idseguimientospc,
      },
    });
  }

  accion(evento) {
    if (evento === 'crear') {
      this.router.navigate(['/pages/comercial/spc/documentospc/crear/' + this.proyecto + '/' + this.idspc]);
    } else {
      if (!this.idseguimientospc) {
        this.toastService.showSeleccion();
      } else {
        switch (evento) {
          case 'editar':
            this.router.navigate(['/pages/comercial/spc/documentospc/editar/'
              + this.proyecto + '/' + this.idseguimientospc]);
            break;
          case 'detalledocumento':
            this.accionModal();
            break;
        }
      }
    }
  }

  onRowSelect(event): void {
    this.idseguimientospc = event.data.idseguimientospc;
    this.proyecto = this.spc;
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
      .getElementsByClassName('row-' + this.idseguimientospc)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/spc/documentospc/listar']);
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  loadDataId() {
    this.seguimientospcService.listByIdDocumento(this.idspc).subscribe((res: Seguimientospc[]) => {
      this.source.load(res);
    });
  }

  /*loadDataModal() {
    this.controldocumentariospcService.list(this.idseguimientospc).subscribe(
      (res: Controldocumentariospc[]) => {
        this.sourcemodal.load(res);
      },
    );
  }*/

  onSearch(query: string = '') {
    if (query !== '') {
      this.source.setFilter([
        {
          field: 'descripcion',
          search: query,
        },
        {
          field: 'fcreate',
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
    this.spcService.get(this.idspc).subscribe((res: Spc) => {
      this.spc = res.codigo;
    });
  }
}
