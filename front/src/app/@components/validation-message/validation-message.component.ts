import {Component, Input, forwardRef} from '@angular/core';
import {NG_VALUE_ACCESSOR} from '@angular/forms';

@Component({
  selector: 'ngx-validation-message',
  styleUrls: ['./validation-message.component.scss'],
  template: `
      <div class="warning">
          <span class="caption status-danger"
             *ngIf="showMinLength"> {{ label }} debe tener como mínimo {{ minLength }} caracteres </span>
          <span class="caption status-danger"
             *ngIf="showMaxLength"> {{ label }} debe tener como máximo {{ maxLength }} caracteres </span>
          <span class="caption status-danger" *ngIf="showPattern"> {{ label }} incorrecto </span>
          <span class="caption status-danger" *ngIf="showRequired"> Debe ingresar un valor para, {{ label }} </span>
          <span class="caption status-danger" *ngIf="showMin"> El valor mínimo para {{ label }} es {{ min }} </span>
          <span class="caption status-danger" *ngIf="showMax"> El valor máximo para {{ label }} es {{ max }} </span>
      </div>
  `,
  providers: [
    {
      provide: NG_VALUE_ACCESSOR,
      useExisting: forwardRef(() => NgxValidationMessageComponent),
      multi: true,
    },
  ],
})
export class NgxValidationMessageComponent {
  @Input()
  label: string = '';

  @Input()
  showRequired?: boolean;

  @Input()
  min?: number;

  @Input()
  showMin?: boolean;

  @Input()
  max?: number;

  @Input()
  showMax: boolean;

  @Input()
  minLength?: number;

  @Input()
  showMinLength?: boolean;

  @Input()
  maxLength?: number;

  @Input()
  showMaxLength?: boolean;

  @Input()
  showPattern?: boolean;
}
