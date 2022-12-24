import { ResponseMensaje } from './../../../../@core/sistema/interfaces/responseMensaje';
import { ToastService } from './../../../../@core/sistema/utils/toast.service';
import { Component, OnInit, OnDestroy } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Cliente, ClienteData } from '../../../../@core/comercial/interfaces/cliente';
import { NUMBERS_PATTERN } from '../../../../@auth/components';

export enum ClienteFormMode {
  VIEW = 'View',
  EDIT = 'Editar',
  ADD = 'Nuevo',
}


@Component({
  selector: 'ngx-crear-clientes',
  templateUrl: './creditar.component.html',
  styleUrls: ['./creditar.component.scss'],
})

export class CreditarComponent implements OnInit, OnDestroy {

  loading = false;

  minLength: number = 4;
  maxLength: number = 100;
  minCellLength: number = 9;
  maxCellLength: number = 9;
  minRUCLength: number = 11;
  maxRUCLength: number = 11;
  clienteForm: FormGroup;

  protected readonly unsubscribe$ = new Subject<void>();

  get razonsocial() { return this.clienteForm.get('razonsocial'); }
  get ruc() { return this.clienteForm.get('ruc'); }
  get direccion() { return this.clienteForm.get('direccion'); }
  get telefono() { return this.clienteForm.get('telefono'); }
  get paginaweb() { return this.clienteForm.get('paginaweb'); }

  mode: ClienteFormMode;

  setViewMode(viewMode: ClienteFormMode) {
    this.mode = viewMode;
  }


  constructor(private clienteService: ClienteData,
    private router: Router,
    private route: ActivatedRoute,
    private toastService: ToastService,
    private fb: FormBuilder) {
  }

  ngOnInit(): void {
    this.initClienteForm();
    this.loadClienteData();
  }

  initClienteForm() {
    this.clienteForm = this.fb.group({
      idcliente: this.fb.control(''),
      razonsocial: this.fb.control('', [
        Validators.required]),
      ruc: this.fb.control('',
        [Validators.pattern(NUMBERS_PATTERN),
        Validators.minLength(this.minRUCLength),
        Validators.maxLength(this.maxRUCLength),
        Validators.required]),
      direccion: this.fb.control('', [
        Validators.minLength(this.minLength),
        Validators.maxLength(this.maxLength),
        Validators.required]),
      telefono: this.fb.control('', [
        Validators.pattern(NUMBERS_PATTERN),
        Validators.minLength(this.minCellLength),
        Validators.maxLength(this.maxCellLength),
        Validators.required]),
      paginaweb: this.fb.control(''),
    });
  }

  get canEdit(): boolean {
    return this.mode !== ClienteFormMode.VIEW;
  }


  loadClienteData() {
    const idcliente = this.route.snapshot.paramMap.get('idcliente');
    if (idcliente) {
      this.setViewMode(ClienteFormMode.EDIT);
      this.loadCliente(idcliente);
    } else {
      this.setViewMode(ClienteFormMode.ADD);
    }
  }

  loadCliente(idcliente?) {
    const loadCliente = this.clienteService.get(idcliente);
    loadCliente.pipe(takeUntil(this.unsubscribe$)).subscribe((cliente) => {
      this.clienteForm.setValue({
        idcliente: cliente.idcliente ? cliente.idcliente : '',
        ruc: cliente.ruc ? cliente.ruc : '',
        razonsocial: cliente.razonsocial ? cliente.razonsocial : '',
        telefono: cliente.telefono ? cliente.telefono : '',
        direccion: cliente.direccion ? cliente.direccion : '',
        paginaweb: cliente.paginaweb ? cliente.paginaweb : '',
      });
    });
  }

  convertToCliente(value: any): Cliente {
    const cliente: Cliente = value;
    return cliente;
  }

  save() {
    const cliente: Cliente = this.convertToCliente(this.clienteForm.value);

    let observable = new Observable<ResponseMensaje>();

    observable = cliente.idcliente
      ? this.clienteService.update(cliente)
      : this.clienteService.create(cliente);

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
    this.router.navigate(['/pages/comercial/cliente/listar']);
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }

  startLoading() {
    this.loading = true;
  }

  stopLoading() {
    this.loading = false;
  }
}
