package negozio;

public class Main {
    public static void main(String[] args) {
        Negozio negozio = new Negozio();
        
        // Aggiungi prodotti validi
        System.out.println("=== AGGIUNTA PRODOTTI VALIDI ===");
        negozio.addSmartphone("AB12345", "Samsung", "Galaxy S24", 
            (short)2024, 899.99, "android", (short)256);
        negozio.addSmartphone("CD67890", "Apple", "iPhone 15", 
            (short)2024, 1199.99, "ios", (short)512);
        negozio.addSmartphone("ZZ99999", "Huawei", "P60", 
            (short)2023, 699.99, "harmonyos", (short)128);
        negozio.addLaptop("EF11111", "Dell", "XPS 15", 
            (short)2023, 1499.99, "Intel i7", "ultrabook");
        negozio.addLaptop("GH22222", "Asus", "ROG Strix", 
            (short)2024, 1899.99, "AMD Ryzen 9", "gaming");
        negozio.addLaptop("IJ33333", "Lenovo", "ThinkPad", 
            (short)2021, 899.99, "Intel i5", "business");
        
        // TEST ERRORI - Codice duplicato
        System.out.println("\n=== TEST: CODICE DUPLICATO ===");
        negozio.addSmartphone("AB12345", "OnePlus", "12", 
            (short)2024, 799.99, "android", (short)128);
        
        // TEST ERRORI - Codice seriale non valido
        System.out.println("\n=== TEST: CODICE SERIALE ERRATO ===");
        negozio.addSmartphone("ABCD123", "Xiaomi", "14", 
            (short)2024, 599.99, "android", (short)128);
        
        // TEST ERRORI - OS non valido
        System.out.println("\n=== TEST: OS NON VALIDO ===");
        negozio.addSmartphone("KL44444", "Nokia", "G50", 
            (short)2023, 299.99, "symbian", (short)64);
        
        // TEST ERRORI - Tipologia laptop non valida
        System.out.println("\n=== TEST: TIPOLOGIA LAPTOP NON VALIDA ===");
        negozio.addLaptop("MN55555", "HP", "Pavilion", 
            (short)2023, 799.99, "Intel i5", "workstation");
        
        // TEST ERRORI - Prezzo negativo
        System.out.println("\n=== TEST: PREZZO NEGATIVO ===");
        negozio.addSmartphone("OP66666", "Sony", "Xperia", 
            (short)2024, -500, "android", (short)128);
        
        // Registra vendite valide
        System.out.println("\n=== REGISTRA VENDITE VALIDE ===");
        negozio.registraVendita("AB12345", "carta");
        negozio.registraVendita("GH22222", "contanti");
        negozio.registraVendita("IJ33333", "finanziamento");
        
        // TEST ERRORI - Vendita prodotto inesistente
        System.out.println("\n=== TEST: VENDITA PRODOTTO INESISTENTE ===");
        negozio.registraVendita("XX99999", "carta");
        
        // TEST ERRORI - Metodo pagamento non valido
        System.out.println("\n=== TEST: METODO PAGAMENTO NON VALIDO ===");
        negozio.registraVendita("CD67890", "bitcoin");
        
        // Stampa vendite
        System.out.println("\n=== STAMPA VENDITE ===");
        negozio.printVendite();
        
        // Ricerca prodotti valida
        System.out.println("\n=== RICERCA (prezzo 500-1500, garanzia 80-150) ===");
        negozio.ricercaProdotti(500, 1500, "", 80, 150);
        
        // TEST ERRORI - Tipo ricerca non valido
        System.out.println("\n=== TEST: TIPO RICERCA NON VALIDO ===");
        try {
            negozio.ricercaProdotti(0, 5000, "Tablet", 0, 500);
        } catch (IllegalArgumentException e) {
            System.out.println("Errore catturato: " + e.getMessage());
        }
        
        // Incasso giornaliero
        System.out.println("\n=== INCASSO OGGI ===");
        double incassoOggi = negozio.getIncassoGG(17, 2, 2026);
        System.out.println("Totale: " + incassoOggi + "€");
        
        // TEST ERRORI - Data non valida
        System.out.println("\n=== TEST: DATA NON VALIDA ===");
        double incassoErrato = negozio.getIncassoGG(32, 13, 2026);
        System.out.println("Totale: " + incassoErrato + "€");
        
        // Incasso range
        System.out.println("\n=== INCASSO RANGE (16-18 Feb 2026) ===");
        double incassoRange = negozio.getIncassoRange(16, 2, 2026, 18, 2, 2026);
        System.out.println("Totale: " + incassoRange + "€");
    }
}
