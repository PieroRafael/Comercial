import { SpcSelect } from './../../../../@core/comercial/interfaces/spcselect';
import { SpcService } from './../../../../@core/comercial/backend/services/spc.service';
import { ResponseMensaje } from './../../../../@core/sistema/interfaces/responseMensaje';
import { Ot } from './../../../../@core/comercial/interfaces/ot';
import { takeUntil } from 'rxjs/operators';
import { ToastService } from './../../../../@core/sistema/utils/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { OtService } from './../../../../@core/comercial/backend/services/ot.service';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject } from 'rxjs';
import { OtSelect } from '../../../../@core/comercial/interfaces/otSelect';

export enum OtFormMode {
  VIEW = 'View',
  EDIT = 'Editar',
  ADD = 'Nuevo',
}

@Component({
  selector: 'ngx-create',
  templateUrl: './creditar.component.html',
  styleUrls: ['./creditar.component.scss'],
})

export class CreditarComponent implements OnInit {

  loading = false;
  idot = 0;
  idspc = 0;
  documentoFile: FileList = null;
  minLength: number = 7;
  maxLength: number = 7;
  minCellLength: number = 9;
  maxCellLength: number = 9;
  minRUCLength: number = 11;
  maxRUCLength: number = 11;
  requeridofechac = false;
  requeridofechae = false;
  otForm: FormGroup;
  spcSelect: SpcSelect[];
  otSelect: OtSelect[];
  tipo: boolean = false;
  requeridoSelect = false;
  nombreread = true;
  ubicacionread = true;
  clienteread = true;

  nombrenombre: string;

  hoy = new Date();
  @Input() fechahoy = this.hoy.getDate() + '-' + (this.hoy.getMonth() + 1) + '-' + this.hoy.getFullYear();

  get codigo() { return this.otForm.get('codigo'); }
  get fechaaprobacion() { return this.otForm.get('fechaaprobacion'); }
  get codigospc() { return this.otForm.get('codigospc'); }
  get tipoproyecto() { return this.otForm.get('tipoproyecto'); }
  get otprincipal() { return this.otForm.get('otprincipal'); }
  get nombreproyecto() { return this.otForm.get('nombreproyecto'); }
  get ubicacion() { return this.otForm.get('ubicacion'); }
  get cliente() { return this.otForm.get('cliente'); }
  get file() { return this.otForm.get('file'); }

  protected readonly unsubscribe$ = new Subject<void>();

  mode: OtFormMode;

  setViewMode(viewMode: OtFormMode) {
    this.mode = viewMode;
  }

  constructor(private otService: OtService,
    private spcService: SpcService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastService: ToastService,
    private fb: FormBuilder,
  ) {
    this.idspc = this.activatedRoute.snapshot.params.idspc;
    this.spcService.listSelect().subscribe((res: SpcSelect[]) => {
      this.spcSelect = res;
    });

    this.otService.listSelect().subscribe((res: OtSelect[]) => {
      this.otSelect = res;
    });
  }

  ngOnInit(): void {
    this.initotForm();
    this.loadOtData();
  }

  initotForm() {
    this.otForm = this.fb.group({
      idot: this.fb.control(''),
      idspc: this.fb.control(''),
      codigo: this.fb.control('', [
        Validators.minLength(this.minLength),
        Validators.maxLength(this.maxLength),
        Validators.required]),
      fechaaprobacion: this.fb.control(''),
      codigospc: this.fb.control('', [
        Validators.required]),
      tipoproyecto: this.fb.control(false),
      otprincipal: this.fb.control(''),
      nombreproyecto: this.fb.control(''),
      ubicacion: this.fb.control(''),
      cliente: this.fb.control(''),
      file: this.fb.control('', [
        Validators.required]),
    });
  }

  onActive() {
    this.tipo;
    if (this.tipo === true) {
      this.requeridoSelect = true;
      this.otForm.get('otprincipal').enable();
      this.otForm.get('otprincipal').setValidators([
        Validators.required,
      ]);
      this.otForm.get('otprincipal').updateValueAndValidity();
    } else {
      this.requeridoSelect = false;
      this.otForm.get('otprincipal').setValue('');
      this.otForm.get('otprincipal').clearValidators();
      this.otForm.get('otprincipal').updateValueAndValidity();
      this.otForm.get('otprincipal').disable();
    }
  }

  validar() {
    if (this.codigo.value === '') {
      this.toastService.showAdvertencia('No se puede elegir unidad sin seleccionar cliente');
      this.otForm.get('unidad').setValue('');
      return false;
    }
  }

  get canEdit(): boolean {
    return this.mode !== OtFormMode.VIEW;
  }

  toggle(tipo: boolean) {
    this.tipo = tipo;
  }

  loadOtData() {
    const idot = this.activatedRoute.snapshot.paramMap.get('idot');
    if (idot) {
      this.setViewMode(OtFormMode.EDIT);
      this.loadOt(idot);
      this.otForm.get('fechaaprobacion').disable();
      this.otForm.get('fechaaprobacion').updateValueAndValidity();
      this.otForm.get('file').setValue('');
      this.otForm.get('file').clearValidators();
      this.otForm.get('file').updateValueAndValidity();
      this.otForm.get('file').disable();
      this.requeridofechac = false;
      this.requeridofechae = true;
    } else {
      this.setViewMode(OtFormMode.ADD);
      this.otForm.get('fechaaprobacion').disable();
      this.otForm.get('fechaaprobacion').updateValueAndValidity();
      this.requeridofechac = true;
      this.requeridofechae = false;
    }
  }

