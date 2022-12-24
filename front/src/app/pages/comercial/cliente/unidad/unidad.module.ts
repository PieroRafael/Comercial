import { UnidadRoutingModule } from './unidad-routing.module';
import { UnidadComponent } from './unidad.component';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { ThemeModule } from '../../../../@theme/theme.module';
import { AuthModule } from '../../../../@auth/auth.module';
import { ListarComponent} from './listar/listar.component';
import { ComponentsModule } from '../../../../@components/components.module';
import { ChecklistModule } from 'angular-checklist';
import { FormsModule } from '@angular/forms';

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
  NbAutocompleteModule,
} from '@nebular/theme';
import { CreditarComponent } from './creditar/creditar.component';


const  NB_MODULES = [
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
  NbAutocompleteModule,
];

@NgModule({
  imports: [
    FormsModule,
    ChecklistModule,
    ThemeModule,
    AuthModule,
    Ng2SmartTableModule,
    UnidadRoutingModule,
    ComponentsModule,
    ReactiveFormsModule,
    NbAutocompleteModule,
    ...NB_MODULES,
  ],
  declarations: [
    UnidadComponent,
    ListarComponent,
    CreditarComponent,
  ],
  entryComponents: [
  ],
  providers: [],
})
export class UnidadModule { }
