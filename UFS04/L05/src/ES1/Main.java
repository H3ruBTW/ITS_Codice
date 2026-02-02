package ES1;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ContoBancario conto = new ContoBancario(1000.0);  // Saldo iniziale 1000€
        
        while (true) {
            System.out.println("\n=== MENU CONTO BANCARIO ===");
            System.out.println("Saldo attuale: " + conto.getSoldi() + "€");
            System.out.println("1. Prelievo");
            System.out.println("2. Deposito");
            System.out.println("3. Pagamento (max 25%)");
            System.out.println("4. Stampa transazioni (tutte/tipo)");
            System.out.println("5. Filtra per descrizione");
            System.out.println("6. Filtra per date (da/a)");
            System.out.println("0. Esci");
            System.out.print("Scegli: ");
            
            String scelta = scanner.nextLine();
            
            try {
                switch (scelta) {
                    case "1":
                        System.out.print("Importo prelievo: ");
                        double prelievo = Double.parseDouble(scanner.nextLine());
                        System.out.print("Descrizione: ");
                        String descP = scanner.nextLine();
                        conto.doPrelievo(prelievo, descP);
                        System.out.println("Prelievo OK!");
                        break;
                        
                    case "2":
                        System.out.print("Importo deposito: ");
                        double deposito = Double.parseDouble(scanner.nextLine());
                        System.out.print("Descrizione: ");
                        String descD = scanner.nextLine();
                        conto.doDeposito(deposito, descD);
                        System.out.println("Deposito OK!");
                        break;
                        
                    case "3":
                        System.out.print("Importo pagamento: ");
                        double pagamento = Double.parseDouble(scanner.nextLine());
                        System.out.print("Descrizione: ");
                        String descPag = scanner.nextLine();
                        conto.doPagamento(pagamento, descPag);
                        System.out.println("Pagamento OK!");
                        break;
                        
                    case "4":
                        System.out.print("Filtro (Prelievo/Deposito/Pagamento/Nessuno): ");
                        String filtro = scanner.nextLine();
                        conto.printTransazioni(filtro);
                        break;
                        
                    case "5":
                        System.out.print("Descrizione da cercare: ");
                        String descCerca = scanner.nextLine();
                        conto.printTransazioniByDesc(descCerca);
                        break;
                        
                    case "6":
                        System.out.print("Da (dd/MM/yyyy, vuoto per ometti): ");
                        String da = scanner.nextLine();
                        System.out.print("A (dd/MM/yyyy, vuoto per ometti): ");
                        String a = scanner.nextLine();
                        conto.printTransazioniByDate(da.isEmpty() ? "0" : da, a.isEmpty() ? "0" : a);
                        break;
                        
                    case "0":
                        System.out.println("Arrivederci!");
                        scanner.close();
                        return;
                        
                    default:
                        System.out.println("Scelta non valida!");
                }
            } catch (Exception e) {
                System.out.println("Errore: " + e.getMessage());
            }
        }
    }
}
