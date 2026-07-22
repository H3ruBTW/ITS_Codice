import { Component, signal } from '@angular/core';
import { Contatore } from './contatore.component';
import { Form } from './form.component';
import { API } from "./api-component";

@Component({
  selector: 'app-root',
  imports: [API],
  template: `
    <app-api>
  `,
  styles: [],
})
export class App {
  
}
