import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';
import { DocumentootComponent } from './documentoot.component';
import { AuthGuard } from '../../../../@auth/auth.guard';

const routes: Routes = [{
  path: '',
  component: DocumentootComponent,
  children: [
    {
      path: 'crear/:idot',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'editar/:iddocumentoot',
      canActivate: [AuthGuard],
      component: CreditarComponent,
    },
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'listar/:idot',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class DocumentootRoutingModule { }
