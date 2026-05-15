const opereFake = [
    {
        nome: "Notte stellata",
        autore: "Vincent van Gogh",
        anno: "1889",
        img: "img/opera1.jpg",
        stato: "Attiva"
    },
    {
        nome: "Installazione urbana",
        autore: "Mario Rossi; Luca Bianchi",
        anno: "2021",
        img: "img/opera2.jpg",
        stato: "Disattivata"
    },
    {
        nome: "Visioni contemporanee",
        autore: "Anna Verdi; Chiara Neri; Paolo Galli",
        anno: "2024",
        img: "img/opera3.jpg",
        stato: "Attiva"
    },
    {
        nome: "Guernica",
        autore: "Pablo Picasso",
        anno: "1937",
        img: "img/opera4.jpg",
        stato: "Disattivata"
    },
    {
        nome: "Opera collettiva",
        autore: "Elisa Fontana; Marco Riva",
        anno: "2019",
        img: "img/opera5.jpg",
        stato: "Attiva"
    }
];
const opereWrapper = document.querySelector(".opere-wrapper");

function creaCard(opera) {
    return `
        <div class="opera-card">
            <img class="img-card" src="${opera.img}" alt="${opera.nome}">

            <div class="info-gen">
                <h3 class="opera-nome">${opera.nome}</h3>
                <p><strong>Autore:</strong> <span class="autore">${opera.autore}</span></p>
                <p><strong>Anno:</strong> <span class="anno">${opera.anno}</span></p>
                <p><strong>Stato:</strong> <span class="stato-opera">${opera.stato}</span></p>
            </div>

            <div class="operations">
                <hr>
                <button type="button" class="btn-modifica">Modifica</button>
                <button type="button" class="btn-cancella">Cancella</button>
            </div>
        </div>
    `;
}

function renderOpere() {
    opereWrapper.innerHTML = opereFake.map(creaCard).join("");
}

renderOpere();