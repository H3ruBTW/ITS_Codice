const chooseDefault = document.getElementById("choose-default");
const chooseAggiungi = document.getElementById("choose-aggiungi");
const chooseModifica = document.getElementById("choose-modifica");
const chooseCancella = document.getElementById("choose-cancella");
const addBtn = document.getElementById("add");

const aggiungiAutoriWrapper = document.getElementById("aggiungi-autori-wrapper");
const modificaAutoriWrapper = document.getElementById("modifica-autori-wrapper");
const aggiungiAutoreBtn = document.getElementById("aggiungi-autore-btn");
const modificaAutoreBtn = document.getElementById("modifica-autore-btn");

function resetChoose() {
    if (chooseDefault) chooseDefault.classList.add("hidden");
    if (chooseAggiungi) chooseAggiungi.classList.add("hidden");
    if (chooseModifica) chooseModifica.classList.add("hidden");
    if (chooseCancella) chooseCancella.classList.add("hidden");
}

function openChooseDefault() {
    resetChoose();
    if (chooseDefault) chooseDefault.classList.remove("hidden");
}

function creaAutoreRow(nome = "", cognome = "", dataNascita = "", dataMorte = "") {
    const row = document.createElement("div");
    row.className = "autore-row";

    row.innerHTML = `
        <input type="text" class="autore-nome" placeholder="Nome" value="${nome}">
        <input type="text" class="autore-cognome" placeholder="Cognome" value="${cognome}">
        <input type="date" class="autore-data-nascita" value="${dataNascita}">
        <input type="date" class="autore-data-morte" value="${dataMorte}">
        <button type="button" class="btn-rimuovi-autore">Rimuovi</button>
        <p class="autore-error hidden">La data di morte non può essere precedente alla data di nascita.</p>
    `;

    const removeBtn = row.querySelector(".btn-rimuovi-autore");
    const nascitaInput = row.querySelector(".autore-data-nascita");
    const morteInput = row.querySelector(".autore-data-morte");
    const errorEl = row.querySelector(".autore-error");

    function validaDateAutore() {
        const nascita = nascitaInput.value;
        const morte = morteInput.value;

        if (morte !== "" && nascita !== "" && morte < nascita) {
            morteInput.setCustomValidity("Data di morte non valida");
            errorEl.classList.remove("hidden");
        } else {
            morteInput.setCustomValidity("");
            errorEl.classList.add("hidden");
        }
    }

    nascitaInput.addEventListener("change", validaDateAutore);
    morteInput.addEventListener("change", validaDateAutore);

    removeBtn.addEventListener("click", function () {
        const wrapper = row.parentElement;
        if (wrapper && wrapper.querySelectorAll(".autore-row").length > 1) {
            row.remove();
        }
    });

    validaDateAutore();
    return row;
}

function popolaAutoriDaData(wrapper, authorsData) {
    if (!wrapper) return;

    wrapper.innerHTML = "";

    let autori = [];

    try {
        autori = JSON.parse(authorsData || "[]");
    } catch (error) {
        autori = [];
    }

    if (!Array.isArray(autori) || autori.length === 0) {
        wrapper.appendChild(creaAutoreRow());
        return;
    }

    autori.forEach(autore => {
        wrapper.appendChild(
            creaAutoreRow(
                autore.nome || "",
                autore.cognome || "",
                autore.dataNascita || "",
                autore.dataMorte || ""
            )
        );
    });
}

function resetAutori(wrapper) {
    if (!wrapper) return;
    wrapper.innerHTML = "";
    wrapper.appendChild(creaAutoreRow());
}

function validaFormAutori(wrapper) {
    if (!wrapper) return true;

    const rows = wrapper.querySelectorAll(".autore-row");

    for (const row of rows) {
        const nome = row.querySelector(".autore-nome");
        const cognome = row.querySelector(".autore-cognome");
        const dataNascita = row.querySelector(".autore-data-nascita");
        const dataMorte = row.querySelector(".autore-data-morte");

        const nomeVal = nome.value.trim();
        const cognomeVal = cognome.value.trim();
        const nascitaVal = dataNascita.value.trim();
        const morteVal = dataMorte.value.trim();

        const autoreVuoto = !nomeVal && !cognomeVal && !nascitaVal && !morteVal;

        if (autoreVuoto) {
            continue;
        }

        if (!nomeVal) {
            nome.reportValidity();
            return false;
        }

        if (!cognomeVal) {
            cognome.reportValidity();
            return false;
        }

        if (!nascitaVal) {
            dataNascita.reportValidity();
            return false;
        }

        if (!dataMorte.checkValidity()) {
            dataMorte.reportValidity();
            return false;
        }
    }

    return true;
}

