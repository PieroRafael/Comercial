import { NbMenuItem } from '@nebular/theme';
import { Observable, of } from 'rxjs';
import { Injectable } from '@angular/core';

@Injectable()
export class PagesMenu {

  constructor() {}

  getMenu(): Observable<NbMenuItem[]> {
    const dashboardMenu: NbMenuItem[] = [
      {
        title: 'Inicio',
        icon: 'home-outline',
        link: '/pages/dashboard',
        home: true,
        children: undefined,
      },
    ];

    const menu: NbMenuItem[] = [];

    return of([...dashboardMenu, ...menu]);
  }
}
