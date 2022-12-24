import { ModuleWithProviders, NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UsuarioData } from '../interfaces/usuario';
import { UsuarioService } from './services/usuario.service';
import { UsuarioApi } from './api/usuario.api';
import { MiscelaneoData } from '../interfaces/miscelaneo';
import { MiscelaneoService } from './services/miscelaneo.service';
import { MiscelaneoApi } from './api/miscelaneo.api';
import { HttpService } from './api/http.service';
import { NbAuthModule } from '@nebular/auth';

const API = [UsuarioApi, MiscelaneoApi, HttpService];

const SERVICES = [
  { provide: UsuarioData, useClass: UsuarioService },
  { provide: MiscelaneoData, useClass: MiscelaneoService },
];

@NgModule({
  imports: [CommonModule, NbAuthModule],
})
export class SistemaBackendModule {
  static forRoot(): ModuleWithProviders<SistemaBackendModule> {
    return {
      ngModule: SistemaBackendModule,
      providers: [
        ...API,
        ...SERVICES,
      ],
    };
  }
}
