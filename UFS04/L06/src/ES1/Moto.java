package ES1;

public class Moto extends Veicolo {
    private int cilindrata;

    public int getCilindrata() {
        return cilindrata;
    }

    public void setCilindrata(int cilindrata) {
        if(cilindrata > 0)
            this.cilindrata = cilindrata;
        else
            throw new IllegalArgumentException("Cilindrata inferiore a 0 non consentita");
    }

    public Moto(String targa, String marca, int annoImm, double prezzo, int cilindrata){
        super(targa, marca, annoImm, prezzo);

        if(cilindrata > 0)
            this.cilindrata = cilindrata;
        else
            throw new IllegalArgumentException("Cilindrata inferiore a 0 non consentita");
    }   

    @Override
    public double getAssicurazione(){
        double assicurazione = 300;

        if(cilindrata<125 || cilindrata>1000)
            assicurazione *= 1.2;

        return assicurazione;
    }
}
