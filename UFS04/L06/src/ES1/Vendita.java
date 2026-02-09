package ES1;

import java.time.ZonedDateTime;

public class Vendita {
    private Veicolo veicolo;
    private ZonedDateTime data;

    public Veicolo getVeicolo() {
        return veicolo;
    }

    public void setVeicolo(Veicolo veicolo) {
        this.veicolo = veicolo;
    }

    public ZonedDateTime getData() {
        return data;
    }

    public Vendita (Veicolo veicolo){
        this.veicolo = veicolo;
        data = ZonedDateTime.now();
    }
}
