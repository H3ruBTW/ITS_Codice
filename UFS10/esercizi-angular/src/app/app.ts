import { Component, signal } from '@angular/core';
import { Contatore } from './contatore.component';
import { Form } from './form.component';

@Component({
  selector: 'app-root',
  imports: [Form],
  template: `
    <app-form>
  `,
  styles: [],
})
export class App {
  
}
