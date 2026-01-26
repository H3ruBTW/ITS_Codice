package ES2;

public class ES2 {
    public static void main(String[] args) {
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
