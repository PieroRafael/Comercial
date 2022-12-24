import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { UsuarioRoutingModule } from './usuario-routing.module';
import { UsuarioComponent } from './usuario.component';
import { CreditarComponent } from './creditar/creditar.component';
import { ListarComponent } from './listar/listar.component';
import { ThemeModule } from '../../../@theme/theme.module';
import { AuthModule } from '../../../@auth/auth.module';
import { ComponentsModule } from '../../../@components/components.module';
import { FormsModule } from '@angular/forms';
import { PermisoComponent } from './permiso/permiso.component';
import {
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbInputModule,
  NbTabsetModule,
  NbUserModule,
  NbRadioModule,
  NbSelectModule,
  NbListModule,
  NbIconModule,
  NbSpinnerModule,
  NbDatepickerModule,
  NbTooltipModule,
} from '@nebular/theme';

const NB_MODULES = [
  NbActionsModule,
  NbButtonModule,
  NbCardModule,
  NbInputModule,
  NbTabsetModule,
  NbUserModule,
  NbRadioModule,
  NbSelectModule,
  NbListModule,
  NbIconModule,
  NbSpinnerModule,
  NbDatepickerModule,
  NbInputModule,
  NbTooltipModule,
];

@NgModule({
  imports: [
    FormsModule,
    ThemeModule,
    AuthModule,
    Ng2SmartTableModule,
    UsuarioRoutingModule,
    ComponentsModule,
    ReactiveFormsModule,
    ...NB_MODULES,
  ],
  declarations: [
    UsuarioComponent,
    ListarComponent,
    CreditarComponent,
    PermisoComponent,
  ],
  entryComponents: [
  ],
  providers: [],
})
export class UsuarioModule { }
