import { ChangeDetectorRef, Component, inject } from "@angular/core";
import { ActivatedRoute } from "@angular/router";
import { Subscription } from "rxjs";
import { ApiCall } from "../servizi/dummy.service";
import { ProdottoStruct } from "../modelli/product.model";

@Component({
    selector: "app-prodotto",
    standalone: true,
    template: `
        <h3>Pagina prodotto con id: {{id}}</h3>
        <article>
            <header>{{prodotto?.title}}</header>
            <img [src]="prodotto?.thumbnail" />
        </article>
    `
})
export class Prodotto {

    id: number
    httpSubscription?: Subscription
    api = inject(ApiCall)
    prodotto?: ProdottoStruct
    
    constructor(private route: ActivatedRoute, private cdr: ChangeDetectorRef){
        this.id = route.snapshot.params['id']
    }

    ngOnInit(): void {
        this.httpSubscription = this.api.getProductById(this.id).subscribe({
            next: (e) => {
                this.prodotto = e
                this.cdr.detectChanges();
            }
        })
    }
}