package ES1;

public class ES1 {
    public static void main(String[] args) {
        Libro libro1 = new Libro("J.K. Rowling", "Harry Potter", 450, 40.50, 1980);

        System.out.println("Valore per pagina: " + libro1.VPP() + "€");
        System.out.println("Età: " + libro1.EtaLibro());
        System.out.println("Vintage?: " + (libro1.isVintage() ? "Si" : "No"));
        System.out.println("Con sconto: " + libro1.Sconto(25.0) + "€");
        libro1.Lunghezza();
    }
}
