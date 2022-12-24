import { ModalpresComponent } from './../modalpres/modalpres.component';
import { ModaldetalleComponent } from './../modaldetalle/modaldetalle.component';
import { NbDialogService } from '@nebular/theme';
import { Spc, SpcData } from './../../../../@core/comercial/interfaces/spc';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LocalDataSource } from 'ng2-smart-table';
import { ToastService } from '../../../../@core/sistema/utils';

@Component({
  selector: 'ngx-listar-spcs',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {

  idspc = 0;
  ideliminado: boolean;
  source: LocalDataSource = new LocalDataSource();
  btnRegistrar = 'none';
  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.idspc}`;
      if (row.data.eliminado) {
        clases += ' ng2-smart-row-eliminado ';
      }

      return clases;
    },
    mode: 'external',
    hideSubHeader: true,
    actions: false,
    columns: {
      codigo: {
        title: 'Código',
        type: 'string,',
      },
      fcreate: {
        title: 'Fecha',
        type: 'string',
      },
      razonsocial: {
        title: 'Cliente',
        type: 'string',
      },
      proyecto: {
        title: 'Proyecto',
        type: 'string',
      },
      vendedor: {
        title: 'vendedor',
        type: 'string',
      },
      tipo: {
        title: 'Tipo',
        type: 'string',
      },
    },
  };

  constructor(private spcService: SpcData,
    private dialogService: NbDialogService,
    private router: Router,
    private toastService: ToastService,
  ) {
    this.loadData();
  }

  accionModal() {
    this.dialogService.open(ModaldetalleComponent, {
      context: {
        idspc: this.idspc,
      },
    });
  }

  accionPresupuesto() {
    this.dialogService.open(ModalpresComponent, {
      context: {
        idspc: this.idspc,
      },
    });
  }

  accion(evento) {
    if (evento === 'crear') {
      this.router.navigate(['/pages/comercial/spc/crear/']);
    } else {
      if (!this.idspc) {
        this.toastService.showSeleccion();
      } else {
        if (evento === 'eliminar') {
          this.eliminar();
          return false;
        }
        if (this.ideliminado === true) {
          this.toastService.showAdvertencia('No se pueden hacer operaciones mientras la SPC este deshabilitada');
        } else {
          switch (evento) {
            case 'editar':
              this.router.navigate(['/pages/comercial/spc/editar/' + this.idspc]);
              break;
            case 'documento':
              this.router.navigate(['/pages/comercial/spc/documentospc/listar/' + this.idspc]);
              break;
            case 'detalle':
              this.accionModal();
              break;
            case 'presupuesto':
              this.accionPresupuesto();
              break;
            case 'descarga':
              break;
          }
        }
      }
    }
  }

  eliminar() {
    this.spcService.delete(this.idspc).subscribe(res => {
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
      .getElementsByClassName('row-' + this.idspc)[0]
      .classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/spc/listar']);
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  loadData() {
    this.spcService.list().subscribe(
      (res: Spc[]) => {
        this.source.load(res);
      },
    );
  }

  onRowSelect(event): void {
    this.idspc = event.data.idspc;
    this.ideliminado = event.data.eliminado;
  }

  onSearch(query: string = '') {
    if (query !== '') {
      this.source.setFilter([
        {
          field: 'proyecto',
          search: query,
        },
        {
          field: 'codigo',
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
