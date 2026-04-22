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

let banco_carte = [];
let giocatore_carte = [];
let bot_carte = [];
let giocatore_mazzo = []
let bot_mazzo = []
let div_banco_cards = null
let div_giocatore_cards = null
let div_bot_cards = null

let player_starts = Math.random() >= 0
let player_turn = player_starts

startGame()

function startGame(){
    pescataDiInfraturno()

    for(let i=0; i<4; i++)
        addCard(mazzo.pesca(), "banco_c")

    updateCounterBanco()

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

    updateCounterBanco()
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

function updateCounterBanco(){
    document.getElementById("counterBanco").textContent = mazzo.size
}

function updateCounterGioc(){
    
    //rimuove il div mazzetto vuoto e mette retro.jpg
    let div_mazzo = document.getElementsByClassName("gioc_mazzetto")
    div_mazzo[0].innerHTML = ""
    const img = document.createElement("img");
    img.src = "assets/images/carte/retro.jpg"
    img.alt = "Retro carta"
    img.classList.add("carta")
    div_mazzo[0].appendChild(img)
    div_mazzo[0].innerHTML += "<span id='counterG'>" + giocatore_mazzo.length + "</span>"
}

function updateCounterBot(){
    
    //rimuove il div mazzetto vuoto e mette retro.jpg
    let div_mazzo = document.getElementsByClassName("bot_mazzetto")
    div_mazzo[0].innerHTML = ""
    const img = document.createElement("img");
    img.src = "assets/images/carte/retro.jpg"
    img.alt = "Retro carta"
    img.classList.add("carta")
    div_mazzo[0].appendChild(img)
    div_mazzo[0].innerHTML += "<span id='counterBot'>" + giocatore_mazzo.length + "</span>"
}


function turnoGiocatore(){
    let selectedCard = null
    let button = document.getElementById("confirm")
    let newButton = null

    div_giocatore_cards = document.querySelectorAll("#giocatore img")

    div_giocatore_cards.forEach(c => {
        c.classList.remove("toplay", "selected")
    })

    newButton = button.cloneNode(true)
    button.replaceWith(newButton)
    newButton.addEventListener("click", () =>  {
        if(!selectedCard) return
        console.log("selectedCard:", selectedCard)
        console.log("selectedCard.id:", selectedCard.id)
        console.log("giocatore_carte:", giocatore_carte)
    
        const cartaDaGiocare = selectedCard  // ← salva prima
        selectedCard = null                  // ← resetta subito
        newButton.disabled = true
        playCard(cartaDaGiocare) 
    })

    let i = 0

    for (const card of div_giocatore_cards) {
        card.id = i
        card.classList.add("toplay")
        card.addEventListener("click", () => {
            if (selectedCard) {
                selectedCard.classList.remove("selected")
                if (selectedCard === card) {
                    selectedCard = null
                    newButton.disabled = true
                    return
                }
            }

            card.classList.add("selected")
            selectedCard = card
            newButton.disabled = false
        })

        i++
    }

    

}

function turnoBot(){
    div_bot_cards = document.querySelectorAll("#bot img")

    const mossa = calcolaMossaBot();

    setTimeout(() => {
        div_banco_cards = document.querySelectorAll("#banco_c img")
        launchCard(mossa.carta, mossa.scelta);
    }, 1000);
}

function endOfTurn(){
    if(giocatore_carte.length === 0 && bot_carte.length === 0){
        if(mazzo.isEmpty)
            endOfGame()
        else{
            endOfTurns()
        }
            
        return
    }

    if(player_turn){
        player_turn = false
        turnoBot()
    } else {
        player_turn = true
        turnoGiocatore()
    }
}

function endOfTurns(){
    pescataDiInfraturno()

    if(player_starts){
        player_turn = true
        turnoGiocatore()
    }else{
        player_turn = false
        turnoBot()
    }
        
}

function endOfGame(){}

let moreOptions 

function playCard(carta){
    moreOptions = false
    div_banco_cards = document.querySelectorAll("#banco_c img")

    let options = trovaPrese(giocatore_carte[carta.id].valore, banco_carte)
    if(options.length === 0){
        launchCard(carta, "none")
        return
    }

    moreOptions = options.length > 1

    if(moreOptions){
        showOptions(carta, options)
    } else {
        launchCard(carta, options[0])
    }
    
}

function launchCard(carta, scelta){
    div_banco_cards = document.querySelectorAll("#banco_c img")
    let card

    if(player_turn){
        card = giocatore_carte[carta.id]

        if(scelta === "none"){
            addCard(card, "banco_c")
            giocatore_carte[carta.id] = null
            div_giocatore_cards[carta.id].remove()
        } else {
            const presaOrdinata = [...scelta].sort((a, b) => b - a) // ← QUI
            
            div_giocatore_cards[carta.id].remove()
            giocatore_mazzo.push(card)
            giocatore_carte[carta.id] = null

            for(let i = 0; i < presaOrdinata.length; i++){
                giocatore_mazzo.push(banco_carte[presaOrdinata[i]])
                banco_carte.splice(presaOrdinata[i], 1)
                div_banco_cards[presaOrdinata[i]].remove()
            }

            if(banco_carte.length === 0) scopa(player_turn)

            updateCounterGioc()
        }

    } else {
        card = bot_carte[carta.id]

        if(scelta === "none"){
            addCard(card, "banco_c")
            bot_carte.splice(carta.id, 1)
            div_bot_cards[carta.id].remove()
        } else {
            const presaOrdinata = [...scelta].sort((a, b) => b - a) // ← QUI

            div_bot_cards[carta.id].src = `assets/images/carte/${card.valore}_${card.seme}.jpg`
            div_bot_cards[carta.id].remove()
            bot_mazzo.push(card)
            bot_carte.splice(carta.id, 1)

            for(let i = 0; i < presaOrdinata.length; i++){
                bot_mazzo.push(banco_carte[presaOrdinata[i]])
                banco_carte.splice(presaOrdinata[i], 1)
                div_banco_cards[presaOrdinata[i]].remove()
            }

            if(banco_carte.length === 0) scopa(player_turn)

            updateCounterBot()
        }
    }

    endOfTurn()

}

function showOptions(carta, options){
    let optionsContainer = document.getElementsByClassName("div_options")
    let optionsList = document.getElementById("opzioni")

    optionsList.innerHTML = ""

    for(let i=0; i<options.length; i++){
        let option_button = document.createElement("button")

        for(let j=0; j<options[i].length; j++){
            if(j>0)
                option_button.textContent += " + "
            option_button.textContent += banco_carte[options[i][j]].valore + " " + banco_carte[options[i][j]].seme + " "
        }

        option_button.classList.add("play_button")
        optionsList.appendChild(option_button)

        option_button.addEventListener("click", () => launchCardOption(carta, options[i]))
    }

    optionsContainer[0].style.visibility = "visible"
}

function launchCardOption(carta, scelta){
    let optionsContainer = document.getElementsByClassName("div_options")
    optionsContainer[0].style.visibility = "hidden"
    launchCard(carta, scelta)
}

let scope_giocatore = 0
let scope_bot = 0

function scopa(isPlayer){
    if(isPlayer){
        scope_giocatore++
        // TODO: animazione/feedback visivo
        console.log("SCOPA del giocatore!", scope_giocatore)
    } else {
        scope_bot++
        console.log("SCOPA del bot!", scope_bot)
    }
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

/* Logica del Bot */
function calcolaMossaBot() {
    let migliorMossa  = null;
    let migliorScore  = -Infinity;

    for (let i = 0; i < bot_carte.length; i++) {
        const carta   = bot_carte[i];
        const opzioni = trovaPrese(carta.valore, banco_carte);

        if (opzioni.length === 0) {
            const score = valutaButto(carta);
            if (score > migliorScore) {
                migliorScore = score;
                migliorMossa = { carta: { id: i }, scelta: "none" };
            }
        } else {
            for (const presa of opzioni) {
                const score = valutaPresa(carta, presa);
                if (score > migliorScore) {
                    migliorScore = score;
                    migliorMossa = { carta: { id: i }, scelta: presa };
                }
            }
        }
    }

    return migliorMossa;
}

function valutaPresa(carta, presa) {
    let score = 0;

    const cartePrese  = presa.map(i => banco_carte[i]);
    const bancoRimasto = banco_carte.filter((_, i) => !presa.includes(i));

    // Scopa
    if (bancoRimasto.length === 0) score += 100;

    // Numero di carte prese
    score += presa.length * 10;

    // Sette bello
    if (carta.seme === "denari" && carta.valore === 7) score += 30;
    if (cartePrese.some(c => c.seme === "denari" && c.valore === 7)) score += 30;

    // Denari
    if (carta.seme === "denari") score += 5;
    score += cartePrese.filter(c => c.seme === "denari").length * 5;

    // ❌ RIMOSSO — il bot non guarda più le tue carte
    // if (bancoRimasto.length === 1) { ...giocatore_carte... }

    score -= carta.valore;

    return score;
}

function valutaButto(carta) {
    let score = 0;

    // ❌ RIMOSSO — il bot non guarda più le tue carte
    // const bancoDopoMossa = [...banco_carte, carta];
    // const scopaAvversario = giocatore_carte.some(...)

    if (carta.seme === "denari") score -= 15;
    if (carta.valore === 7)      score -= 10;

    score -= carta.valore;

    return score;
}