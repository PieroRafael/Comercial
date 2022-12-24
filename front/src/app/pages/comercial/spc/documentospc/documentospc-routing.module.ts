import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';
import { DocumentospcComponent } from './documentospc.component';
import { AuthGuard } from '../../../../@auth/auth.guard';

const routes: Routes = [{
  path: '',
  component: DocumentospcComponent,
  children: [
    {
      path: 'crear/:proyecto/:idspc',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: ':idseguimientospc',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'editar/:proyecto/:idseguimientospc',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'listar/:idspc',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },

    {
      path: 'listar/:idspc/:',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class DocumentospcRoutingModule { }
