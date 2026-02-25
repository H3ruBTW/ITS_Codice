package evento;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;

public class GestoreEvento {
    ArrayList<Evento> eventi = new ArrayList<>();
    ArrayList<Prenotazione> prenotazioni = new ArrayList<>();

    public void aggiungiEvento(String tipo, String nome, LocalDate data, 
        int numero_posti, double prezzo, /*Tipo che funziona per tutt'e due le classi*/String genere_relatore){
            
        try {
            if(tipo.equalsIgnoreCase("concerto")){
                eventi.add(new Concerto(nome, data, numero_posti, prezzo, genere_relatore));
            } else if(tipo.equalsIgnoreCase("conferenza")){
                eventi.add(new Conferenza(nome, data, numero_posti, prezzo, genere_relatore));
            } else {
                throw new IllegalArgumentException("Tipo errato");
            }  
        } catch (IllegalArgumentException e) {
            System.out.println("Errore: " + e.getMessage());
        }
        
    }

    public void prenotaBiglietti(String nome_evento, String nome_persona, int n_persone){
        for (Evento evento : eventi) {
            if(evento.getNome().equalsIgnoreCase(nome_evento)){
                if(evento.getPostiDisponibili() < n_persone){
                    try {
                        prenotazioni.add(new Prenotazione(evento, nome_persona, n_persone));
                        evento.addPostiPresi(n_persone);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Impossibile aggiungere prenotazione: " + e.getMessage());
                    }
                    return;
                } 
            }
        }

        System.out.println("Nessun evento trovato");
    }

    public void cercaEventi(String tipo, String nome, double prezzo_min, 
        double prezzo_max, int n_persone) throws IllegalArgumentException{

        boolean trovato = false;
        for (Evento evento : eventi) {

            if(!tipo.isBlank()){
                //se questo non è della stessa tipologia va alla prossima iterazione
                if(tipo.equalsIgnoreCase("concerto")){
                    if(!(evento instanceof Concerto)){
                        continue;
                    }
                } else if(tipo.equalsIgnoreCase("conferenza")){
                    if(!(evento instanceof Conferenza)){
                        continue;
                    }
                } else 
                    throw new IllegalArgumentException("Tipo errato");
            }

            //trova tutti gli eventi che iniziano con l'input (funziona anche con "" come input)
            if(!evento.getNome().toLowerCase().startsWith(nome.toLowerCase()))
                continue;

            if(prezzo_max != 0 && prezzo_min != 0){
                //Errore <0
                if(prezzo_max < 0 || prezzo_min < 0)
                    throw new IllegalArgumentException("<0");

                //errore dell'utente per il prezzo e switcho
                if(prezzo_max < prezzo_min){
                    double temp = prezzo_max;
                    prezzo_max = prezzo_min;
                    prezzo_min = temp;
                }

                //Trovo se si trova nel range di quello che cerca la persona
                if(evento.getPrezzoDinamico() < prezzo_min || evento.getPrezzoDinamico() > prezzo_max)
                    continue;
            }

            if(n_persone < 0)
                throw new IllegalArgumentException("<0");

            //vedo se ci sono abbastanza posti disponibili oppure se metto 0 sarà sempre true tranne se è sold out
            if(evento.getPostiDisponibili() < n_persone)
                continue;

            //stampo l'evento
            evento.descrizioneEvento();
            trovato = true;
        }

        if(!trovato)
            System.out.println("Nessun evento trovato");
    }

    public void salvaPrenotazioniTXT(){
        LocalDate today = LocalDate.now();
        //Apro un buffered writer per scrivere su un file
        try (BufferedWriter bf = new BufferedWriter(new FileWriter("pren" + today.toString() + ".txt"))) {
            int i = 0;
            //scrivo ogni prenotazione tramite il .toString() sul file
            for (Prenotazione prenotazione : prenotazioni) {
                bf.write(i + " " + prenotazione.toString());
                bf.newLine();
                i++;
            }

            bf.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
