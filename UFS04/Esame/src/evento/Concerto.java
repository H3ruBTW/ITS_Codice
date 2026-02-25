package evento;

import java.time.LocalDate;

public class Concerto extends Evento {
    String genere;

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) throws IllegalArgumentException {
        if(!genere.isBlank())
            this.genere = genere;
        else
            throw new IllegalArgumentException("Blank");
    }

    public Concerto(String nome, LocalDate data, int numero_posti, double prezzo, String genere) throws IllegalArgumentException{
        super(nome, data, numero_posti, prezzo);
        setGenere(genere);
    }

    @Override
    public String descrizioneEvento() {
        return "Tipo Concerto, Nome " + nome + 
        ", Data " + data +
        ", Numero Posti " + numero_posti +
        ", Numero Posti Disponibili " + getPostiDisponibili() +
        ", Prezzo a biglietto " + getPrezzoDinamico() +
        ", Genere " + genere;
    }
}
