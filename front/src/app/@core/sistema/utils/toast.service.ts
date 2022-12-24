import { Injectable } from '@angular/core';
import { NbToastrService } from '@nebular/theme';

@Injectable()
export class ToastService {

  constructor(
    private toastrService: NbToastrService) { }

  showSeleccion() {
    this.showToastApi('top-right', 'Advertencia', 'Seleccione un registro', 'warning');
  }

  showCorrecto(mensaje) {
    this.showToastApi('top-right', 'Correcto', mensaje, 'success');
  }

  showAdvertencia(mensaje) {
    this.showToastApi('top-right', 'Advertencia', mensaje, 'warning');
  }

  showError() {
    this.showToastApi('top-right', 'Error',
      'Evento inesperado, comuníquese con el administrador del sistema', 'danger');
  }

  showToastIcono(titulo, mensaje, status) {
    this.showToastIconoApi('top-right', titulo, mensaje, status);
  }

  showToast(titulo, mensaje, status) {
    this.showToastApi('top-right', titulo, mensaje, status);
  }

  showToastIconoApi(position, titulo, mensaje, status) {
    this.toastrService.show(mensaje, titulo, { position, status, icon: '' });
  }

  showToastApi(position, titulo, mensaje, status) {
    this.toastrService.show(mensaje, titulo, { position, status });
  }
}
