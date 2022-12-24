import { UnidadService } from './services/unidad.service';
import { UnidadApi } from './api/unidad.api';
import { SeguimientospcApi } from './api/seguimientospc.api';
import { SeguimientospcService } from './services/seguimientospc.service';
import { AdicionalotService } from './services/adicionalot.service';
import { AdicionalotApi } from './api/adicionalot.api';
import { DocumentootService } from './services/documentoot.service';
import { DocumentootApi } from './api/documentoot.api';
import { OtService } from './services/ot.service';
import { SpcApi } from './api/spc.api';
import { SpcService } from './services/spc.service';
import { DocumentoClienteApi } from './api/documentocliente.api';
import { DocumentoClienteService } from './services/documentocliente.service';
import { ClienteApi } from './api/cliente.api';
import { ClienteService } from './services/cliente.service';
import { ModuleWithProviders, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ClienteData } from '../interfaces/cliente';
import { HttpService } from './api/http.service';
import { NbAuthModule } from '@nebular/auth';
import { DocumentoClienteData } from '../interfaces/documentocliente';
import { SpcData } from '../interfaces/spc';
import { OtApi } from './api/ot.api';
import { OtData } from '../interfaces/ot';
import { DocumentootData } from '../interfaces/documentoot';
import { AdicionalotData } from '../interfaces/adicionalot';
import { ContactoData } from '../interfaces/contacto';
import { ContactoApi } from './api/contacto.api';
import { ContactoService } from './services/contacto.service';
import { SeguimientospcData } from '../interfaces/seguimientospc';
import { UnidadData } from '../interfaces/unidad';

const API = [ClienteApi, HttpService, DocumentoClienteApi, ContactoApi, SpcApi,
  SeguimientospcApi, OtApi, DocumentootApi, AdicionalotApi, UnidadApi];

const SERVICES = [
  { provide: ClienteData, useClass: ClienteService },
  { provide: DocumentoClienteData, useClass: DocumentoClienteService },
  { provide: ContactoData, useClass: ContactoService },
  { provide: SpcData, useClass: SpcService },
  { provide: SeguimientospcData, useClass: SeguimientospcService },
  { provide: OtData, useClass: OtService },
  { provide: DocumentootData, useClass: DocumentootService },
  { provide: AdicionalotData, useClass: AdicionalotService },
  { provide: UnidadData, useClass: UnidadService },
];

@NgModule({
  imports: [CommonModule, NbAuthModule],
})
export class ComercialBackendModule {
  static forRoot(): ModuleWithProviders<ComercialBackendModule> {
    return {
      ngModule: ComercialBackendModule,
      providers: [
        ...API,
        ...SERVICES,
      ],
    };
  }
}
