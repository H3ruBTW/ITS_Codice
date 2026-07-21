import { Component } from "@angular/core";
import { FormsModule } from "@angular/forms";
import { Nota } from "./nota.component";
import { CommonModule } from "@angular/common";
@Component({
    standalone: true,
    selector: "app-form",
    imports: [FormsModule, CommonModule, Nota],
    template: `
        <label>Titolo</label>
        <input type="text" [(ngModel)]="titolo">
        <label>Testo</label>
        <input type="text" [(ngModel)]="testo">
        <button [disabled]="!titolo || !testo" (click)="creaNota()">Aggiungi nota</button>
        <p *ngIf="note.length === 0">Nessuna Nota</p>
        <form-nota *ngFor="let nota of note" [titolo]="nota.titolo" [testo]="nota.testo" (eventoCancella)="rimuoviNota($event)">
    `
})
export class Form{
    titolo = ""
    testo = ""

    note: {titolo: string, testo:string}[] = []

    creaNota(){
        this.note.push({
            titolo: this.titolo,
            testo: this.testo
        })

        this.titolo = ""
        this.testo = ""
    }

    rimuoviNota(nota: {titolo: string, testo:string}){
        console.log("ciao")

        this.note = this.note.filter(e => e.titolo !== nota.titolo && e.testo !== nota.testo)
    }
}