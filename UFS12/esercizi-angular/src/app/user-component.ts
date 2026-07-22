import { Component, Input } from "@angular/core";


@Component({
    selector: "api-user",
    standalone: true,
    template: `
    <article>
        <header>{{username}}</header>
        <p>Email: {{email}}</p>
    </article>
    `
})
export class User {
    @Input() username: string = ""
    @Input() email: string = ""
}