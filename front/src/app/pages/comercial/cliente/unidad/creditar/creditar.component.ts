import { UnidadService } from './../../../../../@core/comercial/backend/services/unidad.service';
import { ResponseMensaje } from './../../../../../@core/sistema/interfaces/responseMensaje';
import { Unidad } from './../../../../../@core/comercial/interfaces/unidad';
import { takeUntil } from 'rxjs/operators';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Component, OnInit } from '@angular/core';

export enum UnidadFormMode {
  VIEW = 'View',
  EDIT = 'Editar',
  ADD = 'Nuevo',
}

@Component({
  selector: 'ngx-creditar',
  templateUrl: './creditar.component.html',
  styleUrls: ['./creditar.component.scss'],
})
export class CreditarComponent implements OnInit {

  loading = false;

  idcliente = 0;
  idunidad = 0;
  minLength: number = 4;
  maxLength: number = 100;
  unidadForm: FormGroup;

  protected readonly unsubscribe$ = new Subject<void>();

  get nombre() { return this.unidadForm.get('nombre'); }
  get descripcion() { return this.unidadForm.get('descripcion'); }

  mode: UnidadFormMode;

  setViewMode(viewMode: UnidadFormMode) {
    this.mode = viewMode;
  }


  constructor(private unidadService: UnidadService,
    private router: Router,
    private route: ActivatedRoute,
    private toastService: ToastService,
    private fb: FormBuilder,
  ) {
    this.idcliente = this.route.snapshot.params.idcliente;
    this.idunidad = this.route.snapshot.params.idunidad;
  }

  ngOnInit(): void {
    this.initUnidadForm();
    this.loadUnidadData();
  }

  initUnidadForm() {
    this.unidadForm = this.fb.group({
      idunidad: this.fb.control(''),
      idcliente: this.fb.control(''),
      nombre: this.fb.control('', [
        Validators.minLength(this.minLength),
        Validators.required]),
      descripcion: this.fb.control('', [
        Validators.minLength(this.minLength),
        Validators.required]),
    });
  }

  get canEdit(): boolean {
    return this.mode !== UnidadFormMode.VIEW;
  }

  loadUnidadData() {
    const idunidad = this.route.snapshot.paramMap.get('idunidad');
    if (idunidad) {
      this.setViewMode(UnidadFormMode.EDIT);
      this.loadUnidad(idunidad);
    } else {
      this.setViewMode(UnidadFormMode.ADD);
    }
  }

  loadUnidad(idunidad?) {
    const loadUnidad = this.unidadService.get(idunidad);
    loadUnidad.pipe(takeUntil(this.unsubscribe$)).subscribe((unidad) => {
      this.unidadForm.setValue({
        idunidad: unidad.idunidad ? unidad.idunidad : '',
        idcliente: unidad.idcliente ? unidad.idcliente : '',
        nombre: unidad.nombre ? unidad.nombre : '',
        descripcion: unidad.descripcion ? unidad.descripcion : '',
      });
      this.idcliente = unidad.idcliente;
    });
  }

  convertToUnidad(value: any): Unidad {
    const unidad: Unidad = value;
    unidad.idcliente = this.idcliente;
    return unidad;
  }

  save() {
    const unidad: Unidad = this.convertToUnidad(this.unidadForm.value);

    let observable = new Observable<ResponseMensaje>();
    observable = unidad.idunidad
      ? this.unidadService.update(unidad)
      : this.unidadService.create(unidad);

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
      this.stopLoading();
      this.toastService.showAdvertencia(res.mensaje);
    }
  }

  handleWrongResponse() {
    this.toastService.showError();
    this.stopLoading();
  }

  back() {
    this.router.navigate(['/pages/comercial/cliente/unidad/listar/' + this.idcliente]);
  }

  startLoading() {
    this.loading = true;
  }

  stopLoading() {
    this.loading = false;
  }
}
