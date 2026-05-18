const opereFake = [
    {
        id: "OP001",
        nome: "Notte stellata",
        autori: [
            {
                nome: "Vincent",
                cognome: "van Gogh",
                dataNascita: "1853-03-30",
                dataMorte: "1890-07-29"
            }
        ],
        anno: "1889",
        img: "img/opera1.jpg",
        stato: "Attiva",
        descrizione: "Dipinto caratterizzato da un cielo vorticoso e da una forte tensione luminosa che rende il paesaggio intensamente dinamico.",
        stanza: {
            id: "ST01",
            nome: "Sala Impressionisti"
        }
    },
    {
        id: "OP002",
        nome: "Installazione urbana",
        autori: [
            {
                nome: "Mario",
                cognome: "Rossi",
                dataNascita: "1985-06-12",
                dataMorte: ""
            },
            {
                nome: "Luca",
                cognome: "Bianchi",
                dataNascita: "1979-11-03",
                dataMorte: ""
            }
        ],
        anno: "2021",
        img: "img/opera2.jpg",
        stato: "Disattivata",
        descrizione: "Opera contemporanea pensata per spazi pubblici, incentrata sul dialogo tra materiali industriali e movimento dei passanti.",
        stanza: {
            id: "ST03",
            nome: "Sala Arte Contemporanea"
        }
    },
    {
        id: "OP003",
        nome: "Reperto antico",
        autori: [
            {
                nome: "",
                cognome: "",
                dataNascita: "",
                dataMorte: ""
            }
        ],
        anno: "1600",
        img: "img/opera3.jpg",
        stato: "Attiva",
        descrizione: "Manufatto storico con attribuzione incerta, conservato come testimonianza di produzione artigianale d’epoca.",
        stanza: {
            id: "ST02",
            nome: "Sala Reperti Storici"
        }
    }
];

const opereWrapper = document.querySelector(".opere-wrapper");

function formattaAutori(autori) {
    return autori
        .map(autore => {
            const nome = (autore.nome || "").trim();
            const cognome = (autore.cognome || "").trim();
            const dataNascita = (autore.dataNascita || "").trim();
            const dataMorte = (autore.dataMorte || "").trim();

            if (!nome && !cognome && !dataNascita && !dataMorte) {
                return "Autore sconosciuto";
            }

            const nomeCompleto = `${nome} ${cognome}`.trim();
            const morteFinale = dataMorte || "attuale";

            return `${nomeCompleto} (${dataNascita} - ${morteFinale})`;
        })
        .join("; ");
}

function creaCard(opera) {
    return `
        <div class="opera-card">
            <img class="img-card" src="${opera.img}" alt="${opera.nome}">
            <div class="info-gen">
                <h3 class="opera-nome">${opera.nome}</h3>
                <p><strong>Autore:</strong> <span class="autore">${formattaAutori(opera.autori)}</span></p>
                <p><strong>Anno:</strong> <span class="anno">${opera.anno}</span></p>
                <p><strong>Dove si trova:</strong> <span class="stanza">${opera.stanza.nome}</span></p>
                <p class="descrizione"><strong>Descrizione:</strong> ${opera.descrizione}</p>
            </div>
        </div>
    `;
}

function renderOpere(opere) {
    if (!opereWrapper) return;
    opereWrapper.innerHTML = opere.map(creaCard).join("");
}

renderOpere(opereFake);