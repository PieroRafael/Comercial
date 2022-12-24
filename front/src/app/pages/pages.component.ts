import { Component, OnDestroy } from '@angular/core';
import { takeWhile } from 'rxjs/operators';
import { NbTokenService } from '@nebular/auth';
import { NbMenuItem } from '@nebular/theme';

@Component({
  selector: 'ngx-pages',
  styleUrls: ['pages.component.scss'],
  template: `
    <ngx-one-column-layout>
      <nb-menu [items]="menu"></nb-menu>
      <router-outlet></router-outlet>
    </ngx-one-column-layout>
  `,
})
export class PagesComponent implements OnDestroy {

  menu: NbMenuItem[];
  menuSistema: NbMenuItem[];
  menuComercial: NbMenuItem[];
  menuCliente: NbMenuItem[];
  menuSpc: NbMenuItem[];
  menuOt: NbMenuItem[];
  alive: boolean = true;

  constructor(
    private tokenService: NbTokenService,
  ) {
    this.initMenu();

    this.tokenService.tokenChange()
      .pipe(takeWhile(() => this.alive))
      .subscribe(() => {
        this.initMenu();
      });
  }

  initMenu() {

    this.menuSistema = [];
    this.menuComercial = [];
    this.menuCliente = [];
    this.menuSpc = [];
    this.menuOt = [];

    this.menu = [
      {
        title: 'Inicio',
        icon: 'home-outline',
        link: '/pages/dashboard',
        home: true,
        children: undefined,
      },
    ];

    this.menu.push(
      {
        title: 'Sistema',
        icon: 'layout-outline',
        children: this.menuSistema,
      },
      {
        title: 'Comercial',
        icon: 'layout-outline',
        children: this.menuComercial,
      },
    );

    this.menuSistema.push({
      title: 'Usuarios',
      icon: 'people-outline',
      link: '/pages/usuario/listar',
    });

    this.menuComercial.push(
      {
        title: 'Clientes',
        icon: 'person-outline',
        children: this.menuCliente,
      },
      {
        title: 'SPC',
        icon: 'clipboard-outline',
        children: this.menuSpc,
      },
      {
        title: 'OT',
        icon: 'checkmark-circle-outline',
        children: this.menuOt,
      },
    );

    this.menuCliente.push(
      {
        title: 'Lista de clientes',
        link: '/pages/comercial/cliente/listar',
      },
      {
        title: 'Lista de Contactos',
        link: '/pages/comercial/cliente/representantetodo/listar',
      },
    );

    this.menuSpc.push(
      {
        title: 'Lista de SPC',
        link: '/pages/comercial/spc/listar',
      },
    );

    this.menuOt.push(
      {
        title: 'Lista de OT',
        link: '/pages/comercial/ot/listar',
      },
    );

    /* Menu dinamico */

    /* this.menuSistema = [];

    let usuarios = false;
    let mSistema = false;

    usuario.roles.forEach((rol) => {
      const acc = rol.etiqueta.split('/');
      if (acc[2] === 'usuario' && acc[3] === 'listar') {
        usuarios = true;
        mSistema = true;
      }
    });

    if (mSistema) {
      this.menu.push({
        title: 'Sistema',
        icon: 'layout-outline',
        children: this.menuSistema,
      });
    }

    if (usuarios) {
      this.menuSistema.push({
        title: 'Usuarios',
        icon: 'people-outline',
        link: '/pages/usuario/listar',
      });
    } */
  }

  ngOnDestroy(): void {
    this.alive = false;
  }
}
