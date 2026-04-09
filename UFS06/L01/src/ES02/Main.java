package ES02;

public class Main {
	public static void main(String[] args) {
		Biblioteca biblioteca = new Biblioteca();

		// Inserimento libri
		biblioteca.aggiungiLibro("9788804707471", "I Promessi Sposi", "Alessandro Manzoni");
		biblioteca.aggiungiLibro("9788804626079", "Il Nome della Rosa", "Umberto Eco");
		biblioteca.aggiungiLibro("9788806254577", "1984", "George Orwell");
		biblioteca.aggiungiLibro("9788804712031", "Il Gattopardo", "Giuseppe Tomasi di Lampedusa");

		// Inserimento utenti (l'ID viene assegnato automaticamente partendo da 1)
		biblioteca.aggiungiUtente("Mario", "Rossi", 15, 3, 2000);
		biblioteca.aggiungiUtente("Luca", "Bianchi", 2, 9, 1998);

		// Creazione prestiti
		biblioteca.creaPrestito("9788804707471", 1);
		biblioteca.creaPrestito("9788804626079", 1);
		biblioteca.creaPrestito("9788806254577", 1);

		// Questo dovrebbe dare errore: massimo 3 prestiti attivi per utente
		biblioteca.creaPrestito("9788804712031", 1);

		// Prestito per un altro utente
		biblioteca.creaPrestito("9788804712031", 2);

		System.out.println("\n--- Prestiti attivi ---");
		biblioteca.printPrestitiAttivi();

		System.out.println("\n--- Storico libri utente ID 1 ---");
		biblioteca.printLibriPersona(1);

		// Restituzione di un libro
		System.out.println("\n--- Restituzione libro ---");
		biblioteca.restituzione("9788804707471", 1);

		System.out.println("\n--- Prestiti attivi dopo restituzione ---");
		biblioteca.printPrestitiAttivi();
	}
}
