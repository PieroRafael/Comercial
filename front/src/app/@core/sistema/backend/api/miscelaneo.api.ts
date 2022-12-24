import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Area } from '../../interfaces/area';
import { HttpService } from './http.service';

@Injectable()
export class MiscelaneoApi {

  constructor(private api: HttpService) { }

  listSelectArea(): Observable<Area[]> {
    return this.api.get(`area/select`);
  }
}
