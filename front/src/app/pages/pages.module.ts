import { NgModule } from '@angular/core';
import { PagesComponent } from './pages.component';
import { PagesRoutingModule } from './pages-routing.module';
import { ThemeModule } from '../@theme/theme.module';
import { MiscellaneousModule } from './sistema/miscellaneous/miscellaneous.module';
import { PagesMenu } from './pages-menu';
import { NbMenuModule } from '@nebular/theme';
import { AuthModule } from '../@auth/auth.module';
import { DashboardComponent } from './sistema/dashboard/dashboard.component';

const PAGES_COMPONENTS = [
  PagesComponent,
];

@NgModule({
  imports: [
    PagesRoutingModule,
    ThemeModule,
    NbMenuModule,
    MiscellaneousModule,
    AuthModule.forRoot(),
  ],
  declarations: [
    ...PAGES_COMPONENTS,
    DashboardComponent,
  ],
  providers: [
    PagesMenu,
  ],
})
export class PagesModule {
}
