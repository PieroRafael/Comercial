import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { OtComponent } from './ot.component';
import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';
import { AuthGuard } from '../../../@auth/auth.guard';

const routes: Routes = [{
  path: '',
  component: OtComponent,
  children: [
    {
      path: 'crear',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'editar/:idot',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'documentoot',
      loadChildren: () => import('./documentoot/documentoot.module')
        .then(m => m.DocumentootModule),
    },
    {
      path: 'adicional',
      loadChildren: () => import('./adicional/adicional.module')
        .then(m => m.AdicionalModule),
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class OtRoutingModule { }
