import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ClienteComponent } from './cliente.component';
import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';
import { AuthGuard } from '../../../@auth/auth.guard';

const routes: Routes = [{
  path: '',
  component: ClienteComponent,
  children: [
    {
      path: 'crear',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'editar/:idcliente',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'representante',
      loadChildren: () => import('./representante/representante.module')
        .then(m => m.RepresentanteModule),
    },
    {
      path: 'documentocliente',
      loadChildren: () => import('./documentocliente/documentocliente.module')
        .then(m => m.DocumentoclienteModule),
    },
    {
      path: 'representantetodo',
      loadChildren: () => import('./representantetodo/representantetodo.module')
        .then(m => m.RepresentantetodoModule),
    },
    {
      path: 'unidad',
      loadChildren: () => import('./unidad/unidad.module')
        .then(m => m.UnidadModule),
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class ClienteRoutingModule { }
