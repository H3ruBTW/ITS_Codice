const chooseDefault = document.getElementById("choose-default");
const chooseAggiungi = document.getElementById("choose-aggiungi");
const chooseModifica = document.getElementById("choose-modifica");
const chooseCancella = document.getElementById("choose-cancella");
const addBtn = document.getElementById("add");

function resetChoose() {
    chooseDefault.classList.add("hidden");
    chooseAggiungi.classList.add("hidden");
    chooseModifica.classList.add("hidden");
    chooseCancella.classList.add("hidden");
}

function openChooseDefault() {
    resetChoose();
    chooseDefault.classList.remove("hidden");
}

document.querySelectorAll(".btn-modifica").forEach(btn => {
    btn.addEventListener("click", function (event) {
        const card = event.currentTarget.closest(".opera-card");
        if (!card) return;

        const nomeEl = card.querySelector(".opera-nome");
        const autoreEl = card.querySelector(".autore");
        const annoEl = card.querySelector(".anno");
        const statoEl = card.querySelector(".stato-opera");

        if (!nomeEl || !autoreEl || !annoEl || !statoEl) return;

        resetChoose();
        chooseModifica.classList.remove("hidden");

        document.getElementById("modifica-nome").value = nomeEl.textContent.trim();
        document.getElementById("modifica-autore").value = autoreEl.textContent.trim();
        document.getElementById("modifica-anno").value = annoEl.textContent.trim();

        document.getElementById("modifica-disattivato").checked =
            statoEl.textContent.trim().toLowerCase() === "disattivata";
    });
});

addBtn.addEventListener("click", function () {
    resetChoose();
    chooseAggiungi.classList.remove("hidden");

    document.getElementById("aggiungi-nome").value = "";
    document.getElementById("aggiungi-autore").value = "";
    document.getElementById("aggiungi-anno").value = "";
    document.getElementById("aggiungi-img").value = "";
    document.getElementById("aggiungi-disattivato").checked = false;
});

document.querySelectorAll(".btn-cancella").forEach(btn => {
    btn.addEventListener("click", function (event) {
        const card = event.currentTarget.closest(".opera-card");
        if (!card) return;

        const nomeEl = card.querySelector(".opera-nome");
        if (!nomeEl) return;

        resetChoose();
        chooseCancella.classList.remove("hidden");

        document.getElementById("delete-message").textContent =
            `Vuoi davvero cancellare "${nomeEl.textContent.trim()}"?`;
    });
});

document.querySelectorAll(".btn-annulla-choose").forEach(btn => {
    btn.addEventListener("click", function () {
        openChooseDefault();
    });
});



openChooseDefault();