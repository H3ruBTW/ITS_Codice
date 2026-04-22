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

startGame();

function startGame() {
    distribuisci()
    
    for(let i=0; i<4; i++)
        banco_carte.push(mazzo.pesca())

    document.getElementById("confirm").addEventListener("click", () => {
        if (!carta_selezionata) return  // safety check
        
        confermaCarta()
    })

    renderTable()

    gestisciTurno()
}

function distribuisci(){
    for(let i=0; i<3; i++)
        if(player_starts){
            giocatore_carte.push(mazzo.pesca())
            bot_carte.push(mazzo.pesca())
        } else {
            bot_carte.push(mazzo.pesca())
            giocatore_carte.push(mazzo.pesca())
        }
}

function renderTable(){
    //div dove sono presenti le carte
    let div_bot = document.getElementById("bot")
    let div_gioc = document.getElementById("giocatore")
    let div_banco = document.getElementById("banco_c")

    div_bot.innerHTML = ""
    div_banco.innerHTML = ""
    div_gioc.innerHTML = ""

    let img

    bot_carte.forEach(element => {
        img = document.createElement("img")

        img.src = "assets/images/carte/retro.jpg"
        img.alt = "Retro carta"

        img.classList.add("carta")

        div_bot.appendChild(img)
    });

    giocatore_carte.forEach(element =>{
        img = document.createElement("img")

        img.src = "assets/images/carte/" + element.valore + "_" + element.seme + ".jpg"
        img.alt = "Carta di valore " + element.valore + " e di seme " + element.seme

        img.classList.add("carta")
        
        div_gioc.appendChild(img)
    })

    banco_carte.forEach(element => {
        img = document.createElement("img")

        img.src = "assets/images/carte/" + element.valore + "_" + element.seme + ".jpg"
        img.alt = "Carta di valore " + element.valore + " e di seme " + element.seme

        img.classList.add("carta")

        div_banco.appendChild(img)
    })

    //Banco
    let counter_mazzo = document.getElementById("counterBanco")
    counter_mazzo.textContent = mazzo.size

    //Giocatore
    let div_mazzo = document.getElementsByClassName("gioc_mazzetto")
    div_mazzo[0].innerHTML = ""
    img = document.createElement("img");

    if(giocatore_mazzo.length > 0){
        img.src = "assets/images/carte/retro.jpg"
        img.alt = "Retro carta"
        img.classList.add("carta")
        div_mazzo[0].appendChild(img)
    } else {
        div_mazzo[0].innerHTML += "<div class=\"mazzetto_vuoto\"></div>"
    }

    div_mazzo[0].innerHTML += "<span id='counterG'>" + giocatore_mazzo.length + "</span>"

    //Bot
    div_mazzo = document.getElementsByClassName("bot_mazzetto")
    div_mazzo[0].innerHTML = ""
    img = document.createElement("img");
    
    if(bot_mazzo.length > 0){
        img.src = "assets/images/carte/retro.jpg"
        img.alt = "Retro carta"
        img.classList.add("carta")
        div_mazzo[0].appendChild(img)
    } else {
        div_mazzo[0].innerHTML += "<div class=\"mazzetto_vuoto\"></div>"
    }

    div_mazzo[0].innerHTML += "<span id='counterBot'>" + bot_mazzo.length + "</span>"
}

function giocatoreGioca(){
    const imgs = document.querySelectorAll("#giocatore img")

    giocatore_carte.forEach((element, i) => {
        imgs[i].classList.add("toplay")
        imgs[i].addEventListener("click", () => {
            if (carta_selezionata?.element === element) {
                imgs[i].classList.remove("selected")
                carta_selezionata = null
            } else {
                if (carta_selezionata)
                    carta_selezionata.img.classList.remove("selected")

                imgs[i].classList.add("selected")
                carta_selezionata = { element, img: imgs[i] }

            }
            aggiornaBottone()
        })
    })
}

function aggiornaBottone() {
    const btn = document.getElementById("confirm")
    btn.disabled = !(player_turn && carta_selezionata !== null)
}

function gestisciTurno(){
    carta_selezionata = null
    aggiornaBottone()

    if (player_turn) {
        console.log("Turno: GIOCATORE")
        giocatoreGioca()
    } else {
        console.log("Turno: BOT")
        setTimeout(botGioca, 800)
    }
}

function calcolaPrese(carta, banco) {
    const target = carta.valore

    // priorità: prese singole
    const singole = banco.filter(c => c.valore === target)
    if (singole.length > 0)
        return singole.map(c => [c])

    // combinazioni di 2+ carte
    const risultati = []
    function cerca(indice, correnti, somma) {
        if (somma === target && correnti.length > 1) {
            risultati.push([...correnti])
            return
        }
        if (somma > target || indice >= banco.length) return
        cerca(indice + 1, [...correnti, banco[indice]], somma + banco[indice].valore)
        cerca(indice + 1, correnti, somma)
    }
    cerca(0, [], 0)
    return risultati
}

function confermaCarta() {
    if (!carta_selezionata) return

    const carta = carta_selezionata.element
    const prese = calcolaPrese(carta, banco_carte)

    console.log(carta)

    if (prese.length === 0) {
        cartaAlBanco(carta)         
    } else if (prese.length === 1) {
        eseguiPresa(carta, prese[0]) 
    } else {
        mostraOpzioni(carta, prese)
    }
}

function cartaAlBanco(carta) {
    // rimuove la carta dalla mano del giocatore
    giocatore_carte = giocatore_carte.filter(c => c !== carta)
    
    // aggiunge la carta al banco
    banco_carte.push(carta)

    carta_selezionata = null
    player_turn = false

    renderTable()
    gestisciTurno()
}

function eseguiPresa(carta, scelta){
    giocatore_mazzo.push(carta)
    
    scelta.forEach(element => {
        giocatore_mazzo.push(element)
    })

    giocatore_carte = giocatore_carte.filter(c => c !== carta)

    banco_carte = banco_carte.filter(c => !scelta.includes(c))

    carta_selezionata = null
    player_turn = false

    renderTable()
    gestisciTurno()
}

function mostraOpzioni(carta, scelte){
    let div_options = document.getElementsByClassName("div_options")
    let opzioni_disp = document.getElementById("opzioni")

    opzioni_disp.innerHTML = ""

    scelte.forEach(scelta =>{
        const elem_scelta = document.createElement("button")

        for(let i=0; i<scelta.length; i++){
            if(i>0)
                elem_scelta.textContent += " + "
            elem_scelta.textContent += scelta[i].valore + " " + scelta[i].seme + " "
        }

        elem_scelta.classList.add("play_button")
        opzioni_disp.appendChild(elem_scelta)

        elem_scelta.addEventListener("click", () => {
            div_options[0].style.visibility = "hidden"
            eseguiPresa(carta, scelta)
        })
    })

    div_options[0].style.visibility = "visible"
}