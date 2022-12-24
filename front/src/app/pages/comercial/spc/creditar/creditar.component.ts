import { VendedorSelect } from './../../../../@core/comercial/interfaces/vendedorSelect';
import { Contacto } from './../../../../@core/comercial/interfaces/contacto';
import { NbDateService, NbDialogService } from '@nebular/theme';
import { ClienteService } from './../../../../@core/comercial/backend/services/cliente.service';
import { ClienteSelect } from './../../../../@core/comercial/interfaces/clienteSelect';
import { ResponseMensaje } from './../../../../@core/sistema/interfaces/responseMensaje';
import { Spc } from './../../../../@core/comercial/interfaces/spc';
import { takeUntil, map } from 'rxjs/operators';
import { ToastService } from './../../../../@core/sistema/utils/toast.service';
import { SpcService } from './../../../../@core/comercial/backend/services/spc.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { LocalDataSource } from 'ng2-smart-table';
import { Subject, Observable, of } from 'rxjs';
import { ModalspcComponent } from '../modalspc/modalspc.component';

export enum SpcFormMode {
  VIEW = 'View',
  EDIT = 'Editar',
  ADD = 'Nuevo',
}

@Component({
  selector: 'ngx-crear-clientes',
  templateUrl: './creditar.component.html',
  styleUrls: ['./creditar.component.scss'],
})

export class CreditarComponent implements OnInit {

  loading = false;

  @Input() spc: Date;
  source: LocalDataSource = new LocalDataSource();
  minLength: number = 7;
  maxLength: number = 7;
  minCellLength: number = 9;
  maxCellLength: number = 9;
  minCodigoLength: number = 7;
  maxCodigoLength: number = 7;
  idcontacto = 0;
  idcliente = 0;
  idspc = 0;
  fecharead = true;
  codigoread = false;
  proyectoread = false;
  requeridofechac = false;
  requeridofechae = false;
  spcForm: FormGroup;
  clienteSelect: ClienteSelect[];
  vendedorSelect: VendedorSelect[];
  filteredOptions$: Observable<ClienteSelect[]>;
  representante = [];
  consulta: Contacto[] = [];
  tecnica: Contacto[] = [];
  visita: Contacto[] = [];
  minfecha: Date;
  maxfecha: Date;
  @ViewChild('autoInput') input;

  hoy = new Date();
  @Input() fechahoy = this.hoy.getDate() + '-' + (this.hoy.getMonth() + 1) + '-' + this.hoy.getFullYear();

  protected readonly unsubscribe$ = new Subject<void>();

  receptor: LocalDataSource = new LocalDataSource();
  consultor: LocalDataSource = new LocalDataSource();
  visitam: LocalDataSource = new LocalDataSource();
  representantecomercial: LocalDataSource = new LocalDataSource();

  settings_receptor = {
    hideSubHeader: true,
    actions: false,
    columns: {
      nombre: {
        title: 'Nombre',
      },
      correo: {
        title: 'Correo',
      },
      telefono: {
        title: 'Teléfono',
      },
    },
  };

  settings_consultor = {
    hideSubHeader: true,
    actions: false,
    columns: {
      nombre: {
        title: 'Nombre',
      },
      correo: {
        title: 'Correo',
      },
      telefono: {
        title: 'Teléfono',
      },
    },
  };

  settings_visita = {
    hideSubHeader: true,
    actions: false,
    columns: {
      nombre: {
        title: 'Nombre',
      },
      correo: {
        title: 'Correo',
      },
      telefono: {
        title: 'Teléfono',
      },
    },
  };

  get razonsocial() { return this.spcForm.get('razonsocial'); }
  get codigo() { return this.spcForm.get('codigo'); }
  get fechaenviodeconsulta() { return this.spcForm.get('fechaenviodeconsulta'); }
  get fechaabsolucion() { return this.spcForm.get('fechaabsolucion'); }
  get fechaentrega() { return this.spcForm.get('fechaentrega'); }
  get fechavisitatecnica() { return this.spcForm.get('fechavisitatecnica'); }
  get fechareunion() { return this.spcForm.get('fechareunion'); }
  get fecha() { return this.spcForm.get('fecha'); }
  get ubicacion() { return this.spcForm.get('ubicacion'); }
  get cliente() { return this.spcForm.get('cliente'); }
  get proyecto() { return this.spcForm.get('proyecto'); }
  get vendedor() { return this.spcForm.get('vendedor'); }
  get tipo() { return this.spcForm.get('tipo'); }
  get consultau() { return this.spcForm.get('consulta'); }
  get tecnicau() { return this.spcForm.get('tecnica'); }
  get visitau() { return this.spcForm.get('visita'); }

