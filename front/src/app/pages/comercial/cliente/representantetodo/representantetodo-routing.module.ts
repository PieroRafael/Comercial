import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from '../../../../@auth/auth.guard';
import { ListarComponent } from './listar/listar.component';
import { RepresentantetodoComponent } from './representantetodo.component';

const routes: Routes = [{
  path: '',
  component: RepresentantetodoComponent,
  children: [
    {
      path: 'listar',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
    {
      path: 'editar/:idcontacto',
      canActivate: [AuthGuard],
      component: ListarComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class RepresentantetodoRoutingModule { }
