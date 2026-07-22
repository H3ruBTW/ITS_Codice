import { Component } from "@angular/core";
import { RouterLink } from "@angular/router";

@Component({
    selector: "app-prodotti",
    standalone: true,
    template: `
        <h3>Pagina prodotti</h3>
        <ul>
            <li><a routerLink="/prodotto/1">Prodotto 1</a></li>
        </ul>
    `,
    imports: [RouterLink]
})
export class Prodotti {

}