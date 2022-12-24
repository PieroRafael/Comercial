import { Component } from '@angular/core';

@Component({
  selector: 'ngx-footer',
  styleUrls: ['./footer.component.scss'],
  template: `
    <span class="created-by">By <b>
      <a href="https://scing.com" target="_blank">SC Ingeniería y Construcción</a></b> {{ currentYear }}</span>
  `,
})
export class FooterComponent {
  get currentYear(): number {
    return new Date().getFullYear();
  }
}
