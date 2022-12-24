import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { ThemeModule } from '../../../../@theme/theme.module';
import { AuthModule } from '../../../../@auth/auth.module';
import { ListarComponent } from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';
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
} from '@nebular/theme';
import { AdicionalComponent } from './adicional.component';
import { AdicionalRoutingModule } from './adicional-routing.module';

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
  NbSelectModule,
  NbTooltipModule,
];

@NgModule({
  imports: [
    FormsModule,
    ChecklistModule,
    ThemeModule,
    AuthModule,
    Ng2SmartTableModule,
    AdicionalRoutingModule,
    ComponentsModule,
    ReactiveFormsModule,
    ...NB_MODULES,
  ],
  declarations: [
    AdicionalComponent,
    ListarComponent,
    CreditarComponent,
  ],
  entryComponents: [
  ],
  providers: [],
})
export class AdicionalModule { }
