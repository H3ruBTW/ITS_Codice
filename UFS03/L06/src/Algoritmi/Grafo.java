package Algoritmi;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Grafo {
    private Map<String, List<String>> listaAdiacenza;
    

    /**
     * Costruttore: crea un grafo vuoto.
     */
    public Grafo() {
        listaAdiacenza = new HashMap<>();
    }

    /**
     * Aggiunge un nodo al grafo (se non esiste già).
     */
    public void aggiungiNodo(String nodo) {
        listaAdiacenza.putIfAbsent(nodo, new ArrayList<>());
    }

    /**
     * Aggiunge un arco non orientato tra nodo1 e nodo2.
     * Se i nodi non esistono, vengono creati.
     */
    public void aggiungiArco(String nodo1, String nodo2) {
        aggiungiNodo(nodo1);
        aggiungiNodo(nodo2);

        // Evita archi duplicati
        if (!listaAdiacenza.get(nodo1).contains(nodo2)) {
            listaAdiacenza.get(nodo1).add(nodo2);
        }
        if (!listaAdiacenza.get(nodo2).contains(nodo1)) {
            listaAdiacenza.get(nodo2).add(nodo1);
        }
    }

    /**
     * Restituisce l'insieme di tutti i nodi del grafo.
     */
    public Set<String> getNodi() {
        return listaAdiacenza.keySet();
    }

    /**
     * Restituisce la lista dei nodi adiacenti a un nodo.
     * Se il nodo non esiste, restituisce una lista vuota.
     */
    public List<String> getAdiacenti(String nodo) {
        if (listaAdiacenza.containsKey(nodo)) {
            return new ArrayList<>(listaAdiacenza.get(nodo));
        } else {
            return new ArrayList<>();
        }
    }

    /**
     * Restituisce true se il nodo esiste nel grafo.
     */
    public boolean contieneNodo(String nodo) {
        return listaAdiacenza.containsKey(nodo);
    }

    /**
     * Stampa il grafo (lista di adiacenza).
     */
    public void stampa() {
        System.out.println("Grafo:");
        for (String nodo : listaAdiacenza.keySet()) {
            System.out.println("  " + nodo + " -> " + listaAdiacenza.get(nodo));
        }
    }

    public void bfsCamminoMin(String part, String dest){
        // Strutture dati per BFS
        Queue<String> coda = new LinkedList<>();
        Set<String> visitati = new HashSet<>();
        Map<String, Integer> distanza = new HashMap<>();
        Map<String, String> predecessore = new HashMap<>();

        // Inizializzazione
        coda.add(part);
        visitati.add(part);
        distanza.put(part, 0);
        predecessore.put(part, null);

        // BFS
        while (!coda.isEmpty()) {
            String u = coda.poll();

            for (String v : getAdiacenti(u)) {
                if (!visitati.contains(v)) {
                    visitati.add(v);
                    coda.add(v);
                    distanza.put(v, distanza.get(u) + 1);
                    predecessore.put(v, u);
                }
            }
        }

        // Ricostruzione del cammino da part a dest
        List<String> cammino = new ArrayList<>();
        String nodoCorrente = dest;

        // Se dest non è raggiungibile, predecessore.get(dest) sarà null e non avrà distanza
        if (!distanza.containsKey(dest)) {
            System.out.println("Nessun cammino da " + part + " a " + dest);
            return;
        }

        // Risalgo dai predecessori fino a part
        while (nodoCorrente != null) {
            cammino.add(nodoCorrente);
            nodoCorrente = predecessore.get(nodoCorrente);
        }

        // Inverto la lista perché è al contrario (da dest a part)
        Collections.reverse(cammino);

        // Stampo il risultato
        System.out.println("Cammino minimo da " + part + " a " + dest + ": " + cammino);
        System.out.println("Lunghezza: " + (cammino.size() - 1) + " archi");
    }

    public boolean connessoBFS(){
        Set<String> nodi = getNodi();

        // Se il grafo è vuoto o ha un solo nodo, è connesso
        if (nodi.isEmpty() || nodi.size() == 1) {
            return true;
        }

        // Prendo un nodo qualsiasi come partenza (es. il primo)
        String partenza = nodi.iterator().next();

        // Strutture per BFS
        Queue<String> coda = new LinkedList<>();
        Set<String> visitati = new HashSet<>();

        // Inizializzo BFS
        coda.add(partenza);
        visitati.add(partenza);

        // BFS
        while (!coda.isEmpty()) {
            String u = coda.poll();

            for (String v : getAdiacenti(u)) {
                if (!visitati.contains(v)) {
                    visitati.add(v);
                    coda.add(v);
                }
            }
        }

        // Il grafo è connesso se BFS ha visitato tutti i nodi
        return visitati.size() == nodi.size();
    }

    private void dfs(String start, Set<String> visitati) {
        visitati.add(start);

        for (String vicino : getAdiacenti(start)) {
            if (!visitati.contains(vicino)) {
                dfs(vicino, visitati);
            }
        }
    }

    public boolean connessoDFS(){
        Set<String> nodi = getNodi();

        // Se il grafo è vuoto o ha un solo nodo, è connesso
        if (nodi.isEmpty() || nodi.size() == 1) {
            return true;
        }

        // Prendo un nodo qualsiasi come partenza
        String partenza = nodi.iterator().next();
        Set<String> visitati = new HashSet<>();

        // Faccio DFS da partenza
        dfs(partenza, visitati);

        // Il grafo è connesso se DFS ha visitato tutti i nodi
        return visitati.size() == nodi.size();
    }

    private boolean CicloDFS(String nodo, Set<String> visitati, String genitore) {
        visitati.add(nodo);

        for (String vicino : getAdiacenti(nodo)) {
            if (!visitati.contains(vicino)) {
                // Se il vicino non è visitato, scendo ricorsivamente
                if (CicloDFS(vicino, visitati, nodo)) {
                    return true;
                }
            } else if (!vicino.equals(genitore)) {
                // Il vicino è già visitato e non è il genitore → ciclo!
                return true;
            }
        }

        return false;
    }

    public boolean contieneCiclo(){
        Set<String> visitati = new HashSet<>();

        // Per ogni nodo non ancora visitato, avvio DFS
        for (String nodo : getNodi()) {
            if (!visitati.contains(nodo)) {
                // Se trovo un ciclo in una componente, il grafo ha cicli
                if (CicloDFS(nodo, visitati, null)) {
                    return true;
                }
            }
        }

        return false;
    }
}

