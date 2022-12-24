import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ComercialComponent } from './comercial.component';

const routes: Routes = [{
  path: '',
  component: ComercialComponent,
  children: [
    {
      path: 'cliente',
      loadChildren: () => import('./cliente/cliente.module')
        .then(m => m.ClienteModule),
    },
    {
      path: 'spc',
      loadChildren: () => import('./spc/spc.module')
        .then(m => m.SpcModule),
    },
    {
      path: 'ot',
      loadChildren: () => import('./ot/ot.module')
        .then(m => m.OtModule),
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})

export class ComercialRoutingModule { }
