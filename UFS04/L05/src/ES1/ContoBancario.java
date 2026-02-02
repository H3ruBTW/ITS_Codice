package ES1;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ContoBancario {
    //prelievo, deposito, pagamento (max 25% dei soldi disponibili), traccia pagamenti
    //Metodi: Stampa tutti i pagamenti (filtro prelievi, depositi, pagamenti)
    //Filtrare le transizioni in base alla descrizione
    private double soldi;
    private ArrayList<Transazione> Transazioni = new ArrayList<>();

    public ContoBancario(double soldi) {
        this.soldi = soldi;
    }

    public double getSoldi() {
        return soldi;
    }

    public void doPrelievo(double soldiPrelievo, String descrizione){
        
        if(soldiPrelievo <= soldi){
            soldi -= soldiPrelievo;
            Transazioni.add(new Transazione("Prelievo", descrizione, soldiPrelievo, LocalDateTime.now().withNano(0)));
        } else {
            throw new IllegalArgumentException("Saldo non disponibile per questa operazione");
        }
    }

    public void doDeposito(double soldiDeposito, String descrizione){
        
        if(soldiDeposito > 0){
            soldi += soldiDeposito;
            Transazioni.add(new Transazione("Deposito", descrizione, soldiDeposito, LocalDateTime.now().withNano(0)));
        } else {
            throw new IllegalArgumentException("Importo inferiore a 0€");
        }
    }

    public void doPagamento(double soldiPagamento, String descrizione){
        
        if(soldiPagamento <= soldi * 0.25){
            soldi -= soldiPagamento;
            Transazioni.add(new Transazione("Pagamento", descrizione, soldiPagamento, LocalDateTime.now().withNano(0)));
        } else {
            throw new IllegalArgumentException("Pagamento non riuscito, richiesta superiore a 25% dei soldi sul conto");
        }
    }

    public void printTransazioni(String filtro){
        System.out.println("Transizioni sul tuo conto");
        if(!filtro.equals("Nessuno"))
            System.out.println("In base a: " + filtro);
        System.out.println("-----------------------------------------------------");
        if(!Transazioni.isEmpty()){
            switch (filtro) {
                case "Prelievo", "Deposito", "Pagamento":
                    int ripetizioni = 0;
                    for (Transazione transazione : Transazioni) {
                        if(transazione.getTipologia().equals(filtro)){
                            System.out.println("Tipologia: " + transazione.getTipologia());
                            System.out.println("Fatta in data: " + transazione.getData());
                            System.out.println("Descrizione: " + transazione.getDescrizione());
                            System.out.println("Quantitativo di soldi: " + transazione.getQuantitaSoldi());
                            System.out.println("-----------------------------------------------------");
                            ripetizioni++;
                        }
                    }
                    if(ripetizioni == 0)
                        System.out.println("Nessuna transazione trovata con il filtro corrente");
                    break;
            
                default:
                    for (Transazione transazione : Transazioni) {
                        System.out.println("Tipologia: " + transazione.getTipologia());
                        System.out.println("Fatta in data: " + transazione.getData());
                        System.out.println("Descrizione: " + transazione.getDescrizione());
                        System.out.println("Quantitativo di soldi: " + transazione.getQuantitaSoldi());
                        System.out.println("-----------------------------------------------------");
                    }
                    break;
            }
        } else {
            System.out.println("Nessuna transazione trovata sul tuo account");
        }
        
    }

    public void printTransazioniByDesc(String descrizione){
        //Il replace all toglie i doppi spazi
        descrizione = descrizione.toLowerCase().replaceAll("\\s+", " ");
        System.out.println("Transizioni sul tuo conto");
        System.out.println("...che contegono la descrizione: " + descrizione);
        System.out.println("-----------------------------------------------------");
        if(!Transazioni.isEmpty()){
            int ripetizioni = 0;
            for (Transazione transazione : Transazioni) {
                if(transazione.getDescrizione().toLowerCase().replaceAll("\\s+", " ").contains(descrizione)){
                    System.out.println("Tipologia: " + transazione.getTipologia());
                    System.out.println("Fatta in data: " + transazione.getData());
                    System.out.println("Descrizione: " + transazione.getDescrizione());
                    System.out.println("Quantitativo di soldi: " + transazione.getQuantitaSoldi());
                    System.out.println("-----------------------------------------------------");
                    ripetizioni++;
                }
            }

            if(ripetizioni == 0)
                System.out.println("Nessuna transazione trovata con il filtro corrente");
        } else {
            System.out.println("Nessuna transazione trovata sul tuo account");
        }
        
    }

    public void printTransazioniByDate(String dataInizio, String dataFine){
        LocalDateTime inizio, fine;
        int ripetizioni = 0;
        System.out.println("Transizioni sul tuo conto");
        if(!Transazioni.isEmpty()){
            if(!dataInizio.equals("0")){
                inizio = trovaData(dataInizio);
                if(!dataFine.equals("0")){
                    System.out.println("Dalla data " + dataInizio + " a " + dataFine);
                    System.out.println("-----------------------------------------------------");
                    fine = trovaData(dataFine);
                    for (Transazione transazione : Transazioni) {
                        LocalDateTime data = transazione.getData();
                        if(data.isAfter(inizio) && data.isBefore(fine)){
                            System.out.println("Tipologia: " + transazione.getTipologia());
                            System.out.println("Fatta in data: " + transazione.getData());
                            System.out.println("Descrizione: " + transazione.getDescrizione());
                            System.out.println("Quantitativo di soldi: " + transazione.getQuantitaSoldi());
                            System.out.println("-----------------------------------------------------");
                            ripetizioni++;
                        }
                    }

                    if(ripetizioni == 0)
                        System.out.println("Nessuna transazione trovata con questo filtro");
                } else {
                    System.out.println("Dalla data " + dataInizio);
                    System.out.println("-----------------------------------------------------");

                    for (Transazione transazione : Transazioni) {
                        LocalDateTime data = transazione.getData();
                        if(data.isAfter(inizio)){
                            System.out.println("Tipologia: " + transazione.getTipologia());
                            System.out.println("Fatta in data: " + transazione.getData());
                            System.out.println("Descrizione: " + transazione.getDescrizione());
                            System.out.println("Quantitativo di soldi: " + transazione.getQuantitaSoldi());
                            System.out.println("-----------------------------------------------------");
                            ripetizioni++;
                        }
                    }

                    if(ripetizioni == 0)
                        System.out.println("Nessuna transazione trovata con questo filtro");
                }

            } else {
                if(!dataFine.equals("0")){
                    System.out.println("Fino a " + dataFine);
                    System.out.println("-----------------------------------------------------");
                    fine = trovaData(dataFine);

                    for (Transazione transazione : Transazioni) {
                        LocalDateTime data = transazione.getData();
                        if(data.isBefore(fine)){
                            System.out.println("Tipologia: " + transazione.getTipologia());
                            System.out.println("Fatta in data: " + transazione.getData());
                            System.out.println("Descrizione: " + transazione.getDescrizione());
                            System.out.println("Quantitativo di soldi: " + transazione.getQuantitaSoldi());
                            System.out.println("-----------------------------------------------------");
                            ripetizioni++;
                        }
                    }

                    if(ripetizioni == 0)
                        System.out.println("Nessuna transazione trovata con questo filtro");
                } else {
                    throw new IllegalArgumentException("Nessuna data inserita");
                }
            }
        } else {
            System.out.println("Nessuna transazione sul tuo conto");
        }
        
    }

    private LocalDateTime trovaData(String data){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            return LocalDate.parse(data, dtf).atStartOfDay();
        } catch (Exception e) {
            throw new IllegalArgumentException("Formato data errato (gg/MM/aaaa)");
        }
    }
}