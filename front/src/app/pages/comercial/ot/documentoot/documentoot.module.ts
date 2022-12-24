import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { ThemeModule } from '../../../../@theme/theme.module';
import { DocumentootRoutingModule } from './documentoot-routing.module';
import { AuthModule } from '../../../../@auth/auth.module';
import { DocumentootComponent } from './documentoot.component';
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
    DocumentootRoutingModule,
    ComponentsModule,
    ReactiveFormsModule,
    ...NB_MODULES,
  ],
  declarations: [
    DocumentootComponent,
    ListarComponent,
    CreditarComponent,
  ],
  entryComponents: [
  ],
  providers: [],
})
export class DocumentootModule { }
