package ES2;

public class Libro {
    private String ISBN, Titolo, Autore;
    private Boolean inPrestito;

    public Boolean getInPrestito() {
        return inPrestito;
    }

    public void setInPrestito(Boolean inPrestito) {
        this.inPrestito = inPrestito;
    }

    public String getAutore() {
        return Autore;
    }

    public void setAutore(String autore) {
        Autore = autore;
    }

    public String getTitolo() {
        return Titolo;
    }

    public void setTitolo(String titolo) {
        Titolo = titolo;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }

    public Libro(String ISBN, String Titolo, String Autore){
        this.ISBN = ISBN;
        this.Titolo = Titolo;
        this.Autore = Autore;
        inPrestito = false;
    }
}
