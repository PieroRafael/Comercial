import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';

import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';

import { AuthGuard } from '../../../../@auth/auth.guard';
import { UnidadComponent } from './unidad.component';

const routes: Routes = [{
  path: '',
  component: UnidadComponent,
  children: [
    {
      path: 'crear/:idcliente',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'editar/:idunidad',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'listar/:idcliente',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class UnidadRoutingModule { }
