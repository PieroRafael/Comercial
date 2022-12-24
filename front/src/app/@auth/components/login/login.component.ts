import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { getDeepFromObject } from '../../helpers';
import { NbThemeService } from '@nebular/theme';
import { EMAIL_PATTERN } from '../constants';
import { InitUserService } from '../../../@theme/services/init-user.service';
import { takeUntil } from 'rxjs/operators';
import { Subject } from 'rxjs';
import {
  NB_AUTH_OPTIONS,
  NbAuthSocialLink,
  NbAuthService,
  NbAuthResult,
} from '@nebular/auth';

@Component({
  selector: 'ngx-login',
  templateUrl: './login.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
  styleUrls: ['./login.component.scss'],
})

export class NgxLoginComponent implements OnInit, OnDestroy {

  private destroy$: Subject<void> = new Subject<void>();

  minLength: number = this.getConfigValue('forms.validation.password.minLength');
  maxLength: number = this.getConfigValue('forms.validation.password.maxLength');
  redirectDelay: number = this.getConfigValue('forms.login.redirectDelay');
  showMessages: any = this.getConfigValue('forms.login.showMessages');
  strategy: string = this.getConfigValue('forms.login.strategy');
  socialLinks: NbAuthSocialLink[] = this.getConfigValue('forms.login.socialLinks');
  rememberMe = this.getConfigValue('forms.login.rememberMe');
  isEmailRequired: boolean = this.getConfigValue('forms.validation.email.required');
  isPasswordRequired: boolean = this.getConfigValue('forms.validation.password.required');

  loading = false;
  errors: string[] = [];
  messages: string[] = [];
  usuario: any = {};
  submitted: boolean = false;
  loginForm: FormGroup;
  alive: boolean = true;

  get email() { return this.loginForm.get('email'); }
  get password() { return this.loginForm.get('password'); }

  constructor(protected service: NbAuthService,
    @Inject(NB_AUTH_OPTIONS) protected options = {},
    protected cd: ChangeDetectorRef,
    protected themeService: NbThemeService,
    private fb: FormBuilder,
    protected router: Router,
    protected initUserService: InitUserService) { }

  ngOnInit(): void {
    const emailValidators = [
      Validators.pattern(EMAIL_PATTERN),
    ];
    this.isEmailRequired && emailValidators.push(Validators.required);

    const passwordValidators = [
      Validators.minLength(this.minLength),
      Validators.maxLength(this.maxLength),
    ];
    this.isPasswordRequired && passwordValidators.push(Validators.required);

    this.loginForm = this.fb.group({
      email: this.fb.control('', [...emailValidators]),
      password: this.fb.control('12345', [...passwordValidators]),
      rememberMe: this.fb.control(false),
    });
  }

  login(): void {
    this.usuario = this.loginForm.value;
    this.errors = [];
    this.messages = [];
    this.submitted = true;
    this.service.authenticate(this.strategy, this.usuario).subscribe((result: NbAuthResult) => {
      this.submitted = false;

      if (result.isSuccess()) {
        this.messages = ['Usted a iniciado sesión correctamente'];
        this.initUserService.initCurrentUser().subscribe();
      } else {
        if (result.getResponse().status === 0) {
          this.errors = ['Evento inesperado, comuníquese con el administrador del sistema'];
        } else {
          this.errors = [result.getResponse().error];
        }
      }

      const redirect = result.getRedirect();
      if (redirect) {
        setTimeout(() => {
          return this.router.navigateByUrl(redirect);
        }, this.redirectDelay);
      }
      this.cd.detectChanges();
    });
  }

  getConfigValue(key: string): any {
    return getDeepFromObject(this.options, key, null);
  }

  initUser() {
    this.initUserService.initCurrentUser()
      .pipe(
        takeUntil(this.destroy$),
      )
      .subscribe();
  }

  ngOnDestroy() {
    this.destroy$.next();
    this.destroy$.complete();
  }

  keyDownFunction(event) {
    if (event.keyCode === 13) {
      this.login();
    }
  }
}
