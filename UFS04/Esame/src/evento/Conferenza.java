package evento;

import java.time.LocalDate;

public class Conferenza extends Evento {
    String relatore;

    public String getRelatore() {
        return relatore;
    }

    public void setRelatore(String relatore) throws IllegalArgumentException {
        if(!relatore.isBlank())
            this.relatore = relatore;
        else
            throw new IllegalArgumentException("Blank");
    }

    public Conferenza(String nome, LocalDate data, int numero_posti, double prezzo, String relatore) throws IllegalArgumentException{
        super(nome, data, numero_posti, prezzo);
        setRelatore(relatore);
    }

    @Override
    public String descrizioneEvento() {
        return "Tipo Conferenza, Nome " + nome + 
        ", Data " + data +
        ", Numero Posti " + numero_posti +
        ", Numero Posti Disponibili " + getPostiDisponibili() +
        ", Prezzo a biglietto " + getPrezzoDinamico() +
        ", Relatore " + relatore;
    }

}
