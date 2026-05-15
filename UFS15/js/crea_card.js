const opereFake = [
    {
        nome: "Notte stellata",
        autore: "Vincent van Gogh",
        anno: "1889",
        img: "img/opera1.jpg"
    },
    {
        nome: "La Gioconda",
        autore: "Leonardo da Vinci",
        anno: "1503",
        img: "img/opera2.jpg"
    },
    {
        nome: "L'urlo",
        autore: "Edvard Munch",
        anno: "1893",
        img: "img/opera3.jpg"
    },
    {
        nome: "Guernica",
        autore: "Pablo Picasso",
        anno: "1937",
        img: "img/opera4.jpg"
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