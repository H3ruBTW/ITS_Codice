import { CommonModule } from "@angular/common";
import { HttpClient } from "@angular/common/http";
import { ChangeDetectorRef, Component, inject, OnDestroy, OnInit } from "@angular/core";
import { Subscription } from "rxjs";
import { User } from "./user-component";

interface userData {id:number, username: string, email: string}

@Component({
    selector: "app-api",
    standalone: true,
    imports: [CommonModule, User],
    template: `
    <p>Count: {{ users.length }}</p>
    @for (user of users; track user.id) {
        <api-user [username]="user.username" [email]="user.email" />
    } @empty {
        <p>Errore</p>
    }
    `
})
export class API implements OnInit, OnDestroy {
    http = inject(HttpClient)
    httpSubscription?: Subscription
    cd = inject(ChangeDetectorRef);

    users:userData[] = []

    ngOnInit(): void {
        this.httpSubscription = this.http.get<userData[]>("https://jsonplaceholder.typicode.com/users").subscribe(
            {
                next: (e: userData[]) => {
                    console.log(e)
                    this.users = e
                    console.log(this.users)
                    this.cd.detectChanges()
                },
                error: (e) => {
                    console.log(e)
                }
            }
        )
    }

    ngOnDestroy(): void {
        this.httpSubscription?.unsubscribe()
    }
}