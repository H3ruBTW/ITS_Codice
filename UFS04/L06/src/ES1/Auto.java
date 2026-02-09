package ES1;

import java.util.HashSet;
import java.util.List;

public class Auto extends Veicolo {
    private String carburante, tipologia;
    private static HashSet<String> carburantiAccettati = new HashSet<>(List.of("benzina", "diesel", "gpl", "metano", "idrogeno", "elettrico"));

    public String getCarburante() {
        return carburante;
    }

    public void setCarburante(String carburante) {
        if(carburantiAccettati.contains(carburante.toLowerCase()))
            this.carburante = carburante;
        else
            throw new IllegalArgumentException("Carburante non valido");
    }

    public String getTipologia() {
        return tipologia;
    }
    
    public void setTipologia(String tipologia) {
        if(!tipologia.isBlank())
            this.tipologia = tipologia;
        else
            throw new IllegalArgumentException("Tipologia non inserita");
    }

    public Auto(String targa, String marca, int annoImm, double prezzo, String carburante, String tipologia){
        super(targa, marca, annoImm, prezzo);

        if(carburantiAccettati.contains(carburante.toLowerCase()))
            this.carburante = carburante;
        else
            throw new IllegalArgumentException("Carburante non valido");

        if(!tipologia.isBlank())
            this.tipologia = tipologia;
        else
            throw new IllegalArgumentException("Tipologia non inserita");
    }

    @Override
    public double getAssicurazione(){
        double assicurazione = 500;

        if(tipologia.equalsIgnoreCase("SUV"))
            assicurazione *= 1.1;
        else if(tipologia.equalsIgnoreCase("sportiva"))
            assicurazione *= 1.2;

        return assicurazione;
    }

    @Override
    public double getPrezzo(){
        if(carburante.equalsIgnoreCase("elettrico")){
            if(prezzo * 0.25 < 5000)
                return Math.floor(prezzo*0.75*100)/100;
            else
                return prezzo-5000;
        }

        return prezzo;

    }
}
