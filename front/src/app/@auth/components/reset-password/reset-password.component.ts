import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { NB_AUTH_OPTIONS, NbAuthService, NbAuthResult } from '@nebular/auth';
import { ResponseMensaje } from '../../../@core/sistema/interfaces/responseMensaje';
import { Usuario, UsuarioData } from '../../../@core/sistema/interfaces/usuario';
import { ToastService } from '../../../@core/sistema/utils';
import { getDeepFromObject } from '../../helpers';

@Component({
  selector: 'ngx-reset-password-page',
  styleUrls: ['./reset-password.component.scss'],
  templateUrl: './reset-password.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NgxResetPasswordComponent implements OnInit {

  minLength: number = 4;
  maxLength: number = 10;
  redirectDelay: number = this.getConfigValue('forms.resetPassword.redirectDelay');
  showMessages: any = this.getConfigValue('forms.resetPassword.showMessages');
  strategy: string = this.getConfigValue('forms.resetPassword.strategy');
  isPasswordRequired: boolean = this.getConfigValue('forms.validation.password.required');
  token: string = '';

  submitted = false;
  usuario: Usuario = null;
  resetPasswordForm: FormGroup;

  constructor(protected service: NbAuthService,
    @Inject(NB_AUTH_OPTIONS) protected options = {},
    protected cd: ChangeDetectorRef,
    protected fb: FormBuilder,
    protected router: Router,
    private usuarioService: UsuarioData,
    private rutaActiva: ActivatedRoute,
    private toastService: ToastService) {
  }

  ngOnInit(): void {
    const passwordValidators = [
      Validators.minLength(this.minLength),
      Validators.maxLength(this.maxLength),
    ];
    this.isPasswordRequired && passwordValidators.push(Validators.required);

    this.resetPasswordForm = this.fb.group({
      password: this.fb.control('', [...passwordValidators]),
      confirmPassword: this.fb.control('', [...passwordValidators]),
    });
  }

  get password() { return this.resetPasswordForm.get('password'); }
  get confirmPassword() { return this.resetPasswordForm.get('confirmPassword'); }

  reiniciar() {

    this.token = this.rutaActiva.snapshot.params.token;
    if (this.token === undefined) {

      this.resetPass();
    } else {

      this.restorePass();
    }
  }

  resetPass(): void {

    this.submitted = true;
    this.usuario = this.resetPasswordForm.value;

    this.service.resetPassword(this.strategy, this.usuario).subscribe((result: NbAuthResult) => {
      this.submitted = false;
      if (result.isSuccess()) {
        this.handleSuccessResponse(result);
      } else {
        this.handleWrongResponse();
      }
      this.cd.detectChanges();
    },
      err => {
        this.handleWrongResponse();
      });
  }

  restorePass() {

    this.submitted = true;

    const restore = {
      confirmPassword: this.confirmPassword.value,
      newPassword: this.password.value,
      token: this.rutaActiva.snapshot.params.token,
    };

    this.usuarioService.restore(restore).subscribe((res: ResponseMensaje) => {
      this.submitted = false;
      this.handleSuccessResponse(res);
      this.cd.detectChanges();
    },
      err => {
        this.handleWrongResponse();
      });
  }

  handleSuccessResponse(res) {

    const msg = (res.codigo === undefined) ? res.getResponse().body.mensaje : res.mensaje;
    const cod = (res.codigo === undefined) ? res.getResponse().body.codigo : res.codigo;
    if (cod === 200) {

      this.toastService.showCorrecto(msg);
    } else {

      this.toastService.showAdvertencia(msg);
    }
    this.back();
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  back() {
    this.router.navigate(['/']);
  }

  getConfigValue(key: string): any {
    return getDeepFromObject(this.options, key, null);
  }
}
