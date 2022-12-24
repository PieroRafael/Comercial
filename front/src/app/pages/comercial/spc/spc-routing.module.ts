import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SpcComponent } from './spc.component';
import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';
import { ModalspcComponent } from './modalspc/modalspc.component';
import { AuthGuard } from '../../../@auth/auth.guard';

const routes: Routes = [{
  path: '',
  component: SpcComponent,
  children: [
    {
      path: 'crear',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'modal',
      canActivate: [AuthGuard],
      component: ModalspcComponent,
    },
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'editar/:idspc',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'documentospc',
      loadChildren: () => import('./documentospc/documentospc.module')
        .then(m => m.DocumentospcModule),
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class SpcsRoutingModule { }
