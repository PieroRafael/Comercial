import { Ot } from './../../interfaces/ot';
import { OtApi } from './../api/ot.api';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { OtData } from '../../interfaces/ot';
import { ResponseMensaje } from '../../interfaces/responseMensaje';
import { OtSelect } from '../../interfaces/otSelect';

@Injectable()
export class OtService extends OtData {
  constructor(private api: OtApi) {
    super();
  }

  list(): Observable<Ot[]> {
    return this.api.list();
  }

  listByModal(idot: number):
    Observable<Ot[]> {
    return this.api.listByModal(idot);
  }

  listByOt(idot: number): Observable<Ot[]> {
    return this.api.listByOt(idot);
  }

  listSelect(): Observable<OtSelect[]> {
    return this.api.listSelect();
  }

  get(idot: number): Observable<Ot> {
    return this.api.get(idot);
  }

  createFile(ot) {
    const formData = new FormData();
    formData.append('file', ot.file);
    formData.append('descripcion', null);
    formData.append('listArea', null);
    formData.append('idot', ot.idot);
    return this.api.addFile(formData);
  }

  create(ot): Observable<ResponseMensaje> {
    const fdOt = new FormData();
    fdOt.append('idspc', ot.idspc);
    fdOt.append('codigo', ot.codigo);
    fdOt.append('tipoproyecto', ot.tipoproyecto);
    fdOt.append('otprincipal', ot.otprincipal);
    for (let i = 0; i < ot.listFile.length; i++) {
      fdOt.append('listFile', ot.listFile[i]);
    }

    return this.api.add(fdOt);
  }

  update(ot: Ot): Observable<ResponseMensaje> {
    return this.api.update(ot);
  }

  delete(idot: number): Observable<ResponseMensaje> {
    return this.api.delete(idot);
  }
}
