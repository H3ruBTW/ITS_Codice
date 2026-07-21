import { Component } from "@angular/core";

@Component({
    selector: "app-contatore",
    standalone: true,
    template: `
        <h1>Conta: {{conta}}</h1>

        <button [disabled]="conta === 0" (click)="dec()">Decrementa</button>
        <button (click)="inc()">Incrementa</button>
    `
})
export class Contatore {
    conta = 0

    inc(){
        this.conta++
    }

    dec(){
        this.conta--
    }
}