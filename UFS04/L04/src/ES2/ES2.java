package ES2;

import java.util.Scanner;

public class ES2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Biblioteca biblio = new Biblioteca("Biblioteca Centrale");

        // Libri di esempio
        biblio.aggiungiLibro("9788804668239", "It", "Stephen King", 5);
        biblio.aggiungiLibro("9788804736068", "Il nome della rosa", "Umberto Eco", 3);
        biblio.aggiungiLibro("9788804668230", "1984", "George Orwell", 2);
        biblio.aggiungiLibro("9788804668231", "Il Signore degli Anelli", "J.R.R. Tolkien", 4);

        int scelta;
        do {
            System.out.println("\n===== MENU BIBLIOTECA " + biblio.getNome() + " =====");
            System.out.println("1) Aggiungi libro (con quantità)");
            System.out.println("2) Metti in prestito un libro");
            System.out.println("3) Restituisci un libro");
            System.out.println("4) Cerca libro per titolo (vuoto = tutti)");
            System.out.println("0) Esci");
            System.out.print("Scelta: ");

            scelta = getIntInput(in);

            switch (scelta) {
                case 1:
                    System.out.print("ISBN: ");
                    String isbnAdd = in.nextLine();
                    System.out.print("Titolo: ");
                    String titoloAdd = in.nextLine();
                    System.out.print("Autore: ");
                    String autoreAdd = in.nextLine();
                    System.out.print("Quantità: ");
                    int quantitaAdd = getIntInput(in);
                    biblio.aggiungiLibro(isbnAdd, titoloAdd, autoreAdd, quantitaAdd);
                    break;

                case 2:
                    System.out.print("ISBN da prestare: ");
                    biblio.prestito(in.nextLine());
                    break;

                case 3:
                    System.out.print("ISBN da restituire: ");
                    biblio.restituzione(in.nextLine());
                    break;

                case 4:
                    System.out.print("Titolo (Enter per tutti): ");
                    String ricerca = in.nextLine();
                    biblio.ricerca(ricerca);
                    break;

                case 0:
                    System.out.println("Arrivederci!");
                    break;

                default:
                    System.out.println("Scelta non valida.");
            }
        } while (scelta != 0);

        in.close();
    }

    private static int getIntInput(Scanner in) {
        while (!in.hasNextInt()) {
            System.out.print("Numero valido: ");
            in.next();
        }
        int valore = in.nextInt();
        in.nextLine();
        return valore;
    }
}
