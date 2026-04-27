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

let player_starts = Math.random() >= 0.5
let player_turn = player_starts

let ultima_presa_giocatore = false

let punti_giocatore = {primiera: 0, "sette bello": 0, carte: 0, ori: 0, scope: 0}
let punti_bot = {primiera: 0, "sette bello": 0, carte: 0, ori: 0, scope: 0}

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

    if(giocatore_carte.length === 0 && bot_carte.length === 0){
        if(mazzo.isEmpty){
            if(ultima_presa_giocatore){
                banco_carte.forEach(c => giocatore_mazzo.push(c))
            } else {
                banco_carte.forEach(c => bot_mazzo.push(c))
            }

            banco_carte = []
            renderTable()

            setTimeout(endGame, 1500)
            return
        } else {
            distribuisci()
            renderTable()
        }
    }

    if (player_turn) {
        console.log("Turno: GIOCATORE")
        giocatoreGioca()
    } else {
        console.log("Turno: BOT")
        setTimeout(botGioca, 800)
    }
}

function endGame(){
    if(giocatore_mazzo.length > 20)
        punti_giocatore.carte++
    else if(bot_mazzo.length > 20)
        punti_bot.carte++

    switch (calcolaPrimiera()) {
        case "g":
            punti_giocatore.primiera++
            break;

        case "b":
            punti_bot.primiera++
            break

        default:
            break
    }

    if(giocatore_mazzo.some(c => c.seme === "denari" && c.valore === 7))
        punti_giocatore["sette bello"]++

    if(bot_mazzo.some(c => c.seme === "denari" && c.valore === 7))
        punti_bot["sette bello"]++

    if(giocatore_mazzo.filter(c => c.seme === "denari").length > 
                    bot_mazzo.filter(c => c.seme === "denari").length)
        punti_giocatore.ori++

    if(giocatore_mazzo.filter(c => c.seme === "denari").length < 
                bot_mazzo.filter(c => c.seme === "denari").length)
        punti_bot.ori++

    let vincitore = document.getElementById("vincitore")

    let puntiG = 0
    let puntiB = 0

    Object.values(punti_giocatore).forEach(p => puntiG += p)
    Object.values(punti_bot).forEach(p => puntiB += p)

    if(puntiG > puntiB)
        vincitore.textContent = "Hai vinto"
    else if(puntiB > puntiG)
        vincitore.textContent = "Hai perso"
    else 
        vincitore.textContent = "Pareggio"

    let p = document.createElement("p")
    p.innerHTML += "Punti totali: " + puntiG + "<br>"
    p.innerHTML += "Primiera: " + punti_giocatore.primiera + "<br>"
    p.innerHTML += "Sette bello: " + punti_giocatore["sette bello"] + "<br>"
    p.innerHTML += "Carte: " + punti_giocatore.carte + "<br>"
    p.innerHTML += "Denari: " + punti_giocatore.ori + "<br>"
    p.innerHTML += "Scope: " + punti_giocatore.scope

    let div_punti_g = document.getElementById("points_g")
    div_punti_g.appendChild(p)

    p = document.createElement("p")
    p.innerHTML += "Punti totali: " + puntiB + "<br>"
    p.innerHTML += "Primiera: " + punti_bot.primiera + "<br>"
    p.innerHTML += "Sette bello: " + punti_bot["sette bello"] + "<br>"
    p.innerHTML += "Carte: " + punti_bot.carte + "<br>"
    p.innerHTML += "Denari: " + punti_bot.ori + "<br>"
    p.innerHTML += "Scope: " + punti_bot.scope

    let div_punti_b = document.getElementById("points_b")
    div_punti_b.appendChild(p)

    let div_punti = document.getElementById("end_game")
    div_punti.style.visibility = "visible"
}

