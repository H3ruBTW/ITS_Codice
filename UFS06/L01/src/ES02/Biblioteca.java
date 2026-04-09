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

        int count = 0;

        for (Prestito p : Prestiti) {
            if(p.getUtente().equals(u) && p.getInPrestito())
                count++;

            if(p.getLibro().equals(l) && p.getInPrestito())
                return;
        }

        if(count >= 3)
            System.out.println("Troppi prestiti");
        else
            Prestiti.add(new Prestito(l, u));
    }

    public void restituzione(String ISBN, int ID){
        for (Prestito p : Prestiti) {
            if(p.getLibro().getISBN() == ISBN && p.getUtente().getID() == ID && p.getInPrestito()){
                p.finisciPrestito();

                if(p.getFine().isBefore(LocalDate.now()))
                    System.out.println("Consegnato in ritardo");
                else
                    System.out.println("Consegnato nel giusto tempo");
            }
        }
    }

    public void printPrestiti(){
        for (Prestito p : Prestiti) {
            if(p.getInPrestito()){
                System.out.println(p.toString());
            }
        }
    }

    public void printPrestitiPersona(int ID){
        Utente ut = null;

        for(Utente u : Utenti){
            if(u.getID() == ID){
                ut = u;
                break;
            }
        }

        if(ut == null)
            return;

        for (Prestito p : Prestiti) {
            if(p.getUtente().equals(ut)){
                System.out.println(p.toString());   
            }

        }
    }
}
