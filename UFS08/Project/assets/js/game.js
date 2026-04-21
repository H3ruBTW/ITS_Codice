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
giocatore_mazzo = []
bot_mazzo = []

let player_starts = Math.random() >= 0
let player_turn = player_starts

//startGame()

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
            giocatore_carte.push(carta)
            break;
        case "bot":
            img.src = "assets/images/carte/retro.jpg"
            img.alt = "Retro carta"
            bot_carte.push(carta)
            break;
        case "banco_c":
            img.src = "assets/images/carte/" + carta.valore + "_" + carta.seme + ".jpg"
            img.alt = "Carta di valore " + carta.valore + " e di seme " + carta.seme
            banco_carte.push(carta)
            break;
    }

    img.classList.add("carta")

    document.getElementById(contenitore).appendChild(img)
}

function updateCounter(){
    document.getElementById("counter").textContent = mazzo.size
}

let div_banco_cards = null

function turnoGiocatore(){
    /*TODO: Controllo per fine di turno */

    let selectedCard = null
    let button = document.getElementById("confirm")

    div_banco_cards = document.querySelectorAll("#giocatore img")

    let i = 0

    for (const card of div_banco_cards) {
        card.id = i
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

        i++
    }

    button.addEventListener("click", () => playCard(selectedCard))

}

function turnoBot(){
    /*TODO: Controllo per fine di turno */
}

function endOfTurn(){
    if(player_turn){
        player_turn = false
        turnoBot()
    } else {
        player_turn = true
        turnoGiocatore()
    }
}

function endOfTurns(){

}

let moreOptions 

function playCard(carta){
    if(player_turn){
        let options = trovaPrese(giocatore_carte[carta.id], banco_carte)
    }
    //FIXARE ERRORE DELLA CARTA SELEZIONATA CHE NON CORRISPONDE ALLA CARTA NELL'ARRAY

    if(player_turn){
        if(options.length > 0){
            moreOptions = options.length > 1
        } else {
            launchCard(carta, options[0])
        }

        if(moreOptions){

        } else {

        }
    } else {

    }
}

function launchCard(carta, scelta){
    if(player_turn){
        if(scelta === "none"){
            //butta la carta sul banco senza prendere
            banco_carte.push(carta)
            addCard(carta, "banco_c")
            giocatore_carte.splice(carta.id, 1)
        } else {
            div_banco_cards[scelta].remove()
            //rimuove l'elemento dal banco_carte
            giocatore_mazzo.push(carta)
            giocatore_carte.splice(carta.id, 1)

            for(let i=scelta.size-1; i>=0; i--){
                giocatore_mazzo.push(banco_carte[scelta[i]])
                banco_carte.splice(scelta[i], 1)
            }
        }
        

        endOfTurn()
    } else {
    }
}

function showOptions(options){

}

/* Algoritmo di ricerca delle possibili prese sul tavolo con l'uso dei filtri su array
e di ricerxa ricorsiva */
function trovaPrese(cartaValore, tavolo) {
    // Cerca tutte le carte singole con lo stesso valore
    const preseSingole = tavolo
        .map((carta, i) => ({ valore: carta.valore, indice: i }))
        .filter(c => c.valore === cartaValore);

    if (preseSingole.length > 0) {
        // Presa singola obbligatoria → una scelta per ogni carta uguale trovata
        // (il giocatore sceglie quale delle carte uguali prendere)
        return preseSingole.map(c => [c.indice]); // restituisce gli indici
    }

    // Altrimenti cerca combinazioni multiple (somma)
    const combinazioni = [];

    function cerca(indice, corrente, somma) {
        if (somma === cartaValore) {
            combinazioni.push([...corrente]);
            return;
        }

        if (somma > cartaValore || indice >= tavolo.length) 
            return;

        corrente.push(indice);
        cerca(indice + 1, corrente, somma + tavolo[indice].valore);
        corrente.pop();
        cerca(indice + 1, corrente, somma);
    }

    cerca(0, [], 0);
    return combinazioni; // array di array di indici
}