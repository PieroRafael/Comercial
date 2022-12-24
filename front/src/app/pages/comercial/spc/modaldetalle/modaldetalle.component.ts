import { Contacto } from './../../../../@core/comercial/interfaces/contacto';
import { ActivatedRoute } from '@angular/router';
import { Spc } from './../../../../@core/comercial/interfaces/spc';
import { NbDialogRef } from '@nebular/theme';
import { SpcService } from './../../../../@core/comercial/backend/services/spc.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ngx-modaldetalle',
  templateUrl: './modaldetalle.component.html',
  styleUrls: ['./modaldetalle.component.scss'],
})
export class ModaldetalleComponent implements OnInit {

  @Input() idspc: number;
  @Input() dcodigo: string;
  @Input() dcliente: string;
  @Input() dproyecto: string;
  @Input() dvendedor: string;
  @Input() dtipo: string;
  @Input() dubicacion: string;
  @Input() dfechaentrada: Date;
  @Input() dfechaabsolucion: Date;
  @Input() dfechaentrega: Date;
  @Input() dfechavisitatecnica: Date;
  @Input() dfechareunion: Date;
  @Input() dfechaenvioconsulta: Date;
  @Input() dconsulta: Contacto[] = [];
  @Input() dtecnica: Contacto[] = [];
  @Input() dvisita: Contacto[] = [];

  constructor(private spcService: SpcService,
    private activatedRoute: ActivatedRoute,
    protected ref: NbDialogRef<ModaldetalleComponent>,
  ) {
    this.idspc = this.activatedRoute.snapshot.params.idspc;
  }

  ngOnInit(): void {
    this.spcService.get(this.idspc).subscribe((res: Spc) => {
      this.dcliente = res.razonsocial;
      this.dcodigo = res.codigo;
      this.dproyecto = res.proyecto;
      this.dtipo = res.tipo;
      this.dvendedor = res.vendedor;
      this.dubicacion = res.ubicacion;
      this.dfechaentrada = res.fcreate;
      this.dfechaentrega = res.fechaentrega;
      this.dfechaabsolucion = res.fechaabsolucion;
      this.dfechaenvioconsulta = res.fechaenviodeconsulta;
      this.dfechavisitatecnica = res.fechavisitatecnica;
      this.dfechareunion = res.fechareunion;
      res.visita.forEach((valor, index) => {
        this.dvisita.push(valor);
      });

      res.tecnica.forEach((valor, index) => {
        this.dtecnica.push(valor);
      });

      res.consulta.forEach((valor, index) => {
        this.dconsulta.push(valor);
      });
    });
  }
}
