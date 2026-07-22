import { Component } from '@angular/core';
import { Contatore } from './contatore.component';
import { Form } from './form.component';
import { API } from "./api-component";
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  imports: [RouterModule],
  template: `
    <header>
      <nav>
        <ul>
          <li><a routerLink="/" routerLinkActive="active" [routerLinkActiveOptions]="{exact:true}">Home</a></li>
          <li><a routerLink="/prodotti" routerLinkActive="active">Prodotti</a></li>
        </ul>
      </nav>
    </header>
    <main>
      <router-outlet />
    </main>
  `,
  styles: `
    .active {
      color: white;
      background-color: cyan
    }
  `,
})
export class App {
  
}
