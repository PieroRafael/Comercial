import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Area } from '../../interfaces/area';
import { MiscelaneoData } from '../../interfaces/miscelaneo';
import { MiscelaneoApi } from '../api/miscelaneo.api';

@Injectable()
export class MiscelaneoService extends MiscelaneoData {

  constructor(private api: MiscelaneoApi) {
    super();
  }

  listSelectArea(): Observable<Area[]> {
    return this.api.listSelectArea();
  }
}
