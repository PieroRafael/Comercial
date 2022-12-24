import { Documentoot } from './../../../../../@core/comercial/interfaces/documentoot';
import { takeUntil } from 'rxjs/operators';
import { AreasSelect } from './../../../../../@core/comercial/interfaces/areasSelect';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { DocumentootService } from '../../../../../@core/comercial/backend/services/documentoot.service';
import { Subject } from 'rxjs';

export enum DocumentoOtFormMode {
  VIEW = 'View',
  EDIT = 'Editar',
  ADD = 'Nuevo',
}

@Component({
  selector: 'ngx-createotdoc',
  templateUrl: './creditar.component.html',
  styleUrls: ['./creditar.component.scss'],
})

export class CreditarComponent implements OnInit {
  loading = false;

  documentoFile: FileList = null;
  idot = 0;
  idareas = 0;
  iddocumentoot = 0;
  minLength: number = 4;
  maxLength: number = 30;
  minCellLength: number = 9;
  maxCellLength: number = 9;
  minRUCLength: number = 11;
  maxRUCLength: number = 11;
  documentotForm: FormGroup;
  areasSelect: AreasSelect[];
  listA: String[];
  list: any[];

  get listArea() { return this.documentotForm.get('listArea'); }
  get documento() { return this.documentotForm.get('documento'); }
  get descripcion() { return this.documentotForm.get('descripcion'); }

  protected readonly unsubscribe$ = new Subject<void>();

  mode: DocumentoOtFormMode;

  setViewMode(viewMode: DocumentoOtFormMode) {
    this.mode = viewMode;
  }

  constructor(private documentootService: DocumentootService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private fb: FormBuilder,
    private toastService: ToastService,
  ) {
    this.idot = this.activatedRoute.snapshot.params.idot;
    this.iddocumentoot = this.activatedRoute.snapshot.params.iddocumentoot;
    this.documentootService.listArea().subscribe((res: AreasSelect[]) => {
      this.areasSelect = res;
    });
  }

  handleDocumento(files: FileList) {
    this.documentoFile = files;
  }

  ngOnInit(): void {
    this.initdocumentotForm();
    this.loadDocumentoOtData();
  }

  initdocumentotForm() {
    this.documentotForm = this.fb.group({
      iddocumentoot: this.fb.control(''),
      idot: this.fb.control(''),
      listArea: this.fb.control([], Validators.required),
      descripcion: this.fb.control(''),
    });
  }

  get canEdit(): boolean {
    return this.mode !== DocumentoOtFormMode.VIEW;
  }

  loadDocumentoOtData() {
    const iddocumentoot = this.activatedRoute.snapshot.paramMap.get('iddocumentoot');
    if (iddocumentoot) {
      this.setViewMode(DocumentoOtFormMode.EDIT);
      this.loadDocumentoOt(iddocumentoot);
    } else {
      this.setViewMode(DocumentoOtFormMode.ADD);
    }
  }

  loadDocumentoOt(iddocumentoot?) {
    const loadDocumentoOt = this.documentootService.get(iddocumentoot);
    loadDocumentoOt.pipe(takeUntil(this.unsubscribe$)).subscribe((documento) => {
      this.documentotForm.setValue({
        iddocumentoot: documento.iddocumentoot ? documento.iddocumentoot : '',
        idot: documento.idot ? documento.idot : '',
        descripcion: documento.descripcion ? documento.descripcion : '',
        listArea: documento.listArea ? documento.listArea : [],
      });
      this.idot = documento.idot;
    });
  }

  convertToDocumentoOt(value: any): Documentoot {
    const documentoot: Documentoot = value;
    documentoot.iddocumentoot = this.iddocumentoot;
    documentoot.idot = this.idot;
    return documentoot;
  }

  save() {
    const documentoot: Documentoot = this.convertToDocumentoOt(this.documentotForm.value);
    if (window.confirm('¿Está seguro que desea GUARDAR este documento?')) {
      if (documentoot.iddocumentoot) {
        this.documentootService.update(documentoot).subscribe(
          res => {
            this.handleSuccessResponse(res);
          },
          err => {
            this.handleWrongResponse();
          });
      } else {
        this.grabar();
      }
    }
  }

  grabar() {
    let count = 0;
    let msc = '';
    for (let index = 0; index < this.documentoFile.length; index++) {
      const documento = {
        listArea: this.listArea.value,
        file: this.documentoFile[index],
        descripcion: this.descripcion.value,
        idot: this.idot,
      };
      this.documentootService.create(documento).subscribe(
        (res) => {
          if (res.codigo === 200) {
            count += 1;
          } else {
            msc += 'El archivo en la posicion' + index + 'no se subio correctamente ';
          }
          if (index === this.documentoFile.length - 1) {
            this.toastService.showCorrecto((count > 0) ? 'Se subieron' + count.toString() +
              ' arhivos correctamente' : (msc !== '') ? msc : '');
            this.stopLoading();
            this.back();
          }
        },
        error => {
          this.handleWrongResponse();
        },
      );
    }
  }

  handleSuccessResponse(res) {
    if (res.codigo === 200) {

      this.toastService.showCorrecto(res.mensaje);
      this.back();
      this.stopLoading();
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
    this.router.navigate(['/pages/comercial/ot/documentoot/listar/' + this.idot]);
  }

  startLoading() {
    this.loading = true;
  }

  stopLoading() {
    this.loading = false;
  }

}
