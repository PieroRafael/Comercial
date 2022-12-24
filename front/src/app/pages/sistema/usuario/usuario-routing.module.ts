import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UsuarioComponent } from './usuario.component';
import { CreditarComponent } from './creditar/creditar.component';
import { ListarComponent } from './listar/listar.component';

const routes: Routes = [{
  path: '',
  component: UsuarioComponent,
  children: [
    {
      path: 'listar',
      component: ListarComponent,
    },
    {
      path: 'editar/:idusuario',
      component: CreditarComponent,
    },
    {
      path: 'crear',
      component: CreditarComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class UsuarioRoutingModule {

}
