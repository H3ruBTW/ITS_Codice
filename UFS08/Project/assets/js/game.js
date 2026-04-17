const SEMI   = ["bastoni", "coppe", "denari", "spade"];

class Mazzo {
    #carte = [];

    constructor() {
        for (const seme of SEMI)
            for (let valore = 1; valore<=10; valore++)
                this.#carte.push({ seme, valore });
    }

    // Fisher-Yates — O(n), il più efficiente possibile
    mescola() {
        for (let i = this.#carte.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [this.#carte[i], this.#carte[j]] = [this.#carte[j], this.#carte[i]];
        }
    }

    pesca() {
        if (this.isEmpty) throw new Error("Mazzo vuoto!");
        return this.#carte.pop();         
    }

    guarda() {
        if (this.isEmpty) throw new Error("Mazzo vuoto!");
        return this.#carte.at(-1);         
    }

    get size()    { return this.#carte.length; }
    get isEmpty() { return this.#carte.length === 0; }

    toString() {
        return this.#carte
            .map(c => `${c.valore} di ${c.seme}`)
            .join("\n");
    }
}

// ── Utilizzo ─────────────────────────────────
const mazzo = new Mazzo();
mazzo.mescola();

let main = document.querySelector("main");

banco_carte = [];
giocatore_carte = [];
bot_carte = [];

let player_starts = Math.random() >= 0.5

function startGame(){
    pescataDiInfraturno()

    for(let i=0; i<4; i++)
        banco_carte.push(mazzo.pesca())
}

function pescataDiInfraturno(){
    if(player_starts){
        for(let i=0; i<3; i++){
            giocatore_carte.push(mazzo.pesca())
            bot_carte.push(mazzo.pesca())
        }
    } else {
        for(let i=0; i<3; i++){
            bot_carte.push(mazzo.pesca())
            giocatore_carte.push(mazzo.pesca())
        }
    }
}




