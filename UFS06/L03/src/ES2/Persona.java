package ES2;

import java.time.LocalDate;
import java.util.Random;

public class Persona {
    private String nomeCompleto, sesso, nazionalita;
    private LocalDate dataN;

    String[] sessi = {"Maschio", "Femmina"};
    String[] nazionalitas = {
        "Italiana", "Italiana", "Italiana", "Italiana", "Italiana",
        "Italiana", "Italiana", "Italiana", "Italiana", "Italiana",
        "Italiana", "Italiana", "Italiana", "Italiana", "Italiana",
        "Francese", "Spagnola", "Tedesca", "Inglese", "Americana",
        "Giapponese", "Brasiliana", "Argentina", "Cinese", "Portoghese" 
    };

    public LocalDate getDataN() {
        return dataN;
    }

    public String getNazionalita() {
        return nazionalita;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getSesso() {
        return sesso;
    }

    public Persona(String nome){
        this.nomeCompleto = nome;

        Random random = new Random();

        sesso = sessi[random.nextInt(0, sessi.length)];
        nazionalita = nazionalitas[random.nextInt(0, nazionalitas.length)];

        int anno = random.nextInt(1988, 2008);
        int mese = random.nextInt(1, 13);
        int giorno = random.nextInt(1, 29);
        dataN = LocalDate.of(anno, mese, giorno);
    }

    @Override
    public String toString() {
        return "Persona{" +
                "nomeCompleto='" + nomeCompleto + '\'' +
                ", sesso='" + sesso + '\'' +
                ", nazionalita='" + nazionalita + '\'' +
                ", dataN=" + dataN +
                '}';
    }
}
