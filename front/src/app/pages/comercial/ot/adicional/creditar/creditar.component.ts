import { Documentoot } from './../../../../../@core/comercial/interfaces/documentoot';
import { AreasSelect } from './../../../../../@core/comercial/interfaces/areasSelect';
import { takeUntil } from 'rxjs/operators';
import { ToastService } from './../../../../../@core/sistema/utils/toast.service';
import { ActivatedRoute, Router } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Subject } from 'rxjs';
import { DocumentootService } from '../../../../../@core/comercial/backend/services/documentoot.service';

export enum AdicionalFormMode {
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

  protected readonly unsubscribe$ = new Subject<void>();

  get listArea() { return this.documentotForm.get('listArea'); }
  get documento() { return this.documentotForm.get('documento'); }
  get descripcion() { return this.documentotForm.get('descripcion'); }

  mode: AdicionalFormMode;

  setViewMode(viewMode: AdicionalFormMode) {
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
    this.loadAdicionalData();
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
    return this.mode !== AdicionalFormMode.VIEW;
  }


  loadAdicionalData() {
    const idadicionalot = this.activatedRoute.snapshot.paramMap.get('idadicionalot');
    if (idadicionalot) {
      this.setViewMode(AdicionalFormMode.EDIT);
      this.loadAdicionalot(idadicionalot);
    } else {
      this.setViewMode(AdicionalFormMode.ADD);
    }
  }

  loadAdicionalot(idadicionalot?) {
    const loadAdicionalot = this.documentootService.get(idadicionalot);
    loadAdicionalot.pipe(takeUntil(this.unsubscribe$)).subscribe((documento) => {
      this.documentotForm.setValue({
        iddocumentoot: documento.iddocumentoot ? documento.iddocumentoot : '',
        idot: documento.idot ? documento.idot : '',
        descripcion: documento.descripcion ? documento.descripcion : '',
        listArea: documento.area ? documento.area : [],
      });
    });
  }

  convertToAdicionalot(value: any): Documentoot {
    const documentoot: Documentoot = value;
    documentoot.iddocumentoot = this.iddocumentoot;
    documentoot.idot = this.idot;
    return documentoot;
  }

  handleSuccessResponse(res) {
    if (res.codigo === 200) {

      this.toastService.showCorrecto(res.mensaje);
    } else {

      this.toastService.showAdvertencia(res.mensaje);
    }
    this.back();
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  back() {
    this.router.navigate(['/pages/comercial/ot/documentoot/listar/' + this.idot]);
  }
}
