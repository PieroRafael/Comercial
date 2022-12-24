import { Seguimientospc } from './../../../../../@core/comercial/interfaces/seguimientospc';
import { SeguimientospcService } from './../../../../../@core/comercial/backend/services/seguimientospc.service';
import { ResponseMensaje } from './../../../../../@core/sistema/interfaces/responseMensaje';
import { takeUntil } from 'rxjs/operators';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, Input, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Observable, Subject } from 'rxjs';

export enum SeguimientospcFormMode {
  VIEW = 'View',
  EDIT = 'Editar',
  ADD = 'Nuevo',
}

@Component({
  selector: 'ngx-createspcdoc',
  templateUrl: './creditar.component.html',
  styleUrls: ['./creditar.component.scss'],
})

export class CreditarComponent implements OnInit {

  loading = false;

  @Input() documentosearch: string;
  documentoFile: FileList = null;
  idseguimientospc = 0;
  idspc = 0;
  proyecto: string;
  minLength: number = 4;
  maxLength: number = 30;
  minCellLength: number = 9;
  maxCellLength: number = 9;
  minRUCLength: number = 11;
  maxRUCLength: number = 11;
  seguimientospcForm: FormGroup;
  listFolder: String[] = [];
  n = new Date();
  fechaactual = this.n.getDate() + '-' + (this.n.getMonth() + 1) + '-' + this.n.getFullYear();

  get seguimientospc() { return this.seguimientospcForm.get('seguimientospc'); }
  get descripcion() { return this.seguimientospcForm.get('descripcion'); }
  get observacion() { return this.seguimientospcForm.get('observacion'); }
  get formarecepcion() { return this.seguimientospcForm.get('formarecepcion'); }
  get file() { return this.seguimientospcForm.get('file'); }

  protected readonly unsubscribe$ = new Subject<void>();

  mode: SeguimientospcFormMode;

  setViewMode(viewMode: SeguimientospcFormMode) {
    this.mode = viewMode;
  }

  constructor(private seguimientospcService: SeguimientospcService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastService: ToastService,
    private fb: FormBuilder,
  ) {
    this.idseguimientospc = this.activatedRoute.snapshot.params.idseguimientospc;
    this.idspc = this.activatedRoute.snapshot.params.idspc;
    this.proyecto = this.activatedRoute.snapshot.params.proyecto;
    this.documentosearch = this.proyecto;
  }

  handleDocumento(files: FileList) {
    this.documentoFile = files;
    this.getDirectory(this.documentoFile);
  }

  ngOnInit(): void {
    this.initseguimientospcForm();
    this.loadSeguimientospcData();
  }

  initseguimientospcForm() {
    this.seguimientospcForm = this.fb.group({
      idseguimientospc: this.fb.control(''),
      idspc: this.fb.control(''),
      descripcion: this.fb.control('', [
        Validators.minLength(this.minLength),
        Validators.required]),
      observacion: this.fb.control('', [
        Validators.minLength(this.minLength),
        Validators.required]),
      formarecepcion: this.fb.control('', [
        Validators.required]),
      file: this.fb.control(''),
    });
  }

  get canEdit(): boolean {
    return this.mode !== SeguimientospcFormMode.VIEW;
  }

  loadSeguimientospcData() {
    const idseguimientospc = this.activatedRoute.snapshot.paramMap.get('idseguimientospc');
    if (idseguimientospc) {
      this.setViewMode(SeguimientospcFormMode.EDIT);
      this.loadSeguimientospc(idseguimientospc);
      this.seguimientospcForm.get('file').disable();
      this.seguimientospcForm.get('file').updateValueAndValidity();
    } else {
      this.setViewMode(SeguimientospcFormMode.ADD);
      this.seguimientospcForm.get('file').enable();
      this.seguimientospcForm.get('file').setValidators([
        Validators.required,
      ]);
      this.seguimientospcForm.get('file').updateValueAndValidity();
    }
  }

  loadSeguimientospc(idseguimientospc?) {
    const loadSeguimientospc = this.seguimientospcService.get(idseguimientospc);
    loadSeguimientospc.pipe(takeUntil(this.unsubscribe$)).subscribe((documento) => {
      this.seguimientospcForm.setValue({
        idseguimientospc: documento.idseguimientospc ? documento.idseguimientospc : '',
        idspc: documento.idspc ? documento.idspc : '',
        formarecepcion: documento.formarecepcion ? documento.formarecepcion : '',
        descripcion: documento.descripcion ? documento.descripcion : '',
        observacion: documento.observacion ? documento.observacion : '',
        file: null,
      });
      this.idspc = documento.idspc;
    });
  }

  convertToSeguimientoSpc(value: any): Seguimientospc {
    const seguimientospc: Seguimientospc = value;
    seguimientospc.listFile = this.documentoFile;
    seguimientospc.listFolder = this.listFolder;
    seguimientospc.idspc = this.idspc;
    seguimientospc.idseguimientospc = this.idseguimientospc;
    return seguimientospc;
  }

  getDirectory(event) {
    const files = event.target.files;

    for (let i = 0, f; f = files[i]; i++) {
      this.listFolder.push(files[i].webkitRelativePath);
    }

    this.documentoFile = files;
  }

  save() {
    const seguimientospc: Seguimientospc = this.convertToSeguimientoSpc(this.seguimientospcForm.value);
    let observable = new Observable<ResponseMensaje>();

    observable = seguimientospc.idseguimientospc
      ? this.seguimientospcService.update(seguimientospc)
      : this.seguimientospcService.create(seguimientospc);
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

  convertToSeguimientospc(value: any): Seguimientospc {
    const seguimiento: Seguimientospc = value;
    seguimiento.listFile = this.documentoFile;
    return seguimiento;
  }

  handleWrongResponse() {
    this.toastService.showError();
    this.stopLoading();
  }

  back() {
    this.router.navigate(['/pages/comercial/spc/documentospc/listar/' + this.idspc]);
  }

  startLoading() {
    this.loading = true;
  }

  stopLoading() {
    this.loading = false;
  }
}
