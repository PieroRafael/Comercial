import { RouterModule, Routes } from '@angular/router';
import { NgModule } from '@angular/core';
import { PagesComponent } from './pages.component';
import { DashboardComponent } from './sistema/dashboard/dashboard.component';
import { NotFoundComponent } from './sistema/miscellaneous/not-found/not-found.component';

const routes: Routes = [{
  path: '',
  component: PagesComponent,
  children: [
    {
      path: 'dashboard',
      component: DashboardComponent,
    },
    {
      path: 'usuario',
      loadChildren: () => import('./sistema/usuario/usuario.module')
        .then(m => m.UsuarioModule),
    },
    {
      path: 'miscellaneous',
      loadChildren: () => import('./sistema/miscellaneous/miscellaneous.module')
        .then(m => m.MiscellaneousModule),
    },
    {
      path: 'comercial',
      loadChildren: () => import('./comercial/comercial.module')
        .then(m => m.ComercialModule),
    },
    {
      path: '',
      redirectTo: 'dashboard',
      pathMatch: 'full',
    },
    {
      path: '**',
      component: NotFoundComponent,
    },
  ],
}];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule],
})
export class PagesRoutingModule {
}
