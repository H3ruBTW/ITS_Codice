package ES1;

public class ES1 {
    public static void main(String[] args) {
        Persona persona = new Persona("Giuseppe", "Rossi", "15/05/1965");
        Studente studente = new Studente("Antonio", "Bianchi", "17/02/2002", 3865);
        Lavoratore lavoratore = new Lavoratore("Francesco", "Carella", "Non sono nato", 5);

        persona.presentati();
        studente.presentati();
        lavoratore.presentati();
    }
}
