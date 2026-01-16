package Algoritmi;

public class Algoritmi {
    public static void main(String[] args) {
        Grafo grafo = new Grafo();

        grafo.aggiungiArco("A", "B");
        grafo.aggiungiArco("A", "F");
        grafo.aggiungiArco("A", "E");
        grafo.aggiungiArco("B", "C");
        grafo.aggiungiArco("B", "D");
        grafo.aggiungiArco("C", "G");
        grafo.aggiungiArco("D", "G");
        grafo.aggiungiArco("D", "F");
        grafo.aggiungiArco("E", "F");
        grafo.aggiungiArco("H", "I");
        grafo.aggiungiArco("H", "J");
        grafo.aggiungiArco("I", "J");
        grafo.aggiungiArco("I", "K");
        grafo.aggiungiArco("K", "L");

        grafo.stampa();

        grafo.bfsCamminoMin("A", "F");
        grafo.bfsCamminoMin("C", "F");

        System.out.println("Il grafo è connesso? (BFS) : " + grafo.connessoBFS());
        System.out.println("Il grafo è connesso? (DFS) : " + grafo.connessoDFS());
        System.out.println("Il grafo contiene dei cicli? : " + grafo.contieneCiclo());
    }
}
