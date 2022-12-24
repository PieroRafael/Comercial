import { NbDialogService } from '@nebular/theme';
import { Ot, OtData } from './../../../../@core/comercial/interfaces/ot';
import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { LocalDataSource } from 'ng2-smart-table';
import { ToastService } from '../../../../@core/sistema/utils';
import { ModaldetalleComponent } from '../modaldetalle/modaldetalle.component';

@Component({
  selector: 'ngx-listar-spcs',
  templateUrl: './listar.component.html',
  styleUrls: ['./listar.component.scss'],
})
export class ListarComponent implements OnInit {
  idot = 0;
  ideliminado: boolean;
  otprincipal = 0;
  date: string;
  source: LocalDataSource = new LocalDataSource();
  btnRegistrar = 'none';
  settings = {
    rowClassFunction: row => {
      let clases = `ng-star-inserted ng-smart-row row-${row.data.idot}`;
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
        type: 'string',
      },
      fcreate: {
        title: 'Fecha',
        type: 'string',
      },
      proyecto: {
        title: 'SPC',
        type: 'string',
      },
      tipoproyecto: {
        title: 'Tipo de Proyecto',
        type: 'html',
        valuePrepareFunction: function (value) {
          if (value === true) {
            return 'adicional';
          } else {
            if (value === false) {
              return 'principal';
            }
          }
          return '<div class="centrado">' + value + ' </div>';
        },
      },
    },
  };

  constructor(
    private otService: OtData,
    private dialogService: NbDialogService,
    private router: Router,
    private activateRoute: ActivatedRoute,
    private toastService: ToastService,
  ) {
    this.idot = this.activateRoute.snapshot.params.idot;
    this.otprincipal = this.activateRoute.snapshot.params.otprincipal;
    this.loadData();
  }

  accionModal() {
    this.dialogService.open(ModaldetalleComponent, {
      context: {
        idot: this.idot,
      },
    });
  }

  accion(evento) {
    if (evento === 'crear') {
      this.router.navigate(['/pages/comercial/ot/crear/']);
    } else {
      if (!this.idot) {
        this.toastService.showSeleccion();
      } else {
        if (evento === 'eliminar') {
          this.eliminar();
          return false;
        }
        if (this.ideliminado === true) {
          this.toastService.showAdvertencia('No se pueden hacer operaciones mientras la OT este deshabilitada');
        } else {
          switch (evento) {
            case 'editar':
              this.router.navigate(['/pages/comercial/ot/editar/' + this.idot]);
              break;
            case 'documento':
              this.router.navigate(['/pages/comercial/ot/documentoot/listar/' + this.idot]);
              break;
            case 'adicional':
              if (this.otprincipal === 1) {
                this.toastService.showAdvertencia('Solo se pueden mostras las OT principales');
              } else {
                this.router.navigate(['/pages/comercial/ot/adicional/listar/' + this.idot]);
              }
              break;
            case 'detalle':
              this.accionModal();
              break;
          }
        }
      }
    }
  }

  onRowSelect(event): void {
    this.idot = event.data.idot;
    this.otprincipal = event.data.otprincipal;
    this.ideliminado = event.data.eliminado;
  }

  onRowSelectAdicional(event): void {
    this.otprincipal = event.data.otprincipal;
  }

  eliminar() {
    this.otService.delete(this.idot).subscribe(res => {
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
    document.getElementsByClassName('row-' + this.idot)[0].classList.toggle('ng2-smart-row-eliminado');
    this.router.navigate(['/pages/comercial/ot/listar']);
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  loadData() {
    this.otService.list().subscribe(
      (res: Ot[]) => {
        this.source.load(res);
      },
    );
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
