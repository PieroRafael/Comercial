import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DocumentoclienteComponent } from './documentocliente.component';
import { ListarComponent } from './listar/listar.component';
import { AuthGuard } from '../../../../@auth/auth.guard';
import { CreditarComponent } from './creditar/creditar.component';

const routes: Routes = [{
  path: '',
  component: DocumentoclienteComponent,
  children: [
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'listar/:idcliente',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'crear/:idcliente',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'representante',
      loadChildren: () => import('./documentocliente.module')
        .then(m => m.DocumentoclienteModule),
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class DocumentoclienteRoutingModule { }
