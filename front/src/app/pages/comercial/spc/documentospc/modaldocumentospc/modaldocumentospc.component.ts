import { MouseGuard } from './MouseGuard';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { Seguimientospc } from './../../../../../@core/comercial/interfaces/seguimientospc';
import { SeguimientospcService } from './../../../../../@core/comercial/backend/services/seguimientospc.service';
import { LocalDataSource } from 'ng2-smart-table';
import { ActivatedRoute } from '@angular/router';
import { Component, OnInit, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';

@Component({
  selector: 'ngx-modaldocumentospc',
  templateUrl: './modaldocumentospc.component.html',
  styleUrls: ['./modaldocumentospc.component.scss'],
})
export class ModaldocumentospcComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource();
  idspc = 0;
  tipo = 0;
  rutaPasada = 'null';
  @Input() idseguimientospc: number;
  contacto = [];

  settings_modal = {
    hideSubHeader: true,
    actions: {
      columnTitle: 'Acciones',
      edit: false,
      delete: false,
      position: 'right',
      custom: [
        {
          name: 'deleteAction',
          title: '<i class="fas fa-download" title="Descargar"></i>',
        },
      ],
    },
    columns: {
      tipo: {
        title: 'Icono',
        type: 'html',
        valuePrepareFunction: function (value) {
          if (value === true) {
            return '<div class="centrado"><i class="fas fa-file" title="Archivo"></i></div>';
          } else {
            if (value === false) {
              return '<div class="centrado"><i class="fas fa-folder" title="Carpeta"></i></div>';
            }
          }
        },
      },
      nombre: {
        title: 'Nombre',
        type: 'string',
      },
    },
  };

  constructor(private seguimientospcService: SeguimientospcService,
    private activatedRoute: ActivatedRoute,
    protected ref: NbDialogRef<ModaldocumentospcComponent>,
    private toastService: ToastService,
  ) {
    this.idspc = this.activatedRoute.snapshot.params.idspc;
    this.idseguimientospc = this.activatedRoute.snapshot.params.idseguimientospc;
  }

  ngOnInit(): void {
    this.loadDataModal('null');
  }

  dblclick(event) {
    if (MouseGuard.isDoubleClick()) {
      if (event.data.tipo === false) {
        this.rutaPasada = event.data.url;
        this.loadDataModal(event.data.url);
      } else {
        if (event.data.tipo === true) {
          this.toastService.showAdvertencia('Es un archivo, no tiene contenido dentro');
        }
      }
    }

  }

  retroceder() {
    const ruta = this.rutaPasada.split('/');
    let nuevaRuta = '';
    if (ruta.length > 1) {
      for (let index = 0; index < ruta.length - 2; index++) {
        nuevaRuta += ruta[index] + '/';
      }
      if (nuevaRuta.split('/').length === 2) {
        nuevaRuta = 'null';
      }
      this.loadDataModal(nuevaRuta);
    }
  }

  loadDataModal(rutaBase: string) {
    this.seguimientospcService.listByModal(this.idseguimientospc, rutaBase).subscribe(
      (res: Seguimientospc[]) => {
        this.source.load(res);
      },
    );
  }
}
