import { Component, Input } from "@angular/core";

@Component({
    standalone: true,
    selector: "form-nota",
    template: `
        <article>
            <header>
                <strong>{{titolo}}</strong>
            </header>
            <p>{{testo}}</p>
        </article>
    `
})
export class Nota {
    @Input() titolo = "Titolo di default"
    @Input() testo = "Testo di default"
}