  activar() {
    this.idspc = this.spcSelect.find(spc =>
      spc.codigo === this.codigospc.value).idspc;
    const loadOt = this.spcService.get(this.idspc);
    loadOt.subscribe((ot) => {
      if (this.codigospc.value) {
        this.otForm.setValue({
          idot: ot.idot ? ot.idot : '',
          idspc: ot.idspc ? ot.idspc : '',
          codigo: this.otForm.get('codigo').value,
          codigospc: ot.codigo ? ot.codigo : '',
          fechaaprobacion: '',
          tipoproyecto: this.otForm.get('tipoproyecto').value,
          otprincipal: this.otForm.get('otprincipal').value,
          nombreproyecto: ot.proyecto,
          ubicacion: ot.ubicacion,
          cliente: ot.razonsocial,
          file: null,
        });
      }
    });
  }

  nombreArchivo(name: String) {
    return name.substring(0, name.lastIndexOf('.'));
  }

  tildesArchivo = (name) => {
    return name.normalize('NFD').replace(/[\u0300-\u036f]/g, '');
  }

  quitarAcentos(cadena) {
    const acentos = {
      'á': 'a', 'é': 'e', 'í': 'i', 'ó': 'o', 'ú': 'u', 'Á': 'A', 'É': 'E',
      'Í': 'I', 'Ó': 'O', 'Ú': 'U',
    };
    return cadena.split('').map(letra => acentos[letra] || letra).join('').toString();
  }

  /*compararCadena(nombreRecibido){
      var regex = new RegExp("^"+nombreRecibido+"$","gi")
      for(var i=0; i<this.arrayDiscos.length; i++){
          var nombre = this.arrayDiscos[i].nombre;
          if(nombre.match(regex))
              return this.arrayDiscos[i];
      }
      return false;
  }*/

  validarFile(files: FileList) {
    const archivos = ['ofertatecnica', 'ofertaeconomica', 'cronograma', 'presupuesto'];
    let nombrearchivo = '';

    if (files.length > 3 && files.length < 5) {
      for (let i = 0; i < files.length; i++) {
        let count = 0;
        const element = this.nombreArchivo(files[i].name);
        for (let k = 0; k < archivos.length; k++) {
          if (element === archivos[k]) {
            count = 1;
          }
        }
        if (count === 0) {
          nombrearchivo += element;
        }
      }
      if (nombrearchivo !== '') {
        this.otForm.get('file').setValue('');
        this.toastService.showAdvertencia('El o los nombres del archivo son incorrectos ' + ' ' + nombrearchivo);
      }
    } else {
      this.otForm.get('file').setValue('');
      this.toastService.showAdvertencia('Necesita ingresar 4 archivos');
    }
  }

  handleDocumento(files: FileList) {
    this.documentoFile = files;
    this.validarFile(files);
  }

  loadOt(idot) {
    const loadOt = this.otService.get(idot);
    loadOt.subscribe((ot) => {
      if (ot.tipoproyecto === false) {
        this.otForm.get('otprincipal').setValue('');
        this.otForm.get('otprincipal').clearValidators();
        this.otForm.get('otprincipal').updateValueAndValidity();
        this.otForm.get('otprincipal').disable();
        this.requeridoSelect = false;
      } else {
        this.requeridoSelect = true;
        this.otForm.get('otprincipal').enable();
        this.otForm.get('otprincipal').setValidators([
          Validators.required,
        ]);
      }
      this.tipo = ot.tipoproyecto;
      this.otForm.setValue({
        idot: ot.idot ? ot.idot : '',
        idspc: ot.idspc ? ot.idspc : '',
        codigo: ot.codigo ? ot.codigo : '',
        codigospc: ot.codigospc ? ot.codigospc : '',
        fechaaprobacion: ot.fcreate ? ot.fcreate : '',
        tipoproyecto: ot.tipoproyecto ? ot.tipoproyecto : '',
        otprincipal: ot.otprincipal ? ot.otprincipal : '',
        nombreproyecto: ot.nombreproyecto ? ot.nombreproyecto : '',
        ubicacion: ot.ubicacion ? ot.ubicacion : '',
        cliente: ot.cliente ? ot.cliente : '',
        file: null,
      });
      this.idspc = ot.idspc;
    });
  }

  convertToOt(value: any): Ot {
    const ot: Ot = value;
    ot.listFile = this.documentoFile;
    ot.idspc = this.spcSelect.find(spc =>
      spc.codigo === this.codigospc.value).idspc;
    return ot;
  }

  save() {

    const ot: Ot = this.convertToOt(this.otForm.value);
    let observable = new Observable<ResponseMensaje>();

    this.loading = true;
    observable = ot.idot
      ? this.otService.update(ot)
      : this.otService.create(ot);

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

  handleWrongResponse() {
    this.toastService.showError();
    this.stopLoading();
  }

  back() {
    this.router.navigate(['/pages/comercial/ot/listar']);
  }

  startLoading() {
    this.loading = true;
  }

  stopLoading() {
    this.loading = false;
  }
}
