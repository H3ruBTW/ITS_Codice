import { Component } from "@angular/core";
import { ActivatedRoute } from "@angular/router";

@Component({
    selector: "app-prodotto",
    standalone: true,
    template: `
        <h3>Pagina prodotto con id: {{id}}</h3>
    `
})
export class Prodotto {

    id: number
    
    constructor(private route: ActivatedRoute){
        this.id = route.snapshot.params['id']
    }
}