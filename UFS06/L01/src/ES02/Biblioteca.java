package ES02;

import java.time.LocalDate;
import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> Libri = new ArrayList<>();
    private ArrayList<Utente> Utenti = new ArrayList<>();
    private ArrayList<Prestito> Prestiti = new ArrayList<>();

    public void aggiungiLibro(String ISBN, String nome, String autore){
        try{
            if(getLibroFromISBN(ISBN) == null)
                Libri.add(new Libro(ISBN, nome, autore));
            else
                throw new IllegalArgumentException("Libro già esistente");
        } catch (Exception e){
            System.err.println("Errore nel inserimento: " + e.getMessage());
        }
        
    }

    public void aggiungiUtente(String nome, String cognome, int GG, int MM, int YYYY){
        int ID = (Utenti.size() > 0) ? Utenti.getLast().getID() + 1 : 1;
        try {
            Utenti.add(new Utente(ID, nome, cognome, GG, MM, YYYY));
        } catch (Exception e) {
            System.err.println("Errore nel inserimento: " + e.getMessage());
        }
        
    }

    public Libro getLibroFromISBN(String ISBN){
        for (Libro libro : Libri) {
            if(libro.getISBN() == ISBN)
                return libro;
            
        }

        return null;
    }

    public Utente getUtenteFromID(int ID){
        for (Utente utente : Utenti) {
            if(utente.getID() == ID)
                return utente;
            
        }

        return null;
    }

    public void creaPrestito(String ISBN, int ID){
        
        try {

            Libro l = getLibroFromISBN(ISBN);
            Utente u = getUtenteFromID(ID);

            if(l == null){
                throw new Exception("Libro non esistente");
            }


            if(u == null){
                throw new Exception("Utente non esistente");
            }

            int count = 0;

            for (Prestito p : Prestiti) {
                if(p.getUtente().equals(u) && p.getInPrestito())
                    count++;

                if(p.getLibro().equals(l) && p.getInPrestito())
                    return;
            }

            if(count >= 3)
                throw new Exception("L'utente ha troppi prestiti {"+ count + "}");
            else
                Prestiti.add(new Prestito(l, u));
        } catch (Exception e) {
            System.err.println("Errore: " + e.getMessage());
        }

        
    }

    public void restituzione(String ISBN, int ID){
        try {
            Libro l = getLibroFromISBN(ISBN);
            Utente u = getUtenteFromID(ID);

            if(l == null){
                throw new Exception("Libro non esistente");
            }

            if(u == null){
                throw new Exception("Utente non esistente");
            }

            Boolean found = false;
            for (Prestito p : Prestiti) {
                if(p.getLibro().equals(l) && p.getUtente().equals(u) && p.getInPrestito()){
                    found = true;
                    p.finisciPrestito();

                    if(p.getFine().isBefore(LocalDate.now()))
                        System.out.println("Consegnato correttamente ma in ritardo");
                    else
                        System.out.println("Consegnato correttamente prima della deadline");
                }
            }

            if(!found){
                System.out.println("Nessun riscontro trovato");
            }
        } catch (Exception e){
            System.err.println("Errore: " + e.getMessage());
        }
            
    }

    public void printPrestitiAttivi(){
        Boolean found = false;

        System.out.println("Prestiti attivi trovati:");

        for (Prestito p : Prestiti) {
            if(p.getInPrestito()){
                found = true;
                System.out.println(p.toString());
            }
        }

        if(!found){
            System.out.println("Nessun riscontro trovato");
        }
    }

    public void printLibriPersona(int ID){

        try{
            Utente ut = getUtenteFromID(ID);

            Boolean found = false;

            if(ut == null)
                throw new Exception("Utente non esistente");

            System.out.println("Libri trovati per l'utente con ID: " + ID);

            for (Prestito p : Prestiti) {
                if(p.getUtente().equals(ut)){
                    found = true;
                    System.out.println(p.getLibro());   
                }

            }

            if(!found){
                System.out.println("Nessun riscontro trovato");
            }
        } catch (Exception e){
            System.err.println("Errore: " + e.getMessage());
        }
    }
}
