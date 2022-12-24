import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { ThemeModule } from '../../../@theme/theme.module';
import { OtRoutingModule } from './ot-routing.module';
import { AuthModule } from '../../../@auth/auth.module';
import { OtComponent } from './ot.component';
import { ListarComponent} from './listar/listar.component';
import { CreditarComponent } from './creditar/creditar.component';
import { ComponentsModule } from '../../../@components/components.module';
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
  NbCheckboxModule,
  NbTooltipModule,
} from '@nebular/theme';
import { ModaldetalleComponent } from './modaldetalle/modaldetalle.component';

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
  NbCheckboxModule,
  NbTooltipModule,
];

@NgModule({
  imports: [
    FormsModule,
    ChecklistModule,
    ThemeModule,
    AuthModule,
    Ng2SmartTableModule,
    OtRoutingModule,
    ComponentsModule,
    ReactiveFormsModule,
    ...NB_MODULES,
  ],
  declarations: [
    OtComponent,
    ListarComponent,
    CreditarComponent,
    ModaldetalleComponent,
  ],
  entryComponents: [
  ],
  providers: [],
})
export class OtModule { }
