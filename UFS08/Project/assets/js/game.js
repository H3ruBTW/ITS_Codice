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

    renderTable()
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