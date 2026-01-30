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
        super.presentati();
        System.out.println("Il mio Salario è di " + Salario + "€ al mese");
    }
}
