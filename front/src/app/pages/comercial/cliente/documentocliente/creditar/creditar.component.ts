import { takeUntil } from 'rxjs/operators';
import { ResponseMensaje } from './../../../../../@core/comercial/interfaces/responseMensaje';
import { Observable, Subject } from 'rxjs';
import { DocumentoCliente } from './../../../../../@core/comercial/interfaces/documentocliente';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { Router, ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { DocumentoClienteService } from '../../../../../@core/comercial/backend/services/documentocliente.service';

@Component({
  selector: 'ngx-crear-clientes',
  templateUrl: './creditar.component.html',
  styleUrls: ['./creditar.component.scss'],
})

export class CreditarComponent implements OnInit {

  loading = false;

  documentoFile: FileList = null;
  idcliente = 0;
  minLength: number = 4;
  maxLength: number = 30;
  documentoclienteForm: FormGroup;

  get documentocliente() { return this.documentoclienteForm.get('documentocliente'); }
  get nombre() { return this.documentoclienteForm.get('nombre'); }
  get descripcion() { return this.documentoclienteForm.get('descripcion'); }

  protected readonly unsubscribe$ = new Subject<void>();

  constructor(private fb: FormBuilder,
    private activatedRouter: ActivatedRoute,
    private router: Router,
    private documentoclienteService: DocumentoClienteService,
    private toastService: ToastService,
  ) {
    this.idcliente = this.activatedRouter.snapshot.params.idcliente;
  }

  handleDocumento(files: FileList) {
    this.documentoFile = files;
  }

  ngOnInit(): void {
    this.initdocumentoclienteForm();
  }

  initdocumentoclienteForm() {
    this.documentoclienteForm = this.fb.group({
      descripcion: this.fb.control(''),
    });
  }

  convertToCliente(value: any): DocumentoCliente {
    const documentocliente: DocumentoCliente = value;
    documentocliente.listFile = this.documentoFile;
    documentocliente.idcliente = this.idcliente;
    return documentocliente;
  }

  save() {
    let count = 0;
    let msc = '';

    const documentocliente: DocumentoCliente = this.convertToCliente(this.documentoclienteForm.value);
    let observable = new Observable<ResponseMensaje>();

    this.loading = true;
    observable = documentocliente.iddocumentocliente
      ? this.documentoclienteService.update(documentocliente)
      : this.documentoclienteService.create(documentocliente);

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

    } else {

      this.toastService.showAdvertencia(res.mensaje);
      this.stopLoading();

    }
    this.back();
  }

  handleWrongResponse() {
    this.toastService.showError();
    this.stopLoading();

  }

  back() {
    this.router.navigate(['/pages/comercial/cliente/documentocliente/listar/' + this.idcliente]);
  }

  startLoading() {
    this.loading = true;
  }

  stopLoading() {
    this.loading = false;
  }
}
