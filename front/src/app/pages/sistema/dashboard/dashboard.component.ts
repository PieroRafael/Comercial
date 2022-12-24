import { Component, OnInit } from '@angular/core';
import { UsuarioStore } from '../../../@core/sistema/stores/usuario.store';

@Component({
  selector: 'ngx-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.scss'],
})
export class DashboardComponent implements OnInit {

  constructor(protected usuarioStore: UsuarioStore) { }

  ngOnInit(): void { }
}
