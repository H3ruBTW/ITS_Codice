package ES1;

public abstract class Veicolo {
    protected String marca, targa;
    protected int annoImm;
    protected double prezzo;

    public String getTarga() {
        return targa;
    }

    public void setTarga(String targa) {
        if(!targa.isBlank() && checkTarga(targa))
            this.targa = targa.toUpperCase();
        else
            throw new IllegalArgumentException("Targa non valida");
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        if(!marca.isBlank())
            this.marca = marca;
        else
            throw new IllegalArgumentException("Non hai inserito la marca");
    }

    public int getAnnoImm() {
        return annoImm;
    }

    public void setAnnoImm(int annoImm) {
        if(annoImm<0)
            this.annoImm = annoImm;
        else
            throw new IllegalArgumentException("Anno non consentito");
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        if(prezzo>0)
            this.prezzo = prezzo;
        else
            throw new IllegalArgumentException("Prezzo inferiore a 0 non consentito");
    }

    public Veicolo(String targa, String marca, int annoImm, double prezzo){
        if(!targa.isBlank() && checkTarga(targa))
            this.targa = targa.toUpperCase();
        else
            throw new IllegalArgumentException("Targa non valida");

        if(!marca.isBlank())
            this.marca = marca;
        else
            throw new IllegalArgumentException("Non hai inserito la marca");

        if(annoImm<0)
            this.annoImm = annoImm;
        else
            throw new IllegalArgumentException("Anno non consentito");

        if(prezzo>0)
            this.prezzo = prezzo;
        else
            throw new IllegalArgumentException("Prezzo inferiore a 0 non consentito");
    }

    public abstract double getAssicurazione();

    public static boolean checkTarga(String targa){
        if(targa.matches("^[a-zA-Z]{2}\\d{3}[a-zA-Z]{2}$"))
            return true;

        return false;
    }
}
