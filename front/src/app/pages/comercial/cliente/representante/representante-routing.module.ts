import { NgModule } from '@angular/core';

import { Routes, RouterModule } from '@angular/router';



import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';


import { AuthGuard } from '../../../../@auth/auth.guard';
import { RepresentanteComponent } from './representante.component';

const routes: Routes = [{
  path: '',
  component: RepresentanteComponent,
  children: [
    {
      path: 'crear',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'crear/:idcliente',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'editar/:idcontacto',
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

export class RepresentanteRoutingModule { }
