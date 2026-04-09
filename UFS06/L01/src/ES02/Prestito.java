package ES02;

import java.time.LocalDate;

public class Prestito {
    private Libro libro;
    private Utente utente;
    private LocalDate inizio, fine;
    private Boolean inPrestito = true;

    public Prestito(Libro libro, Utente utente){
        this.libro = libro;
        this.utente = utente;
        inizio = LocalDate.now();
        fine = inizio.plusDays(30);
    }

    public Libro getLibro() {
        return libro;
    }

    public Utente getUtente() {
        return utente;
    }

    public LocalDate getInizio() {
        return inizio;
    }

    public LocalDate getFine() {
        return fine;
    }

    public Boolean getInPrestito() {
        return inPrestito;
    }

    public void finisciPrestito() {
        inPrestito = false;
    }

    @Override
    public String toString(){
        return "{\nLibro: " + libro.toString() + "\nUtente: " + utente.toString() + "\nData inizio: " + inizio + "\nData fine: " + fine + "\nPrestito attivo: " + inPrestito + "}";
    }
}
