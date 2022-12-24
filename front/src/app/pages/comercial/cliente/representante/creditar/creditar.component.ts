import { UnidadSelect } from './../../../../../@core/comercial/interfaces/unidadSelect';
import { UnidadService } from './../../../../../@core/comercial/backend/services/unidad.service';
import { Contacto } from './../../../../../@core/comercial/interfaces/contacto';
import { ClienteService } from './../../../../../@core/comercial/backend/services/cliente.service';
import { ResponseMensaje } from './../../../../../@core/sistema/interfaces/responseMensaje';
import { takeUntil, map } from 'rxjs/operators';
import { Subject, Observable, of } from 'rxjs';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit, ViewChild } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { NUMBERS_PATTERN, LETTERS_PATTERN, EMAIL_PATTERN } from '../../../../../@auth/components';
import { ClienteSelect } from '../../../../../@core/comercial/interfaces/clienteSelect';
import { ContactoService } from '../../../../../@core/comercial/backend/services/contacto.service';

export enum ContactoFormMode {
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

  minLength: number = 4;
  maxLength: number = 30;
  minTelefonoLength: number = 9;
  maxTelefonoLength: number = 9;
  minCellLength: number = 9;
  maxCellLength: number = 9;
  minRUCLength: number = 11;
  maxRUCLength: number = 11;
  contactoForm: FormGroup;
  idcliente: any = 0;
  idcontacto = 0;
  clienteSelect: ClienteSelect[];
  unidadSelect: UnidadSelect[];
  filteredOptions$: Observable<ClienteSelect[]>;
  @ViewChild('autoInput') input;

  protected readonly unsubscribe$ = new Subject<void>();

  get razonsocial() { return this.contactoForm.get('razonsocial'); }
  get nombre() { return this.contactoForm.get('nombre'); }
  get correo() { return this.contactoForm.get('correo'); }
  get telefono() { return this.contactoForm.get('telefono'); }
  get celular() { return this.contactoForm.get('celular'); }
  get cargo() { return this.contactoForm.get('cargo'); }
  get unidad() { return this.contactoForm.get('unidad'); }

  mode: ContactoFormMode;


  setViewMode(viewMode: ContactoFormMode) {
    this.mode = viewMode;
  }

  constructor(private contactoService: ContactoService,
    private unidadService: UnidadService,
    private clienteService: ClienteService,
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private toastService: ToastService,
    private fb: FormBuilder,
  ) {

    this.idcliente = this.activatedRoute.snapshot.params.idcliente;

    this.clienteService.listSelect().subscribe((res: ClienteSelect[]) => {
      this.clienteSelect = res;
    });
    /*if (!this.idcliente) {
      this.idcliente = this.clienteSelect.find(cliente =>
        cliente.razonsocial === this.razonsocial.value).idcliente
        //this.contactoForm.get('razonsocial');
    }*/

    if (this.idcliente) {
      this.unidadService.listSelect(this.idcliente).subscribe((res: UnidadSelect[]) => {
        this.unidadSelect = res;
      });
    }

    /*contacto.idcliente = this.clienteSelect.find(cliente =>
    cliente.razonsocial === this.razonsocial.value).idcliente;*/
  }

  ngOnInit(): void {
    this.initcontactoForm();
    this.loadContactoData();
  }

  initcontactoForm() {
    this.contactoForm = this.fb.group({
      idcontacto: this.fb.control('', [
      ]),
      razonsocial: this.fb.control('', [
        Validators.required]),
      nombre: this.fb.control('', [
        Validators.pattern(LETTERS_PATTERN),
        Validators.required]),
      correo: this.fb.control('', [
        Validators.pattern(EMAIL_PATTERN),
        Validators.required]),
      telefono: this.fb.control('', [
        Validators.pattern(NUMBERS_PATTERN),
        Validators.minLength(this.minTelefonoLength),
        Validators.maxLength(this.maxTelefonoLength)]),
      celular: this.fb.control('', [
        Validators.pattern(NUMBERS_PATTERN),
        Validators.minLength(this.minCellLength),
        Validators.maxLength(this.maxCellLength),
        Validators.required]),
      cargo: this.fb.control(''),
      unidad: this.fb.control(''),
    });
  }

  get canEdit(): boolean {
    return this.mode !== ContactoFormMode.VIEW;
  }

  loadContactoData() {
    const idcontacto = this.activatedRoute.snapshot.paramMap.get('idcontacto');
    if (idcontacto) {
      this.setViewMode(ContactoFormMode.EDIT);
      this.loadContacto(idcontacto);
    } else {
      this.setViewMode(ContactoFormMode.ADD);
      if (this.idcliente) {
        const loadCliente = this.contactoService.get(this.idcliente);
        loadCliente.pipe(takeUntil(this.unsubscribe$)).subscribe((contacto) => {
          this.contactoForm.setValue({
            idcontacto: idcontacto ? idcontacto : '',
            razonsocial: contacto.razonsocial,
            nombre: '',
            correo: '',
            telefono: '',
            celular: '',
            cargo: '',
            unidad: '',
          });
        });
      }
    }
  }

  loadContacto(idcontacto?) {
    const loadContacto = this.contactoService.get(idcontacto);
    loadContacto.pipe(takeUntil(this.unsubscribe$)).subscribe((contacto) => {
      this.contactoForm.setValue({
        idcontacto: contacto.idcontacto ? contacto.idcontacto : '',
        razonsocial: contacto.razonsocial ? contacto.razonsocial : '',
        nombre: contacto.nombre ? contacto.nombre : '',
        correo: contacto.correo ? contacto.correo : '',
        telefono: contacto.telefono ? contacto.telefono : '',
        celular: contacto.celular ? contacto.celular : '',
        cargo: contacto.cargo ? contacto.cargo : '',
        unidad: contacto.unidad ? contacto.unidad : '',
      });
    });
  }

  validar() {
    if (this.razonsocial.value === '') {
      this.toastService.showAdvertencia('No se puede elegir unidad sin seleccionar cliente');
      this.contactoForm.get('unidad').setValue('');
      return false;
    }
  }

  click() {
    if (this.razonsocial.value) {
      this.idcliente = this.clienteSelect.find(cliente =>
        cliente.razonsocial === this.razonsocial.value).idcliente;
      this.unidadService.listSelect(this.idcliente).subscribe((res: UnidadSelect[]) => {
        this.unidadSelect = res;
      });
    } else {
      this.unidadSelect = [];
    }
  }

  convertToContacto(value: any): Contacto {
    const contacto: Contacto = value;
    contacto.idcliente = this.clienteSelect.find(cliente =>
      cliente.razonsocial === this.razonsocial.value).idcliente;
    return contacto;
  }

  save() {
    const contacto: Contacto = this.convertToContacto(this.contactoForm.value);
    let observable = new Observable<ResponseMensaje>();

    observable = contacto.idcontacto
      ? this.contactoService.update(contacto)
      : this.contactoService.create(contacto);

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
    this.router.navigate(['/pages/comercial/cliente/representantetodo/listar']);
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
}
