import { ChangeDetectorRef, Component, inject, OnInit } from "@angular/core";
import { RouterLink } from "@angular/router";
import { Subscription } from "rxjs";
import { ApiCall } from "../servizi/dummy.service";
import { ProdottoStruct } from "../modelli/product.model";



@Component({
    selector: "app-prodotti",
    standalone: true,
    template: `
        <h3>Pagina prodotti</h3>
        <ul>
            @for (prod of prodotti; track prod.id) {
                <li><a [routerLink]="['/prodotto', prod.id]">{{prod.title}}</a></li>
            }
        </ul>
    `,
    imports: [RouterLink]
})
export class Prodotti implements OnInit {
    httpSubscription?: Subscription
    api = inject(ApiCall)
    prodotti: ProdottoStruct[] = []

    constructor(private cdr: ChangeDetectorRef) {}

    ngOnInit(): void {
        this.httpSubscription = this.api.getAllProducts().subscribe({
            next: (e) => {
                this.prodotti = e.products
                this.cdr.detectChanges();
            }
        })
    }
}