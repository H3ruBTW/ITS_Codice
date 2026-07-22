import { Routes } from '@angular/router';
import { Home } from './pagine/home.component';
import { Prodotti } from './pagine/prodotti.component';
import { Prodotto } from './pagine/prodotto.component';

export const routes: Routes = [
    {
        path: "",
        component: Home
    },
    {
        path: "prodotti",
        component: Prodotti
    },
    {
        path: "prodotto/:id",
        component: Prodotto
    },
    {
        path: "**",
        redirectTo: ""
    }
];
