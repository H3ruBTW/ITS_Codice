package ES1;
//matricola, metodo presentati sovrascritto
public class Studente extends Persona {
    private int Matricola;

    public Studente(String Nome, String Cognome, String DataDN, int Matricola){
        super(Nome, Cognome, DataDN);
        this.Matricola = Matricola;
    }

    @Override
    public void presentati(){
        System.out.println("Ciao, Mi chiamo " + Nome + " " + Cognome + "\nSono nato il " + DataDN + "\nIl mio numero di matricola è " + Matricola);
    }
}
