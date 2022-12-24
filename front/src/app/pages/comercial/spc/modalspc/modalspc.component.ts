import { Contacto } from './../../../../@core/comercial/interfaces/contacto';
import { ActivatedRoute } from '@angular/router';
import { LocalDataSource } from 'ng2-smart-table';
import { Component, OnInit, Input } from '@angular/core';
import { NbDialogRef } from '@nebular/theme';
import { ContactoService } from '../../../../@core/comercial/backend/services/contacto.service';

@Component({
  selector: 'ngx-modalspc',
  templateUrl: './modalspc.component.html',
  styleUrls: ['./modalspc.component.scss'],
})
export class ModalspcComponent implements OnInit {

  source: LocalDataSource = new LocalDataSource();
  idcontacto = 0;
  @Input() idcliente: number;
  contacto = [];
  presupuesto: Contacto[];
  consultatecnica: Contacto[];
  contactovisita: Contacto[];
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
    },
  };

  constructor(private contactoService: ContactoService,
    private activatedRoute: ActivatedRoute,
    protected ref: NbDialogRef<ModalspcComponent>,
  ) {
    this.idcliente = this.activatedRoute.snapshot.params.idcliente;
  }

  ngOnInit(): void {
    this.loadData();
  }

  save() {
    this.ref.close(this.contacto);
  }

  cleanContacto(idcontacto) {
    const i = this.contacto.findIndex(function (contacto) {
      return contacto.idcontacto === idcontacto;
    });
    if (i !== -1) {
      this.contacto.splice(i, 1);
    }
  }

  cancelar() {
    this.contacto = [];
    this.ref.close();
  }

  onRowSelect(event): void {
    this.contacto.push(event.data);
  }
  loadData() {
    this.contactoService.listByIdCliente(this.idcliente).subscribe((res: Contacto[]) => {
      this.source.load(res);
    });
  }
}
