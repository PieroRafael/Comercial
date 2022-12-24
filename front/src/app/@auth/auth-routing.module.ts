import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import {
  NgxAuthComponent,
  NgxLoginComponent,
  NgxLogoutComponent,
  NgxRequestPasswordComponent,
  NgxResetPasswordComponent,
} from './components';

const routes: Routes = [{
  path: '',
  component: NgxAuthComponent,
  children: [
    {
      path: '',
      component: NgxLoginComponent,
    },
    {
      path: 'login',
      component: NgxLoginComponent,
    },
    {
      path: 'logout',
      component: NgxLogoutComponent,
    },
    {
      path: 'request-password',
      component: NgxRequestPasswordComponent,
    },
    {
      path: 'reset-password',
      component: NgxResetPasswordComponent,
    },
    {
      path: 'restore-pass/:token',
      component: NgxResetPasswordComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class AuthRoutingModule {
}