  mode: SpcFormMode;

  setViewMode(viewMode: SpcFormMode) {
    this.mode = viewMode;
  }

  constructor(private spcService: SpcService,
    protected dateService: NbDateService<Date>,
    private dialogService: NbDialogService,
    private clienteService: ClienteService,
    private router: Router,
    private activatedRout: ActivatedRoute,
    private toastService: ToastService,
    private fb: FormBuilder,

  ) {
    this.minfecha = this.dateService.addDay(this.dateService.today(), +0);
    this.maxfecha = this.dateService.addDay(this.dateService.today(), +69);

    this.clienteService.listSelect().subscribe((res: ClienteSelect[]) => {
      this.clienteSelect = res;
    });

    this.spcService.listVendedor().subscribe((res: VendedorSelect[]) => {
      this.vendedorSelect = res;
    });
  }

  ngOnInit(): void {
    this.initspcForm();
    this.loadSpcData();
  }

  initspcForm() {
    this.spcForm = this.fb.group({
      idspc: this.fb.control(''),
      codigo: this.fb.control('', [
        Validators.minLength(this.minLength),
        Validators.maxLength(this.maxLength),
        Validators.required]),
      razonsocial: this.fb.control('', [
        Validators.required]),
      fecha: this.fb.control(''),
      proyecto: this.fb.control('', [
        Validators.required]),
      vendedor: this.fb.control('', [
        Validators.required]),
      tipo: this.fb.control('', [
        Validators.required]),
      fechaenviodeconsulta: this.fb.control('', [
        Validators.required]),
      fechaabsolucion: this.fb.control('', [
        Validators.required]),
      fechaentrega: this.fb.control('', [
        Validators.required]),
      fechareunion: this.fb.control('', [
        Validators.required]),
      fechavisitatecnica: this.fb.control('', [
        Validators.required]),
      ubicacion: this.fb.control('', [
        Validators.required]),
    });
  }

  get canEdit(): boolean {
    return this.mode !== SpcFormMode.VIEW;
  }

  loadSpcData() {
    const idspc = this.activatedRout.snapshot.paramMap.get('idspc');
    if (idspc) {
      this.setViewMode(SpcFormMode.EDIT);
      this.loadSpc(idspc);
      this.codigoread = true;
      this.proyectoread = true;
      this.requeridofechac = false;
      this.requeridofechae = true;
    } else {
      this.setViewMode(SpcFormMode.ADD);
      this.requeridofechac = true;
      this.requeridofechae = false;
    }
  }

  loadSpc(idspc?) {
    const loadspc = this.spcService.get(idspc);
    loadspc.pipe(takeUntil(this.unsubscribe$)).subscribe((spc) => {
      this.spcForm.setValue({
        idspc: spc.idspc ? spc.idspc : '',
        razonsocial: spc.razonsocial ? spc.razonsocial : '',
        vendedor: spc.vendedor ? spc.vendedor : '',
        codigo: spc.codigo ? spc.codigo : '',
        proyecto: spc.proyecto ? spc.proyecto : '',
        tipo: spc.tipo ? spc.tipo : '',
        fecha: spc.fcreate ? spc.fcreate : '',
        fechaenviodeconsulta: spc.fechaenviodeconsulta ?
          new Date(Date.parse(spc.fechaenviodeconsulta.toString()) + 18000000) : '',
        fechaabsolucion: spc.fechaabsolucion ?
          new Date(Date.parse(spc.fechaabsolucion.toString()) + 18000000) : '',
        fechaentrega: spc.fechaentrega ?
          new Date(Date.parse(spc.fechaentrega.toString()) + 18000000) : '',
        fechareunion: spc.fechareunion ?
          new Date(Date.parse(spc.fechareunion.toString()) + 18000000) : '',
        fechavisitatecnica: spc.fechavisitatecnica ?
          new Date(Date.parse(spc.fechavisitatecnica.toString()) + 18000000) : '',
        ubicacion: spc.ubicacion ? spc.ubicacion : '',
      });
      this.consulta = spc.consulta;
      this.tecnica = spc.tecnica;
      this.visita = spc.visita;
      this.receptor.load(this.consulta);
      this.consultor.load(this.tecnica);
      this.visitam.load(this.visita);
    });
  }

  convertToSpc(value: any): Spc {
    const spc: Spc = value;
    spc.idcliente = this.clienteSelect.find(cliente =>
      cliente.razonsocial === this.razonsocial.value).idcliente;
    return spc;
  }

