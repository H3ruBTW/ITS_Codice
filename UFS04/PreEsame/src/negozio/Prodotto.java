package negozio;

public abstract class Prodotto {
    protected String codice, marca, modello;
    protected short annoUscita;
    protected double prezzo;

    public String getCodice() {
        return codice;
    }

    public String getMarca() {
        return marca;
    }

    public String getModello() {
        return modello;
    }

    public short getAnnoUscita() {
        return annoUscita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setCodice(String codice) throws IllegalArgumentException {
        if(checkCodice(codice))
            this.codice = codice;
        else
            throw new IllegalArgumentException("Pattern errato");
    }

    public void setMarca(String marca) throws IllegalArgumentException {
        if(!marca.isBlank())
            this.marca = marca;
        else
            throw new IllegalArgumentException("Blank");
    }

    public void setModello(String modello) throws IllegalArgumentException {
        if(!modello.isBlank())
            this.modello = modello;
        else
            throw new IllegalArgumentException("Blank");
    }

    public void setAnnoUscita(short annoUscita) throws IllegalArgumentException {
        if(annoUscita > 0)
            this.annoUscita = annoUscita;
        else
            throw new IllegalArgumentException("Non permesso <0");
    }

    public void setPrezzo(double prezzo) {
        if(prezzo > 0)
            this.prezzo = prezzo;
        else
            throw new IllegalArgumentException("Non permesso <=0");
    }

    public Prodotto(String codice, String marca, String modello, short annoUscita, double prezzo) throws IllegalArgumentException {
        setCodice(codice);
        setMarca(marca);
        setModello(modello);
        setAnnoUscita(annoUscita);
        setPrezzo(prezzo);
    }

    public abstract double calcolaGaranzia();

    public static boolean checkCodice(String codice){
        return codice.matches("^[A-Z]{2}[0-9]{5}$");
    }

    @Override
    public String toString() {
        return "\nProdotto\nCodice " + codice + 
        "\nMarca " + marca + 
        "\nModello " + modello + 
        "\nAnno Uscita " + annoUscita +
        "\nPrezzo " + prezzo;
    }
}
