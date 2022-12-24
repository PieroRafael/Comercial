import { UnidadService } from './comercial/backend/services/unidad.service';
import { OtService } from './comercial/backend/services/ot.service';
import { SeguimientospcService } from './comercial/backend/services/seguimientospc.service';
import { SpcService } from './comercial/backend/services/spc.service';
import { ClienteService } from './comercial/backend/services/cliente.service';
import { ComercialBackendModule } from './comercial/backend/comercial-backend.module';
import { ModuleWithProviders, NgModule, Optional, SkipSelf } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MAT_RIPPLE_GLOBAL_OPTIONS } from '@angular/material/core';
import { NbAuthModule } from '@nebular/auth';
import { throwIfAlreadyLoaded } from './module-import-guard';
import {
  LayoutService,
  RippleService,
  StateService,
  ToastService,
} from './sistema/utils';
import { SistemaBackendModule } from './sistema/backend/sistema-backend.module';
import { UsuarioStore } from './sistema/stores/usuario.store';
import { UsuarioService } from './sistema/backend/services/usuario.service';
import { InitUserService } from '../@theme/services/init-user.service';
import { DocumentoClienteService } from './comercial/backend/services/documentocliente.service';
import { DocumentootService } from './comercial/backend/services/documentoot.service';
import { AdicionalotService } from './comercial/backend/services/adicionalot.service';
import { ContactoService } from './comercial/backend/services/contacto.service';

export const NB_CORE_PROVIDERS = [
  ...SistemaBackendModule.forRoot().providers,
  ...ComercialBackendModule.forRoot().providers,

  LayoutService,
  StateService,
  ToastService,
  { provide: MAT_RIPPLE_GLOBAL_OPTIONS, useExisting: RippleService },
];

@NgModule({
  imports: [
    CommonModule,
  ],
  exports: [
    NbAuthModule,
  ],
  declarations: [],
})
export class CoreModule {
  constructor(@Optional() @SkipSelf() parentModule: CoreModule) {
    throwIfAlreadyLoaded(parentModule, 'CoreModule');
  }

  static forRoot(): ModuleWithProviders<CoreModule> {
    return {
      ngModule: CoreModule,
      providers: [
        ...NB_CORE_PROVIDERS,
        UsuarioStore,
        UsuarioService,
        InitUserService,
        ClienteService,
        DocumentoClienteService,
        ContactoService,
        SpcService,
        SeguimientospcService,
        OtService,
        DocumentootService,
        AdicionalotService,
        UnidadService,
      ],
    };
  }
}
