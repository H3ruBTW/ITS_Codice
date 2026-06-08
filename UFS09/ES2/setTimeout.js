const somma = (valore1, valore2, callback) => {
    setTimeout(() => {
        const ris = valore1 + valore2
        callback(ris)
    }, 1000);
};

somma(5, 3, (ris) => {
    console.log("Totale:", ris)
});


