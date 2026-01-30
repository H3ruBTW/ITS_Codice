package ES1;
//salario, metodo presentati sovrascritto
public class Lavoratore extends Persona {
    private int Salario;

    public Lavoratore(String Nome, String Cognome, String DataDN, int Salario){
        super(Nome, Cognome, DataDN);
        this.Salario = Salario;
    }

    @Override
    public void presentati(){
        System.out.println("Ciao, Mi chiamo " + Nome + " " + Cognome + "\nSono nato il " + DataDN + "\nIl mio Salario è di " + Salario + "€ al mese");
    }
}
