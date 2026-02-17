package negozio;

import java.util.Arrays;
import java.util.HashSet;

public class Smartphone extends Prodotto {
    private String os;
    private short memoriaGB;
    HashSet<String> possibiliOS = new HashSet<>(Arrays.asList("android", "ios", "windows", "harmonyos"));

    public String getOs() {
        return os;
    }

    public short getMemoriaGB() {
        return memoriaGB;
    }

    public void setOs(String os) throws IllegalArgumentException {
        if(possibiliOS.contains(os.toLowerCase()))
            this.os = os.toLowerCase();
        else 
            throw new IllegalArgumentException("OS non esistente");
    }

    public void setMemoriaGB(short memoriaGB) {
        if(memoriaGB > 0)
            this.memoriaGB = memoriaGB;
    }

    public Smartphone(String codice, String marca, String modello, short annoUscita, double prezzo, String os, short memoriaGB) throws IllegalArgumentException{
        super(codice, marca, modello, annoUscita, prezzo);
        setOs(os);
        setMemoriaGB(memoriaGB);
    }

    @Override
    public double calcolaGaranzia() {
        double garanzia = 80;
        final double garanzia_base = 80;

        if(os.equals("ios"))
            garanzia += garanzia_base * 0.2;

        if(memoriaGB >= 256)
            garanzia += garanzia_base * 0.15;

        return garanzia;
    }

    @Override
    public double getPrezzo() {
        if(os.equals("harmonyos"))
            if(prezzo * 0.12 < 100)
                return prezzo * 0.88;
            else
                return prezzo - 100;
        else
            return prezzo;

    }

    @Override
    public String toString() {
        return super.toString() + 
        "\nTipo Smartphone" +
        "\nOS " + os +
        "\nMemoria in GB " + memoriaGB;
    }
}
