package negozio;

import java.time.LocalDate;
import java.util.ArrayList;

public class Negozio {
    private ArrayList<Prodotto> magazzino = new ArrayList<>();
    private ArrayList<Vendita> vendite = new ArrayList<>();

    public void addSmartphone(String codice, String marca, String modello, short annoUscita, 
        double prezzo, String os, short memoriaGB){

        for (Prodotto prodotto : magazzino) {
            if(prodotto.getCodice() == codice){
                System.out.println("Prodotto già esistente");
                return;
            }
        }

        try {
            magazzino.add(new Smartphone(codice, marca, modello, annoUscita, prezzo, os, memoriaGB));
        } catch (IllegalArgumentException e) {
            System.out.println("Errore di aggiunta: " + e.getMessage());
        }
    }

    public void addLaptop(String codice, String marca, String modello, short annoUscita, 
        double prezzo, String processore, String tipologia){

        for (Prodotto prodotto : magazzino) {
            if(prodotto.getCodice() == codice){
                System.out.println("Prodotto già esistente");
                return;
            }
        }

        try {
            magazzino.add(new Laptop(codice, marca, modello, annoUscita, prezzo, processore, tipologia));
        } catch (IllegalArgumentException e) {
            System.out.println("Errore di aggiunta: " + e.getMessage());
        }
    }

    public void registraVendita(String codice, String metodo){
        for (Prodotto prodotto : magazzino) {
            if(prodotto.getCodice() == codice){
                try {
                    vendite.add(new Vendita(prodotto, metodo));
                } catch (IllegalArgumentException e) {
                    System.out.println("Errore di aggiunta: " + e.getMessage());
                }   
                return;
            }
        }

        System.out.println("Prodotto non trovato");
    }

    public void printVendite(){
        System.out.println("Vendite trovate");
        for (Vendita vendita : vendite) {
            System.out.println(vendita);
            System.out.println();
        }
    }

    public void ricercaProdotti(double prezzoMin, double prezzoMax, String tipo, double garanziaMin, double garanziaMax) throws IllegalArgumentException{
        System.out.println("Prodotti trovati con i filtri\n");
        
        for (Prodotto prodotto : magazzino) {
            if(prodotto.getPrezzo() < prezzoMin || prodotto.getPrezzo() > prezzoMax)
                continue;

            if(!tipo.equalsIgnoreCase("Smartphone") && !tipo.equalsIgnoreCase("Laptop") && !tipo.isBlank())
                throw new IllegalArgumentException("Tipo non accettato");

            if(!tipo.isBlank()){
                if(tipo.equalsIgnoreCase("Smartphone") && !(prodotto instanceof Smartphone))
                    continue;
                else if(tipo.equalsIgnoreCase("Laptop") && !(prodotto instanceof Laptop))
                    continue;
            }

            if(prodotto.calcolaGaranzia() < garanziaMin || prodotto.calcolaGaranzia() > garanziaMax)
                continue;
            
            System.out.println(prodotto);
        }
    }

    public double getIncassoGG(int gg, int mm, int yyyy){
        LocalDate ld = LocalDate.now();
        double incasso = 0;
        
        try {
            ld = LocalDate.of(yyyy, mm, gg);
        } catch (Exception e) {
            System.out.println("Errore data, imposto ad oggi");
        } finally {
            System.out.println("Vendite trovate");
            for (Vendita vendita : vendite) {
                if(vendita.getDataVendita().isEqual(ld)){
                    incasso += vendita.getProdotto().getPrezzo();
                }
            }
        }

        return incasso;
    }

    public double getIncassoRange(int gg1, int mm1, int yyyy1, int gg2, int mm2, int yyyy2){
        LocalDate inizio = LocalDate.now().minusDays(1);
        LocalDate fine = LocalDate.now();
        double incasso = 0;
        
        try {
            inizio = LocalDate.of(yyyy1, mm1, gg1);
            fine = LocalDate.of(yyyy2, mm2, gg2);

            if(inizio.isAfter(fine)){
                LocalDate temp = inizio;
                inizio = fine;
                fine = temp;
            }
        } catch (Exception e) {
            System.out.println("Errore data, imposto ad oggi e ieri per range");
        } finally {
            System.out.println("Vendite trovate");
            for (Vendita vendita : vendite) {
                if(vendita.getDataVendita().isAfter(inizio) && vendita.getDataVendita().isBefore(fine)){
                    incasso += vendita.getProdotto().getPrezzo();
                }
            }
        }

        return incasso;
    }
}
