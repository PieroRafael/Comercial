import { OtSelect } from './../../../../@core/comercial/interfaces/otSelect';
import { Ot } from './../../../../@core/comercial/interfaces/ot';
import { NbDialogRef } from '@nebular/theme';
import { ActivatedRoute } from '@angular/router';
import { OtService } from './../../../../@core/comercial/backend/services/ot.service';
import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'ngx-modaldetalle',
  templateUrl: './modaldetalle.component.html',
  styleUrls: ['./modaldetalle.component.scss'],
})
export class ModaldetalleComponent implements OnInit {

  @Input() idot: number;
  @Input() dcodigo: string;
  @Input() dfecha: Date;
  @Input() dspc: string;
  @Input() dtipo: string;
  @Input() dot: string;
  otSelect: OtSelect[];
  adicionalSelect: OtSelect[];

  constructor(private otService: OtService,
    private activatedRoute: ActivatedRoute,
    protected ref: NbDialogRef<ModaldetalleComponent>) {
    this.idot = this.activatedRoute.snapshot.params.idot;
  }

  ngOnInit(): void {
    this.otService.get(this.idot).subscribe((res: Ot) => {
      this.dcodigo = res.codigo;
      this.dspc = res.proyecto;
      this.dfecha = res.fcreate;
      if (res.tipoproyecto === true) {
        this.dtipo = 'OT Adicional';
      } else {
        this.dtipo = 'OT Principal';
      }
      if (res.otprincipal === null) {
        this.dot = 'Es una OT principal';
      } else {
        this.otService.listSelect().subscribe((otSelect: OtSelect[]) => {
          this.dot = otSelect.find(ot =>
            ot.idot === res.otprincipal,
          ).codigo;
        });
      }
    });
  }
}
