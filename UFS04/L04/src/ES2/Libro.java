package ES2;

public class Libro {
    private String ISBN, Titolo, Autore;
    private int quantitaTotale;
    private int quantitaInPrestito;

    public Libro(String ISBN, String Titolo, String Autore, int quantitaTotale){
        this.ISBN = ISBN;
        this.Titolo = Titolo;
        this.Autore = Autore;
        this.quantitaTotale = quantitaTotale;
        this.quantitaInPrestito = 0;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public String getAutore() {
        return Autore;
    }

    public void setAutore(String autore) {
        Autore = autore;
    }

    public int getQuantitaTotale() {
        return quantitaTotale;
    }

    public void setQuantitaTotale(int quantitaTotale) {
        this.quantitaTotale = quantitaTotale;
    }

    public int getQuantitaInPrestito() {
        return quantitaInPrestito;
    }

    public void setQuantitaInPrestito(int quantitaInPrestito) {
        this.quantitaInPrestito = quantitaInPrestito;
    }

    public int getQuantitaDisponibile() {
        return quantitaTotale - quantitaInPrestito;
    }
}