if (aggiungiAutoreBtn) {
    aggiungiAutoreBtn.addEventListener("click", function () {
        if (aggiungiAutoriWrapper) {
            aggiungiAutoriWrapper.appendChild(creaAutoreRow());
        }
    });
}

if (modificaAutoreBtn) {
    modificaAutoreBtn.addEventListener("click", function () {
        if (modificaAutoriWrapper) {
            modificaAutoriWrapper.appendChild(creaAutoreRow());
        }
    });
}

document.querySelectorAll(".btn-modifica").forEach(btn => {
    btn.addEventListener("click", function (event) {
        const card = event.currentTarget.closest(".opera-card");
        if (!card) return;

        const idEl = card.querySelector(".opera-id");
        const nomeEl = card.querySelector(".opera-nome");
        const autoreEl = card.querySelector(".autore");
        const annoEl = card.querySelector(".anno");
        const statoEl = card.querySelector(".stato-opera");

        if (!idEl || !nomeEl || !autoreEl || !annoEl || !statoEl) return;

        resetChoose();
        if (chooseModifica) chooseModifica.classList.remove("hidden");

        const modificaId = document.getElementById("modifica-id");
        const modificaNome = document.getElementById("modifica-nome");
        const modificaAnno = document.getElementById("modifica-anno");
        const modificaDescrizione = document.getElementById("modifica-descrizione");
        const modificaImg = document.getElementById("modifica-img");
        const modificaDisattivato = document.getElementById("modifica-disattivato");
        const modificaStanza = document.getElementById("modifica-stanza");
        const modificaRestauro = document.getElementById("modifica-restauro");

        if (modificaId) modificaId.value = idEl.textContent.trim();
        if (modificaNome) modificaNome.value = nomeEl.textContent.trim();
        if (modificaAnno) modificaAnno.value = annoEl.textContent.trim();
        if (modificaDescrizione) modificaDescrizione.value = card.dataset.description || "";
        if (modificaImg) modificaImg.value = "";
        if (modificaDisattivato) {
            modificaDisattivato.checked =
                statoEl.textContent.trim().toLowerCase() === "disattivata";
        }
        if (modificaStanza) modificaStanza.value = card.dataset.roomId || "";
        if (modificaRestauro) {
            modificaRestauro.checked = card.dataset.restauroAttivo === "true";
        }

        popolaAutoriDaData(modificaAutoriWrapper, card.dataset.authors);
    });
});

document.querySelectorAll(".btn-cancella").forEach(btn => {
    btn.addEventListener("click", function (event) {
        const card = event.currentTarget.closest(".opera-card");
        if (!card) return;

        const nomeEl = card.querySelector(".opera-nome");
        if (!nomeEl) return;

        resetChoose();
        if (chooseCancella) chooseCancella.classList.remove("hidden");

        const deleteMessage = document.getElementById("delete-message");
        if (deleteMessage) {
            deleteMessage.textContent =
                `Vuoi davvero cancellare "${nomeEl.textContent.trim()}"?`;
        }
    });
});

document.querySelectorAll(".btn-annulla-choose").forEach(btn => {
    btn.addEventListener("click", function () {
        openChooseDefault();
    });
});

if (addBtn) {
    addBtn.addEventListener("click", function () {
        resetChoose();
        if (chooseAggiungi) chooseAggiungi.classList.remove("hidden");

        const aggiungiNome = document.getElementById("aggiungi-nome");
        const aggiungiAnno = document.getElementById("aggiungi-anno");
        const aggiungiImg = document.getElementById("aggiungi-img");
        const aggiungiDescrizione = document.getElementById("aggiungi-descrizione");
        const aggiungiDisattivato = document.getElementById("aggiungi-disattivato");
        const aggiungiStanza = document.getElementById("aggiungi-stanza");

        if (aggiungiNome) aggiungiNome.value = "";
        if (aggiungiAnno) aggiungiAnno.value = "";
        if (aggiungiImg) aggiungiImg.value = "";
        if (aggiungiDescrizione) aggiungiDescrizione.value = "";
        if (aggiungiDisattivato) aggiungiDisattivato.checked = false;
        if (aggiungiStanza) aggiungiStanza.value = "";

        resetAutori(aggiungiAutoriWrapper);
    });
}

const formAggiungi = document.getElementById("form-aggiungi-opera");
if (formAggiungi) {
    formAggiungi.addEventListener("submit", function (event) {
        if (!validaFormAutori(aggiungiAutoriWrapper)) {
            event.preventDefault();
        }
    });
}

const formModifica = document.getElementById("form-modifica-opera");
if (formModifica) {
    formModifica.addEventListener("submit", function (event) {
        if (!validaFormAutori(modificaAutoriWrapper)) {
            event.preventDefault();
        }
    });
}

resetAutori(aggiungiAutoriWrapper);
resetAutori(modificaAutoriWrapper);
openChooseDefault();