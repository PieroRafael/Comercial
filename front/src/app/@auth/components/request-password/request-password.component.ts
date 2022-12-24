import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Inject, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { NB_AUTH_OPTIONS, NbAuthService } from '@nebular/auth';
import { ToastService } from '../../../@core/sistema/utils';
import { getDeepFromObject } from '../../helpers';
import { EMAIL_PATTERN } from '../constants';

@Component({
  selector: 'ngx-request-password-page',
  styleUrls: ['./request-password.component.scss'],
  templateUrl: './request-password.component.html',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class NgxRequestPasswordComponent implements OnInit {

  redirectDelay: number = this.getConfigValue('forms.requestPassword.redirectDelay');
  showMessages: any = this.getConfigValue('forms.requestPassword.showMessages');
  strategy: string = this.getConfigValue('forms.requestPassword.strategy');
  isEmailRequired: boolean = this.getConfigValue('forms.validation.email.required');

  loading = false;
  submitted = false;
  errors: string[] = [];
  messages: string[] = [];
  usuario: any = {};
  requestPasswordForm: FormGroup;

  constructor(protected service: NbAuthService,
    @Inject(NB_AUTH_OPTIONS) protected options = {},
    protected cd: ChangeDetectorRef,
    protected fb: FormBuilder,
    protected router: Router,
    private toastService: ToastService) { }

  get email() { return this.requestPasswordForm.get('email'); }

  ngOnInit(): void {
    const passwordValidators = [
      Validators.pattern(EMAIL_PATTERN),
    ];
    this.isEmailRequired && passwordValidators.push(Validators.required);

    this.requestPasswordForm = this.fb.group({
      email: this.fb.control('scingerp@gmail.com', [...passwordValidators]),
    });
  }

  requestPass(): void {

    this.loading = true;
    this.usuario = this.requestPasswordForm.value;
    this.errors = this.messages = [];
    this.submitted = true;

    this.service.requestPassword(this.strategy, this.usuario).subscribe((result) => {
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

  handleSuccessResponse(res) {
    if (res.getResponse().body.codigo === 200) {

      this.toastService.showCorrecto(res.getResponse().body.mensaje);
      this.back();
    } else {

      this.toastService.showAdvertencia(res.getResponse().body.mensaje);
    }
  }

  handleWrongResponse() {
    this.toastService.showError();
  }

  back() {
    this.loading = false;
    this.router.navigate(['/']);
  }

  getConfigValue(key: string): any {
    return getDeepFromObject(this.options, key, null);
  }
}
