import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { Observable, Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';
import { Usuario, UsuarioData } from '../../../../@core/sistema/interfaces/usuario';
import { EMAIL_PATTERN, LETTERS_PATTERN, NUMBERS_PATTERN } from '../../../../@auth/components';
import { Rol } from '../../../../@core/sistema/interfaces/rol';
import { ResponseMensaje } from '../../../../@core/sistema/interfaces/responseMensaje';
import { ToastService } from '../../../../@core/sistema/utils';
import { NbDialogService } from '@nebular/theme';
import { PermisoComponent } from '../permiso/permiso.component';

export enum UsuarioFormMode {
  VIEW = 'View',
  EDIT = 'Editar',
  ADD = 'Nuevo',
}

@Component({
  selector: 'ngx-creditar-usuario',
  templateUrl: './creditar.component.html',
  styleUrls: ['./creditar.component.scss'],
})
export class CreditarComponent implements OnInit, OnDestroy {

  usuarioForm: FormGroup;
  permisos = [];
  usuarioPermisos: Array<Rol>;

  protected readonly unsubscribe$ = new Subject<void>();

  get nombres() { return this.usuarioForm.get('nombres'); }
  get apellidos() { return this.usuarioForm.get('apellidos'); }
  get nick() { return this.usuarioForm.get('nick'); }
  get correo() { return this.usuarioForm.get('correo'); }
  get celular() { return this.usuarioForm.get('celular'); }

  mode: UsuarioFormMode;
  setViewMode(viewMode: UsuarioFormMode) {
    this.mode = viewMode;
  }

  constructor(private usuarioService: UsuarioData,
    private router: Router,
    private route: ActivatedRoute,
    private toastService: ToastService,
    private fb: FormBuilder,
    private dialogService: NbDialogService) {
    this.usuarioPermisos = [];
  }

  ngOnInit(): void {
    this.initUsuarioForm();
    this.loadUsuarioData();
  }

  initUsuarioForm() {
    this.usuarioForm = this.fb.group({
      idusuario: this.fb.control(''),
      nombres: this.fb.control('', [Validators.required, Validators.minLength(4), Validators.maxLength(40),
      Validators.pattern(LETTERS_PATTERN)]),
      apellidos: this.fb.control('', [Validators.required, Validators.minLength(4), Validators.maxLength(40),
      Validators.pattern(LETTERS_PATTERN)]),
      nick: this.fb.control('', [Validators.required, Validators.minLength(4), Validators.maxLength(15),
      Validators.pattern(LETTERS_PATTERN)]),
      celular: this.fb.control('', [Validators.min(900000000), Validators.max(999999999),
      Validators.pattern(NUMBERS_PATTERN)]),
      correo: this.fb.control('', [Validators.required, Validators.pattern(EMAIL_PATTERN)]),
    });
  }

  get canEdit(): boolean {
    return this.mode !== UsuarioFormMode.VIEW;
  }


  loadUsuarioData() {
    const idusuario = this.route.snapshot.paramMap.get('idusuario');
    if (idusuario) {
      this.setViewMode(UsuarioFormMode.EDIT);
      this.loadUsuario(idusuario);
    } else {
      this.setViewMode(UsuarioFormMode.ADD);
    }
  }

  loadUsuario(idusuario?) {
    const loadUsuario = this.usuarioService.get(idusuario);
    loadUsuario.pipe(takeUntil(this.unsubscribe$)).subscribe((usuario) => {
      this.usuarioForm.setValue({
        idusuario: usuario.idusuario ? usuario.idusuario : '',
        nombres: usuario.nombres ? usuario.nombres : '',
        apellidos: usuario.apellidos ? usuario.apellidos : '',
        nick: usuario.nick ? usuario.nick : '',
        celular: usuario.celular ? usuario.celular : '',
        correo: usuario.correo,
      });

      this.usuarioPermisos = usuario.roles;
      this.permisos = usuario.roles;
    });
  }

  convertToUsuario(value: any): Usuario {
    const usuario: Usuario = value;
    usuario.roles = this.permisos;
    return usuario;
  }

  save() {
    const usuario: Usuario = this.convertToUsuario(this.usuarioForm.value);

    let observable = new Observable<ResponseMensaje>();

    observable = usuario.idusuario
      ? this.usuarioService.update(usuario)
      : this.usuarioService.create(usuario);

    observable
      .pipe(takeUntil(this.unsubscribe$))
      .subscribe(res => {
        this.handleSuccessResponse(res);
      },
        err => {
          this.handleWrongResponse();
        });
  }

  permiso() {
    this.dialogService.open(PermisoComponent, {
      context: {
        usuarioPermisos: this.usuarioPermisos,
      },
    }).onClose.subscribe(res => { this.permisos = res; });
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
    this.router.navigate(['/pages/usuario/listar']);
  }

  ngOnDestroy(): void {
    this.unsubscribe$.next();
    this.unsubscribe$.complete();
  }
}