function calcolaPrimiera(){
    let bestGioc = {bastoni: 0, coppe: 0, denari: 0, spade: 0}
    let bestBot = {bastoni: 0, coppe: 0, denari: 0, spade: 0}

    const PUNTI_PRIMIERA = {
        7: 21,
        6: 18,
        1: 16,
        5: 15,
        4: 14,
        3: 13,
        2: 12,
        8: 10,
        9: 10,
        10: 10
    }

    giocatore_mazzo.forEach(c => {
        const seme = c.seme

        if(bestGioc[seme] < PUNTI_PRIMIERA[c.valore])
            bestGioc[seme] = PUNTI_PRIMIERA[c.valore]
    })

    bot_mazzo.forEach(c => {
        const seme = c.seme

        if(bestBot[seme] < PUNTI_PRIMIERA[c.valore])
            bestBot[seme] = PUNTI_PRIMIERA[c.valore]
    })

    let primieraGioc = 0
    let primieraBot = 0

    Object.values(bestGioc).forEach(p => primieraGioc += p)
    Object.values(bestBot).forEach(p => primieraBot += p)

    if(primieraGioc > primieraBot)
        return "g"
    else if(primieraBot > primieraGioc)
        return "b"

    return null
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
    
    scelta.forEach(c => {
        giocatore_mazzo.push(c)
    })

    giocatore_carte = giocatore_carte.filter(c => c !== carta)

    banco_carte = banco_carte.filter(c => !scelta.includes(c))

    if(banco_carte.length === 0 && (bot_carte.length > 0 || giocatore_carte.length > 0 || !mazzo.isEmpty))
        scopa(true)

    ultima_presa_giocatore = true
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

function botGioca(){
    const mossa = calcolaMossaBot()

    if(mossa.scelta === "none"){
        botCartaAlBanco(mossa.carta)
    } else {
        botEffettuaPresa(mossa.carta, mossa.scelta)
    }
}

function botCartaAlBanco(carta){
    // rimuove la carta dalla mano del giocatore
    bot_carte = bot_carte.filter(c => c !== carta)
    
    // aggiunge la carta al banco
    banco_carte.push(carta)

    player_turn = true

    renderTable()
    gestisciTurno()
}

function botEffettuaPresa(carta, presa){
    bot_mazzo.push(carta)
    
    presa.forEach(c => {
        bot_mazzo.push(c)
    })

    bot_carte = bot_carte.filter(c => c !== carta)

    banco_carte = banco_carte.filter(c => !presa.includes(c))

    if(banco_carte.length === 0 && (bot_carte.length > 0 || giocatore_carte.length > 0 || !mazzo.isEmpty))
        scopa(false)

    ultima_presa_giocatore = false
    player_turn = true

    renderTable()
    gestisciTurno()
}

function scopa(giocatore){
    let div
    if(giocatore){
        punti_giocatore.scope++
        div = document.getElementById("scopa_g")
    } else {
        punti_bot.scope++
        div = document.getElementById("scopa_b")
    }

    div.classList.remove("dissolvenza")
    void div.offsetWidth
    div.classList.add("dissolvenza")
}

function calcolaMossaBot(){
    let migliore_mossa = null
    let migliore_score = -Infinity

    for(let i=0; i<bot_carte.length; i++){
        const carta = bot_carte[i]
        const opzioni = calcolaPrese(carta, banco_carte)

        if(opzioni.length === 0){
            const score = valutaButto(carta)

            if(score > migliore_score){
                migliore_mossa = {carta: carta, scelta: "none"}
                migliore_score = score
            }

        } else {
            opzioni.forEach(presa => {
                const score = valutaPresa(carta, presa)

                if(score > migliore_score){
                    migliore_mossa = {carta: carta, scelta: presa}
                    migliore_score = score
                }
            })
        }
    }

    return migliore_mossa
}

function valutaButto(carta){
    let score = 0

    if(carta.seme === "denari") 
        score -= 15

    switch (carta.valore) {
        case 7:
            score -= 15
            break;

        case 6:
            score -= 10
            break

        case 1:
            score -= 9
            break
    
        default:
            break;
    }

    score -= carta.valore

    return score
}

function valutaPresa(carta, presa){
    let score = 0

    const banco_rimasto = banco_carte.filter(c => 
        !presa.some(p => p.seme === c.seme && p.valore === c.valore)
    )

    //scopa
    if(banco_rimasto.length === 0)
        score += 100

    //piu carte prende meglio è
    score += presa.length * 5

    //settebello
    if(carta.seme === "denari" && carta.valore === 7)
        score += 30
    if(presa.some(c => c.seme === "denari" && c.valore === 7))
        score += 30

    const lascia_7_denari = banco_rimasto.some(c => c.seme === "denari" && c.valore === 7)
    if(lascia_7_denari) 
        score -= 25  // stai regalando potenzialmente il settebello

    //denari
    if(carta.seme === "denari")
        score += 8
    score += presa.filter(c => c.seme === "denari").length * 8;

    //primiera
    if(carta.valore === 7)
        score += 10
    if(presa.some(c => c.valore === 7))
        score += 10
    if(carta.valore === 6)
        score += 8
    if(presa.some(c => c.valore === 6))
        score += 8
    if(carta.valore === 1)
        score += 6
    if(presa.some(c => c.valore === 1))
        score += 6

    score -= carta.valore

    return score
}

function riavviaGioco(){
    location.reload()
}