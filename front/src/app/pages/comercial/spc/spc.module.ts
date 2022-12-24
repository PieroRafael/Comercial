import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { ThemeModule } from '../../../@theme/theme.module';
import { SpcsRoutingModule } from './spc-routing.module';
import { AuthModule } from '../../../@auth/auth.module';
import { SpcComponent } from './spc.component';
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
  NbAccordionModule,
  NbAutocompleteModule,
  NbTooltipModule,
  NbStepperModule,
} from '@nebular/theme';
import { ModalspcComponent } from './modalspc/modalspc.component';
import { ModaldetalleComponent } from './modaldetalle/modaldetalle.component';
import { ModalpresComponent } from './modalpres/modalpres.component';

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
  Ng2SmartTableModule,
  NbAccordionModule,
  NbAutocompleteModule,
  NbTooltipModule,
  NbStepperModule,
];

@NgModule({
  imports: [
    FormsModule,
    ChecklistModule,
    ThemeModule,
    AuthModule,
    Ng2SmartTableModule,
    SpcsRoutingModule,
    ComponentsModule,
    ReactiveFormsModule,
    NbStepperModule,
    ...NB_MODULES,
  ],
  declarations: [
    SpcComponent,
    ListarComponent,
    CreditarComponent,
    ModalspcComponent,
    ModaldetalleComponent,
    ModalpresComponent,
  ],
  entryComponents: [
  ],
  providers: [],
})
export class SpcModule { }
