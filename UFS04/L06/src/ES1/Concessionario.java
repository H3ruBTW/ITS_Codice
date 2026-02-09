package ES1;
/*Vende auto e moto, tutte e due i veicoli hanno una marca,
un prezzo e un anno d'immatricolazione, tutte e due devono pagare l'assicurazione (metodo per calcolarla),
per calcolare per le auto si parte da 500€ (suv: +10%, sportiva: +20%) per le moto parte da 300€ (cilindrata <125: +20%, 
cilindrata >1000: +20%), il prezzo cambia per le auto (elettriche: -25% fino ad un massimo 5000€)
Sulla concessionaria si può aggiungere i veicoli, ricerca per prezzo tipo e assicurazione, vendita auto (salvarle)(veicolo, data, prezzo), 
stampa vendite
fatturato di uno specifico giorno o in un range
*/

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Iterator;

public class Concessionario {
    private ArrayList<Veicolo> Veicoli = new ArrayList<>();
    private ArrayList<Vendita> Vendite = new ArrayList<>();

    public void addAuto(String targa, String marca, int annoImm, double prezzo, String carburante, String tipologia){
        Veicoli.add(new Auto(targa, marca, annoImm, prezzo, carburante, tipologia));
    }

    public void addMoto(String targa, String marca, int annoImm, double prezzo, int cilindrata){
        Veicoli.add(new Moto(targa, marca, annoImm, prezzo, cilindrata));
    }

    public void addVendita(String targa){
        if(Veicolo.checkTarga(targa)){
            Iterator<Veicolo> iterator = Veicoli.iterator();

            while (iterator.hasNext()) {
                Veicolo veicolo = iterator.next();

                if(veicolo.getTarga().equalsIgnoreCase(targa)){
                    Vendite.add(new Vendita(veicolo));
                    iterator.remove();
                    return;
                }
            }

            System.out.println("Nessun veicolo trovato con questa targa");
        }
    }

    public void printVendite(){
        System.out.println("Vendite effettuate");
        for (Vendita vendita : Vendite) {
            Veicolo veicolo = vendita.getVeicolo();

            if(veicolo instanceof Auto){
                    Auto auto = (Auto) veicolo;
                    System.out.println("Tipo: Auto");
                    System.out.println("Carburante: " + auto.getCarburante());
                    System.out.println("Tipologia: " + auto.getTipologia());
                    System.out.println("Assicurazione: " + auto.getAssicurazione());
                } else {
                    Moto moto = (Moto) veicolo;
                    System.out.println("Tipo: Moto");
                    System.out.println("Cilindrata: " + moto.getCilindrata());
                    System.out.println("Assicurazione: " + moto.getAssicurazione());
                }

            System.out.println("Targa: " + veicolo.getTarga());
            System.out.println("Anno Immatricolazione: " + veicolo.getAnnoImm());
            System.out.println("Marca: " + veicolo.getMarca());
            System.out.println("Prezzo: " + veicolo.getPrezzo());
            System.out.println("In data: " + vendita.getData());
        }
    }

    public void ricercaAuto(double prIniziale, double prFinale, String tipo, double asIniziale, double asFinale){
        System.out.println("Veicoli trovati");
        Iterator<Veicolo> iterator = Veicoli.iterator();

        if(!tipo.equalsIgnoreCase("Auto") && !tipo.equalsIgnoreCase("Moto") && !tipo.equalsIgnoreCase("null"))
            throw new IllegalArgumentException("Tipo non accettato");

        while (iterator.hasNext()) {
            Veicolo veicolo = iterator.next();

            if(prIniziale >= 0)
                if(veicolo.getPrezzo() < prIniziale)
                    continue;

            if(prFinale >= 0)
                if(veicolo.getPrezzo() > prFinale)
                    continue;

            if(tipo.equalsIgnoreCase("null"))
                continue;

            if(tipo.equalsIgnoreCase("Auto")){
                if(!(veicolo instanceof Auto))
                    continue;
                else {
                    Auto auto = (Auto) veicolo;
                    if(auto.getAssicurazione() < asIniziale)
                        continue;

                    if(auto.getAssicurazione() > asFinale)
                        continue;
                }
            } else {
                if(!(veicolo instanceof Moto))
                    continue;
                else {
                    Moto moto = (Moto) veicolo;
                    if(moto.getAssicurazione() < asIniziale)
                        continue;

                    if(moto.getAssicurazione() > asFinale)
                        continue;
                }
            }

            if(veicolo instanceof Auto){
                    Auto auto = (Auto) veicolo;
                    System.out.println("Tipo: Auto");
                    System.out.println("Carburante: " + auto.getCarburante());
                    System.out.println("Tipologia: " + auto.getTipologia());
                    System.out.println("Assicurazione: " + auto.getAssicurazione());
                } else {
                    Moto moto = (Moto) veicolo;
                    System.out.println("Tipo: Moto");
                    System.out.println("Cilindrata: " + moto.getCilindrata());
                    System.out.println("Assicurazione: " + moto.getAssicurazione());
            }

            System.out.println("Targa: " + veicolo.getTarga());
            System.out.println("Anno Immatricolazione: " + veicolo.getAnnoImm());
            System.out.println("Marca: " + veicolo.getMarca());
            System.out.println("Prezzo: " + veicolo.getPrezzo());

        }
    }

    public double getFatturatoGiorno(int gg, int MM, int YYYY){
        double fatturato = 0;

        LocalDate giorno = LocalDate.of(YYYY, MM, gg);
        ZoneId fuso = ZoneId.of("Europe/Rome");

        ZonedDateTime inizio = giorno.atStartOfDay(fuso);
        ZonedDateTime fine = giorno.atTime(LocalTime.MAX).atZone(fuso);

        Iterator<Vendita> iterator = Vendite.iterator();

        while (iterator.hasNext()) {
            Vendita vendita = iterator.next();

            if(vendita.getData().isAfter(inizio) && vendita.getData().isBefore(fine)){
                if(vendita.getVeicolo() instanceof Auto a)
                    fatturato += a.getPrezzo();
                else if(vendita.getVeicolo() instanceof Moto m)
                    fatturato += m.getPrezzo();
            }
        }

        return fatturato;
    }

    public double getFatturatoGiorni(int gg, int MM, int YYYY, int gg2, int MM2, int YYYY2){
        double fatturato = 0;

        LocalDate giorno = LocalDate.of(YYYY, MM, gg);
        LocalDate giorno2 = LocalDate.of(YYYY2, MM2, gg2);
        ZoneId fuso = ZoneId.of("Europe/Rome");

        ZonedDateTime inizio = giorno.atStartOfDay(fuso);
        ZonedDateTime fine = giorno2.atTime(LocalTime.MAX).atZone(fuso);

        Iterator<Vendita> iterator = Vendite.iterator();

        while (iterator.hasNext()) {
            Vendita vendita = iterator.next();

            if(vendita.getData().isAfter(inizio) && vendita.getData().isBefore(fine)){
                if(vendita.getVeicolo() instanceof Auto a)
                    fatturato += a.getPrezzo();
                else if(vendita.getVeicolo() instanceof Moto m)
                    fatturato += m.getPrezzo();
            }
        }

        return fatturato;
    }
}
