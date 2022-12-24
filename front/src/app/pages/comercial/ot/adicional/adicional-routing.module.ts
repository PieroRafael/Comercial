import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';
import { AuthGuard } from '../../../../@auth/auth.guard';
import { AdicionalComponent } from './adicional.component';

const routes: Routes = [{
  path: '',
  component: AdicionalComponent,
  children: [
    {
      path: 'crear',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'editar/:idot',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'listar/:otprincipal',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class AdicionalRoutingModule {}
