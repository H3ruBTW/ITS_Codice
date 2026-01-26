package ES2;

public class ES2 {
    public static void main(String[] args) {
        Studente studente2 = new Studente("Francesco");

        studente2.NewVoto(0);
        studente2.NewVoto(31);
        studente2.NewVoto(25);
        studente2.NewVoto(25);
        studente2.NewVoto(25);
        studente2.NewVoto(22);
        studente2.NewVoto(22);
        studente2.NewVoto(21);
        studente2.NewVoto(18);

        System.out.println("Nome: " + studente2.getNome());
        System.out.println("Media: " + studente2.media());
        System.out.println("Voto Minimo: " + studente2.VMin());
        System.out.println("Voto Massimo: " + studente2.VMax());
        System.out.println("Voto Mediano: " + studente2.mediana());
        System.out.println("Promosso?: " + (studente2.isPromosso() ? "Si" : "No"));
        System.out.println("Insufficienze: " + studente2.insufficienze());
        System.out.println("Moda dei voti: " + studente2.moda());

        Studente studente = new Studente("Francesco");

        studente.NewVoto(0);
        studente.NewVoto(31);
        studente.NewVoto(25);
        studente.NewVoto(25);
        studente.NewVoto(25);
        studente.NewVoto(22);
        studente.NewVoto(22);
        studente.NewVoto(21);
        studente.NewVoto(18);

        System.out.println("Nome: " + studente.getNome());
        System.out.println("Media: " + studente.media());
        System.out.println("Voto Minimo: " + studente.VMin());
        System.out.println("Voto Massimo: " + studente.VMax());
        System.out.println("Voto Mediano: " + studente.mediana());
        System.out.println("Promosso?: " + (studente.isPromosso() ? "Si" : "No"));
        System.out.println("Insufficienze: " + studente.insufficienze());
        System.out.println("Moda dei voti: " + studente.moda());
    }
}
