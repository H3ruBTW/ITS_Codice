package ES02;

import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteca {
    ArrayList<Libro> Libri = new ArrayList<>();
    ArrayList<Utente> Utenti = new ArrayList<>();
    ArrayList<Prestito> Prestiti = new ArrayList<>();

    public void aggiungiLibro(String ISBN, String nome, String autore){
        Libri.add(new Libro(ISBN, nome, autore));
    }

    public void aggiungiUtente(String nome, String cognome, LocalDate dataNascita){
        int ID = (Utenti.size() > 0) ? Utenti.getLast().getID() + 1 : 1;
        Utenti.add(new Utente(ID, nome, cognome, dataNascita));
    }

    public void creaPrestito(String ISBN, int ID){
        Libro l = null;
        Utente u = null;

        for (Libro libro : Libri) {
            if(libro.getISBN() == ISBN)
                l = libro;
                break;
            
        }

        if(l == null){
            System.out.println("Libro non trovato");
            return;
        }

        for (Utente utente : Utenti) {
            if(utente.getID() == ID)
                u = utente;
                break;
            
        }

        if(u == null){
            System.out.println("Utente non iscritto");
            return;
        }

        Prestiti.add(new Prestito(l, u));
    }

    public void restituzione(String ISBN, int ID){
        for (Prestito p : Prestiti) {
            if(p.getLibro().getISBN() == ISBN && p.getUtente().getID() == ID)
                if(p.getFine().isBefore(LocalDate.now()))
                    System.out.println("Consegnato in ritardo");
                else
                    System.out.println("Consegnato nel giusto tempo");
        }
    }
}
