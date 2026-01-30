package ES2;

import java.util.ArrayList;

//AGGIUNTA LIBRI, PRESTITO LIBRI, RESTITUZIONE DAL PRESTITO, CERCA LIBRO
public class Biblioteca {
    private ArrayList<Libro> Libri = new ArrayList<>();
    private String Nome;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Biblioteca(String Nome){this.Nome = Nome;}

    public void aggiungiLibro(String ISBN, String Titolo, String Autore){
        if(findISBN(ISBN) == -1){
            Libri.add(new Libro(ISBN, Titolo, Autore));
        } else {
            System.out.println("Niente duplicati");
        }
    }

    public void prestito(String ISBN){
        if(Libri.size()!=0 && !checkAllPrestito()){
            int indLibro;
            if((indLibro = findISBN(ISBN)) != -1){
                if(!Libri.get(indLibro).getInPrestito()){
                    Libri.get(indLibro).setInPrestito(true);
                    System.out.println("Libro mandato in prestito");
                } else {
                    System.out.println("Il libro è già in prestito");
                }
            } else {
                System.out.println("Nessun libro trovato");
            }
        } else {
            System.out.println("Tutti i libri sono stati presi");
        }
    }

    public int findISBN(String ISBN){
        for(int i=0; i<Libri.size(); i++){
            if(Libri.get(i).getISBN().equals(ISBN))
                return i;
        }

        return -1;
    }

    public Boolean checkAllPrestito(){
        for (Libro libro : Libri) {
            if(libro.getInPrestito() == false)
                return false;
        }

        return true;
    }

    public void restituzione(String ISBN){
        if(Libri.size() != 0){
            int indLibro;
            if((indLibro = findISBN(ISBN)) != -1){
                if(Libri.get(indLibro).getInPrestito()){
                    Libri.get(indLibro).setInPrestito(false);
                    System.out.println("Libro restituito");
                } else {
                    System.out.println("Il libro non era in prestito in questa biblioteca");
                }
            } else {
                System.out.println("Hai sbagliato biblioteca");
            }
        } else {
            System.out.println("Non ci sono libri in libreria");
        }
    }

    public void ricerca(String Titolo){
        ArrayList<Libro> FiltroLibri = new ArrayList<>();

        Titolo = Titolo.toLowerCase().replaceAll(" ", "");

        for (Libro libro : Libri) {
            String titLib = libro.getTitolo().toLowerCase().replaceAll(" ", "");
            
            if(titLib.startsWith(Titolo) && !libro.getInPrestito()){
                FiltroLibri.add(libro);
            }
        }
        System.out.println("----------------------------------------------------");
        System.out.println("Libri trovati disponibili");
        System.out.println("----------------------------------------------------");

         if (FiltroLibri.isEmpty()) {
            System.out.println("Nessun libro trovato.");
        } else {
            for (Libro libro : FiltroLibri) {
                System.out.println("ISBN:   " + libro.getISBN());
                System.out.println("Titolo: " + libro.getTitolo());
                System.out.println("Autore: " + libro.getAutore());
                System.out.println("----------------------------------------------------");
            }
        }
    }
}