  accion(evento) {
    this.dialogService.open(ModalspcComponent, {
      context: {
        idcliente: this.clienteSelect.find(cliente =>
          cliente.razonsocial === this.razonsocial.value).idcliente,
      },
    }).onClose.subscribe(res => {
      this.clienteSelect = res;
      if (res !== undefined) {
        if (evento === 'openModalpresupuesto') {
          if (this.consulta.length === 0) {
            this.consulta.push(res[0]);
          } else {
            this.consulta.forEach((data, index) => {
              if (data.idcontacto === res[0].idcontacto) {
                this.toastService.showAdvertencia('No se puede registrar el mismo contacto');
              }
            });
          }
          this.receptor.load(this.consulta);
        }
        if (evento === 'openModalconsultas') {
          if (this.tecnica.length === 0) {
            this.tecnica.push(res[0]);
          } else {
            this.tecnica.forEach((data, index) => {
              if (data.idcontacto === res[0].idcontacto) {
                this.toastService.showAdvertencia('No se puede registrar el mismo contacto');
              }
            });
          }
          this.consultor.load(this.tecnica);
        }
        if (evento === 'openModalcontacto') {
          if (this.visita.length === 0) {
            this.visita.push(res[0]);
          } else {
            this.visita.forEach((data, index) => {
              if (data.idcontacto === res[0].idcontacto) {
                this.toastService.showAdvertencia('No se puede registrar el mismo contacto');
              }
            });
          }
          this.visitam.load(this.visita);
        }
      }
    });
  }

  onRowTable(event): void {
    this.idcontacto = event.data.idcontacto;
  }

  eliminarTable(tabla) {
    if (tabla === 'consulta') {
      this.consulta.forEach((data, index) => {
        if (data.idcontacto === this.idcontacto) {
          this.consulta.splice(index, 1);
        }
      });
      this.receptor.load(this.consulta);
    }

    if (tabla === 'tecnica') {
      this.tecnica.forEach((data, index) => {
        if (data.idcontacto === this.idcontacto) {
          this.tecnica.splice(index, 1);
        }
      });
      this.consultor.load(this.tecnica);
    }

    if (tabla === 'visita') {
      this.visita.forEach((data, index) => {
        if (data.idcontacto === this.idcontacto) {
          this.visita.splice(index, 1);
        }
      });
      this.visitam.load(this.visita);
    }


  }

  save() {
    const spc: Spc = this.convertToSpc(this.spcForm.value);
    spc.consulta = this.consulta;
    spc.tecnica = this.tecnica;
    spc.visita = this.visita;
    let observable = new Observable<ResponseMensaje>();
    observable = spc.idspc

      ? this.spcService.update(spc)
      : this.spcService.create(spc);

    observable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(res => {
        this.handleSuccessResponse(res);
      },
        err => {
          this.handleWrongResponse();
        });
  }

  handleSuccessResponse(res) {
    if (res.codigo === 200) {

      this.toastService.showCorrecto(res.mensaje);
      this.stopLoading();
      this.back();

    } else {

      this.toastService.showAdvertencia(res.mensaje);
      this.stopLoading();
    }
  }

  onRowSelectId(event): void {
    this.idcontacto = event.data.idcontacto;
  }

  handleWrongResponse() {
    this.toastService.showError();
    this.stopLoading();
  }

  back() {
    this.router.navigate(['/pages/comercial/spc/listar']);
  }

  onChange() {
    this.filteredOptions$ = this.getFilteredOptions(this.input.nativeElement.value);
  }

  onSelectionChange($event) {
    this.filteredOptions$ = this.getFilteredOptions($event);
  }

  getFilteredOptions(value: string): Observable<ClienteSelect[]> {
    if (value !== '') {
      return of(value).pipe(
        map(filterString => this.filter(filterString)),
      );
    }
  }

  private filter(value: string): ClienteSelect[] {
    const filterValue = value.toLowerCase();
    return this.clienteSelect.filter(optionValue => optionValue.razonsocial.toLowerCase().includes(filterValue));
  }

  startLoading() {
    this.loading = true;
  }

  stopLoading() {
    this.loading = false;
  }

  onCreateConfirmreceptor(event) {

  }

  onDeleteConfirmreceptor(event) {

  }

  onSaveConfirmreceptor(event) {

  }

  onCreateConfirmconsultor(event) {

  }

  onDeleteConfirmconsultor(event) {

  }

  onSaveConfirmconsultor(event) {

  }

  onCreateConfirmvisita(event) {

  }

  onDeleteConfirmvisita(event) {

  }

  onSaveConfirmvisita(event) {

  }

  onCreateConfirmrepresentantecomercial(event) {

  }

  onDeleteConfirmrepresentantecomercial(event) {

  }

  onSaveConfirmrepresentantecomercial(event) {

  }
}
