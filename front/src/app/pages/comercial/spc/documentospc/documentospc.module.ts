import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { ThemeModule } from '../../../../@theme/theme.module';
import { DocumentospcRoutingModule } from './documentospc-routing.module';
import { AuthModule } from '../../../../@auth/auth.module';
import { DocumentospcComponent } from './documentospc.component';
import { ListarComponent} from './listar/listar.component';
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
import { ModaldocumentospcComponent } from './modaldocumentospc/modaldocumentospc.component';


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
];

@NgModule({
  imports: [
    FormsModule,
    ChecklistModule,
    ThemeModule,
    AuthModule,
    Ng2SmartTableModule,
    DocumentospcRoutingModule,
    ComponentsModule,
    ReactiveFormsModule,
    ...NB_MODULES,
  ],
  declarations: [
    DocumentospcComponent,
    ListarComponent,
    CreditarComponent,
    ModaldocumentospcComponent,
  ],
  entryComponents: [
  ],
  providers: [],
})
export class DocumentospcModule { }
