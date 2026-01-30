package ES2;

import java.util.ArrayList;

public class Biblioteca {
    private ArrayList<Libro> Libri = new ArrayList<>();
    private String Nome;

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public Biblioteca(String Nome){
        this.Nome = Nome;
    }

    public void aggiungiLibro(String ISBN, String Titolo, String Autore, int quantita){
        if (quantita <= 0) {
            System.out.println("Quantità non valida");
            return;
        }

        int index = findISBN(ISBN);
        if(index == -1){
            Libri.add(new Libro(ISBN, Titolo, Autore, quantita));
        } else {
            Libro esistente = Libri.get(index);
            esistente.setQuantitaTotale(esistente.getQuantitaTotale() + quantita);
            System.out.println("Aggiunte " + quantita + " copie a un libro esistente");
        }
    }

    public void prestito(String ISBN){
        if(Libri.size() != 0){
            int indLibro = findISBN(ISBN);
            if(indLibro != -1){
                Libro libro = Libri.get(indLibro);
                if(libro.getQuantitaDisponibile() > 0){
                    libro.setQuantitaInPrestito(libro.getQuantitaInPrestito() + 1);
                    System.out.println("Libro mandato in prestito");
                } else {
                    System.out.println("Non ci sono copie disponibili per il prestito");
                }
            } else {
                System.out.println("Nessun libro trovato");
            }
        } else {
            System.out.println("Non ci sono libri in libreria");
        }
    }

    public int findISBN(String ISBN){
        for(int i = 0; i < Libri.size(); i++){
            if(Libri.get(i).getISBN().equals(ISBN))
                return i;
        }
        return -1;
    }

    public void restituzione(String ISBN){
        if(Libri.size() != 0){
            int indLibro = findISBN(ISBN);
            if(indLibro != -1){
                Libro libro = Libri.get(indLibro);
                if(libro.getQuantitaInPrestito() > 0){
                    libro.setQuantitaInPrestito(libro.getQuantitaInPrestito() - 1);
                    System.out.println("Libro restituito");
                } else {
                    System.out.println("Nessuna copia di questo libro risulta in prestito");
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
            if(titLib.contains(Titolo) && libro.getQuantitaDisponibile() > 0){
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
                System.out.println("ISBN:            " + libro.getISBN());
                System.out.println("Titolo:          " + libro.getTitolo());
                System.out.println("Autore:          " + libro.getAutore());
                System.out.println("Quantità totale: " + libro.getQuantitaTotale());
                System.out.println("In prestito:     " + libro.getQuantitaInPrestito());
                System.out.println("Disponibili:     " + libro.getQuantitaDisponibile());
                System.out.println("----------------------------------------------------");
            }
        }
    }
}
