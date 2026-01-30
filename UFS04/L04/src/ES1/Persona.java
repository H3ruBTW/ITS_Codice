package ES1;
//Nome cognome data di nascita metodo presentati
public class Persona {
    protected String Nome, Cognome, DataDN;

    public Persona(String Nome, String Cognome, String DataDN){
        this.Nome = Nome;
        this.Cognome = Cognome;
        this.DataDN = DataDN;
    }

    public void presentati(){
        System.out.println("Ciao, Mi chiamo " + Nome + " " + Cognome + "\nSono nato il " + DataDN);
    }
}
