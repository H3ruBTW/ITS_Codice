package ES3;

import java.util.Random;

public class Dipendente {
    private String nome;
    private int ruolo, stipendio;

    public String getNome() {
        return nome;
    }

    public int getRuolo() {
        return ruolo;
    }

    public int getStipendio() {
        return stipendio;
    }

    public Dipendente(String nome){
        Random random = new Random();

        ruolo = random.nextInt(1, 4);
        stipendio = random.nextInt(10, 200) * 100;
        this.nome = nome;
    }

    @Override
    public String toString(){
        return "Nome: " + nome + " Ruolo: " + ruolo + " Stipendio: " + stipendio;
    }
}
