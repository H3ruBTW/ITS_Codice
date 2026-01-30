package ES2;

import java.util.Scanner;

public class ES2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Crea biblioteca e aggiungi qualche libro reale
        Biblioteca biblio = new Biblioteca("Biblioteca Centrale");
        biblio.aggiungiLibro("9788804668239", "It", "Stephen King");              // [web:14]
        biblio.aggiungiLibro("9788804736068", "Il nome della rosa", "Umberto Eco"); // [web:14]
        biblio.aggiungiLibro("9788804668230", "1984", "George Orwell");           // [web:14]
        biblio.aggiungiLibro("9788804668231", "Il Signore degli Anelli", "J.R.R. Tolkien"); // [web:14]

        int scelta;
        do {
            System.out.println("===== MENU BIBLIOTECA =====");
            System.out.println("1) Aggiungi libro");
            System.out.println("2) Metti in prestito un libro");
            System.out.println("3) Restituisci un libro");
            System.out.println("4) Cerca libro per titolo");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");

            while (!in.hasNextInt()) {
                System.out.print("Inserisci un numero valido: ");
                in.next();
            }
            scelta = in.nextInt();
            in.nextLine(); // consuma il newline

            switch (scelta) {
                case 1:
                    System.out.print("ISBN: ");
                    String isbnAdd = in.nextLine();
                    System.out.print("Titolo: ");
                    String titoloAdd = in.nextLine();
                    System.out.print("Autore: ");
                    String autoreAdd = in.nextLine();
                    biblio.aggiungiLibro(isbnAdd, titoloAdd, autoreAdd);
                    break;

                case 2:
                    System.out.print("ISBN del libro da mettere in prestito: ");
                    String isbnPrestito = in.nextLine();
                    biblio.prestito(isbnPrestito);
                    break;

                case 3:
                    System.out.print("ISBN del libro da restituire: ");
                    String isbnRest = in.nextLine();
                    biblio.restituzione(isbnRest);
                    break;

                case 4:
                    System.out.print("Titolo (o inizio titolo) da cercare: ");
                    String titoloRicerca = in.nextLine();
                    biblio.ricerca(titoloRicerca);
                    break;

                case 0:
                    System.out.println("Uscita dal programma...");
                    break;

                default:
                    System.out.println("Scelta non valida.");
                    break;
            }

            System.out.println();

        } while (scelta != 0);

        in.close();
    }
}
