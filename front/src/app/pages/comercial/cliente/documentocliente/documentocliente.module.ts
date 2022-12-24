import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { Ng2SmartTableModule } from 'ng2-smart-table';
import { ThemeModule } from '../../../../@theme/theme.module';
import { DocumentoclienteRoutingModule } from './documentocliente-routing.module';
import { AuthModule } from '../../../../@auth/auth.module';
import { DocumentoclienteComponent } from './documentocliente.component';
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
];

@NgModule({
  imports: [
    FormsModule,
    ChecklistModule,
    ThemeModule,
    AuthModule,
    Ng2SmartTableModule,
    DocumentoclienteRoutingModule,
    ComponentsModule,
    ReactiveFormsModule,
    ...NB_MODULES,
  ],
  declarations: [
    DocumentoclienteComponent,
    ListarComponent,
    CreditarComponent,
  ],
  entryComponents: [
  ],
  providers: [],
})
export class DocumentoclienteModule { }
