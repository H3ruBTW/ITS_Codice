package negozio;

import java.util.Arrays;
import java.util.HashSet;

public class Laptop extends Prodotto {
    private String processore, tipologia;
    HashSet<String> possibileTipo = new HashSet<>(Arrays.asList("ultrabook", "gaming", "business"));

    public String getProcessore() {
        return processore;
    }

    public String getTipologia() {
        return tipologia;
    }

    public void setProcessore(String processore) throws IllegalArgumentException {
        if(!processore.isBlank())
            this.processore = processore;
        else
            throw new IllegalArgumentException("Blank");
    }

    public void setTipologia(String tipologia) throws IllegalArgumentException {
        if(possibileTipo.contains(tipologia.toLowerCase()))
            this.tipologia = tipologia.toLowerCase();
        else 
            throw new IllegalArgumentException("Tipo non accettato");
    }

    public Laptop(String codice, String marca, String modello, short annoUscita, 
        double prezzo, String processore, String tipologia) throws IllegalArgumentException {
        super(codice, marca, modello, annoUscita, prezzo);
        setProcessore(processore);
        setTipologia(tipologia);
    }

    @Override
    public double calcolaGaranzia() {
        double garanzia = 120;
        final double garanzia_base = 120;

        if(tipologia.equals("gaming"))
            garanzia += garanzia_base * 0.35;
        else if(tipologia.equals("ultrabook"))
            garanzia += garanzia_base * 0.1;

        return garanzia;
    }

    @Override
    public double getPrezzo() {
        if(tipologia.equals("business") && annoUscita < 2023)
            if(prezzo * 0.20 < 200)
                return prezzo * 0.80;
            else
                return prezzo - 200;
        else 
            return prezzo;
    }

    @Override
    public String toString() {
        return super.toString() +
        "\nTipo Laptop" +
        "\nProcessore " + processore +
        "\nTipologia " + tipologia;
    }
}
