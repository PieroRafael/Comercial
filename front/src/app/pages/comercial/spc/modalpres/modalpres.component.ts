import { Presupuesto } from './../../../../@core/comercial/interfaces/presupuesto';
import { MouseGuard } from './../documentospc/modaldocumentospc/MouseGuard';
import { ToastService } from './../../../../@core/sistema/utils/toast.service';
import { LocalDataSource } from 'ng2-smart-table';
import { NbDialogRef } from '@nebular/theme';
import { ActivatedRoute } from '@angular/router';
import { SpcService } from './../../../../@core/comercial/backend/services/spc.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ngx-modalpres',
  templateUrl: './modalpres.component.html',
  styleUrls: ['./modalpres.component.scss']
})
export class ModalpresComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource();
  @Input() idspc: number;
  iddocumentopres = 0;
  tipo = 0;
  rutaPasada = 'null';

  settings = {
    hideSubHeader: true,
    actions:{
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
      tipopres: {
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
        title: 'Teléfono',
        type: 'string',
      },
    },
  };

  constructor(private spcService: SpcService,
    private activatedRoute: ActivatedRoute,
    protected ref: NbDialogRef<ModalpresComponent>,
    private toastService: ToastService,
  ) { 
    this.idspc = this.activatedRoute.snapshot.params.idspc;
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
    this.spcService.listByModalPres(this.idspc, rutaBase).subscribe(
      (res: Presupuesto[]) => {
        this.source.load(res);
      },
    );
  }

}
