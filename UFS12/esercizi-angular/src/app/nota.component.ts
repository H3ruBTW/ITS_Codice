import { Component, EventEmitter, Input, Output } from "@angular/core";
import { CommonModule } from "@angular/common";

@Component({
    standalone: true,
    selector: "form-nota",
    imports: [CommonModule],
    template: `
        <article>
            <header>
                <strong>{{titolo}}</strong>
            </header>
            <p>{{testo}}</p>
            <a (click)="rimuovi($event)" style="cursor: pointer">Rimuovi</a>
        </article>
    `,
})
export class Nota {
    @Input() titolo = "Titolo di default"
    @Input() testo = "Testo di default"
    @Output() eventoCancella = new EventEmitter<{titolo: string, testo: string}>()

    rimuovi(e: Event){
        e.preventDefault()
        const ok = confirm("VUoi cancellare la nota?")

        if(ok)
            this.eventoCancella.emit({titolo: this.titolo, testo: this.testo})
    }
}