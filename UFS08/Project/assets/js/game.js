const SEMI   = ["bastoni", "coppe", "denari", "spade"];

class Mazzo {
    carte = [];

    constructor() {
        for (const seme of SEMI)
            for (let valore = 1; valore<=10; valore++)
                this.carte.push({ seme, valore });
    }

    // Fisher-Yates — O(n), il più efficiente possibile
    mescola() {
        for (let i = this.carte.length - 1; i > 0; i--) {
            const j = Math.floor(Math.random() * (i + 1));
            [this.carte[i], this.carte[j]] = [this.carte[j], this.carte[i]];
        }
    }

    pesca() {
        if (this.isEmpty) 
            throw new Error("Mazzo vuoto!");

        return this.carte.pop();         
    }

    get size()    { return this.carte.length; }
    get isEmpty() { return this.carte.length === 0; }
}

const mazzo = new Mazzo();
mazzo.mescola();

let main = document.querySelector("main");

banco_carte = [];
giocatore_carte = [];
bot_carte = [];

let player_starts = Math.random() >= 0
let player_turn = player_starts

startGame()

function startGame(){
    pescataDiInfraturno()

    for(let i=0; i<4; i++)
        addCard(mazzo.pesca(), "banco_c")

    if(player_turn)
        turnoGiocatore()
    else
        turnoBot()

}

function pescataDiInfraturno(){
    if(player_starts){
        for(let i=0; i<3; i++){
            addCard(mazzo.pesca(), "giocatore")
            addCard(mazzo.pesca(), "bot")
        }
    } else {
        for(let i=0; i<3; i++){
            addCard(mazzo.pesca(), "bot")
            addCard(mazzo.pesca(), "giocatore")
        }
    }

    updateCounter()
}

function addCard (carta, contenitore) {

    let div
    const img = document.createElement("img");

    switch (contenitore) {
        case "giocatore":
            img.src = "assets/images/carte/" + carta.valore + "_" + carta.seme + ".jpg"
            img.alt = "Carta di valore " + carta.valore + " e di seme " + carta.seme
            giocatore_carte.push(carta, true)
            break;
        case "bot":
            img.src = "assets/images/carte/retro.jpg"
            img.alt = "Retro carta"
            bot_carte.push(carta, true)
            break;
        case "banco_c":
            img.src = "assets/images/carte/" + carta.valore + "_" + carta.seme + ".jpg"
            img.alt = "Carta di valore " + carta.valore + " e di seme " + carta.seme
            banco_carte.push(carta, true)
            break;
    }

    img.classList.add("carta")

    document.getElementById(contenitore).appendChild(img)
}

function updateCounter(){
    document.getElementById("counter").textContent = mazzo.size
}

function turnoGiocatore(){
    let selectedCard = null
    let button = document.getElementById("confirm")

    let document_cards = document.querySelectorAll("#giocatore img")

    for (const card of document_cards) {
        card.classList.add("toplay")
        card.addEventListener("click", () => {
            if (selectedCard) {
                selectedCard.classList.remove("selected")
                if (selectedCard === card) {
                    selectedCard = null
                    button.disabled = true
                    return
                }
            }

            card.classList.add("selected")
            selectedCard = card
            button.disabled = false
        })
    }
}

function turnoBot(){

}

function endOfTurns(){

}


